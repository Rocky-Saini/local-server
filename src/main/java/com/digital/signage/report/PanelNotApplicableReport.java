package com.digital.signage.report;

import com.digital.signage.models.DataCollectionConfig;
import com.digital.signage.models.Panel;
import com.digital.signage.models.PanelLogReportResponseNewDTO;
import com.digital.signage.repository.PanelRepository;
import com.digital.signage.repository.PanelStatusRepository;
import com.digital.signage.util.DataCollectionEnum;
import com.digital.signage.util.DateUtils;
import com.digital.signage.util.ReportsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.digital.signage.util.DataCollectionEnum.OutputStatus.NOT_APPLICABLE;
import static com.digital.signage.util.DataCollectionEnum.OutputStatus.WEEK_OFF;
import static com.digital.signage.util.DateUtils.*;

@Component
public class PanelNotApplicableReport extends ReportsUtils {
    @Autowired
    private PanelRepository panelRepository;
    @Autowired
    private PanelStatusRepository panelStatusRepository;

    public List<PanelLogReportResponseNewDTO> addNotApplicableEntryInResponseDTOS(
            List<Date> filterDateRange, String reportToken, List<Long> validPanelIds,
            List<PanelLogReportResponseNewDTO> responseDTOS,
            final Map<String, List<DataCollectionConfig>> businessConfigMap,
            final Map<Long, List<DataCollectionConfig>> defaultDataCollectionConfig) {
        responseDTOS = ObjectUtils.isEmpty(responseDTOS) ? new ArrayList<>() : responseDTOS;
        if (!ObjectUtils.isEmpty(filterDateRange) && !ObjectUtils.isEmpty(validPanelIds)) {
            List<Panel> panelListFromDb = panelRepository.findAllByPanelIds(validPanelIds);
            Map<Long, Panel> panelMap =
                    panelListFromDb.stream().collect(Collectors.toMap(Panel::getId,
                            Function.identity()));
            final List<PanelLogReportResponseNewDTO> reportNewDTOS = new ArrayList<>();
            if (!ObjectUtils.isEmpty(panelListFromDb) && !ObjectUtils.isEmpty(filterDateRange)) {
                filterDateRange.forEach(date ->
                        validPanelIds.forEach(aLong -> {
                            Panel panel = panelMap.get(aLong);
                            if (panel != null) {
                                //fill NA as status as either when panel is created after requested from date or panel is delete before requested to date.
                                if (isBothDateOfSameDate(date, panel.getCreatedOn()) || (panel.getDeletedOn()
                                        != null && isBothDateOfSameDate(date, panel.getDeletedOn()))) {
                                    String keysForHashMap = getKeysForHashMap(panel.getDevice().getDeviceId(), date);
                                    List<DataCollectionConfig> dataCollectionConfigs =
                                            chooseConfigFromMapOrDefault(keysForHashMap, businessConfigMap,
                                                    defaultDataCollectionConfig);
                                    for (DataCollectionConfig currentDateConfig : dataCollectionConfigs) {
                                        /*
                                         * handle when same date device is created and deleted
                                         * */
                                        if (isDeletedAndCreatedOnSameDateDateUnderConfig(currentDateConfig, panel,
                                                date)) {
                                            addSameDateDeletedAndCreatedNotApplicableEntry(currentDateConfig, date, panel,
                                                    reportToken,
                                                    reportNewDTOS);
                                        } else {
                                            PanelLogReportResponseNewDTO notApplicableEntry =
                                                    createNotApplicableEntry(panel, currentDateConfig, date, reportToken);
                                            if (notApplicableEntry != null) reportNewDTOS.add(notApplicableEntry);
                                        }
                                        //add week off for deleted date if panel is deleted on week_off
                                        PanelLogReportResponseNewDTO weekOffOnDeletedDate =
                                                createWeekOffDeletedDayEntry(currentDateConfig, panel, date, reportToken);
                                        if (weekOffOnDeletedDate != null) reportNewDTOS.add(weekOffOnDeletedDate);
                                    }
                                }
                            }
                        })
                );
            }
            if (!ObjectUtils.isEmpty(reportNewDTOS)) {
                responseDTOS.addAll(reportNewDTOS);
            }
        }
        return responseDTOS;
    }

    @Nullable
    private PanelLogReportResponseNewDTO createNotApplicableEntry(Panel panel,
                                                                  DataCollectionConfig currentDateConfig, Date date, String reportToken) {
        Date startTime, endTime;
        Date panelOnTime = combineUtilDateAndTime(date, currentDateConfig.getPanelOnTime());
        Date panelOffTime = combineUtilDateAndTime(date, currentDateConfig.getPanelOffTime());
        boolean isPanelDeletedUnderConfig =
                panel.getDeletedOn() != null && isMeBetweenTheseTwoDates(panel.getDeletedOn(),
                        panelOnTime, panelOffTime);
        boolean isPanelCreatedUnderConfig = isMeBetweenTheseTwoDates(panel.getCreatedOn(),
                panelOnTime, panelOffTime);
        /*
         * if device is deleted before config or device created before config then return null
         */
        if (!isPanelDeletedUnderConfig && !isPanelCreatedUnderConfig) return null;
        // now make start date and end date
        //in case of createdDate ==date then  startTime= panelOnTime and endTime= createdDate
        //in case of deletedDate ==date then  startTime= deletedDate and endTime= panelOffTime
        if (panel.getDeletedOn() != null && isPanelDeletedUnderConfig) {
            startTime = panel.getDeletedOn();
        } else if (currentDateConfig != null
                && currentDateConfig.getPanelOnTime() != null) {
            startTime = panelOnTime;
        } else {
            startTime = floorDate(date);
        }
        if (isPanelCreatedUnderConfig) {
            endTime = panel.getCreatedOn();
        } else if (currentDateConfig != null
                && currentDateConfig.getPanelOffTime() != null) {
            endTime = panelOffTime;
        } else {
            endTime = ceilDate(date);
        }
        if (isThisPastDate(startTime) && isThisPastDate(endTime) && endTime.after(startTime)) {
            return createResponseDTORow(panel, startTime, endTime,
                    reportToken, NOT_APPLICABLE,
                    NOT_APPLICABLE.toString());
        } else {
            return null;
        }
    }

    private void addSameDateDeletedAndCreatedNotApplicableEntry(
            DataCollectionConfig currentDateConfig, Date date,
            Panel panel, String reportToken, List<PanelLogReportResponseNewDTO> reportNewDTOS) {
        reportNewDTOS.add(
                createResponseDTORow(panel,
                        combineUtilDateAndTime(date, currentDateConfig.getPanelOnTime()), panel.getCreatedOn(),
                        reportToken,
                        NOT_APPLICABLE));
        reportNewDTOS.add(createResponseDTORow(panel, panel.getDeletedOn(),
                combineUtilDateAndTime(date, currentDateConfig.getPanelOffTime()), reportToken,
                NOT_APPLICABLE));
    }

    private boolean isDeletedAndCreatedOnSameDateDateUnderConfig(
            DataCollectionConfig currentDateConfig, Panel panel, Date date) {
        boolean isSameDayDeleted =
                panel.getDeletedOn() != null && isBothDateOfSameDate(panel.getCreatedOn(),
                        panel.getDeletedOn());
        if (isSameDayDeleted) {
            Date panelOnTime = combineUtilDateAndTime(date, currentDateConfig.getPanelOnTime());
            Date panelOffTime = combineUtilDateAndTime(date, currentDateConfig.getPanelOffTime());
            return
                    isMeBetweenTheseTwoDates(panel.getCreatedOn(), panelOnTime, panelOffTime)
                            && isMeBetweenTheseTwoDates(panel.getDeletedOn(), panelOnTime, panelOffTime);
        }
        return false;
    }

    private PanelLogReportResponseNewDTO createWeekOffDeletedDayEntry(
            DataCollectionConfig currentDateConfig, Panel panel, Date date, String reportToken) {
        /*
         * WEEk_OFF handle on deleted date , add one week_off entry if today is week_Off And today panel is deleted
         * Example 1: config time 09:00 to 18:00 and today is week_off and panel is deleted at 15:00
         *   entries{
         *      1.> 09:00 to 15:00  WEEK_OFF
         *      2.> 15:00 to 18:00 NOT_APPLICABLE
         *     }
         * Example 2: config time 10:00 to 08:00 and today and tomorrow is week_off and panel is deleted at 15:00
         *   entries{
         *      1.> 10:00 to 15:00  WEEK_OFF
         *      2.> 15:00 to 23:59:59 NOT_APPLICABLE
         *     }
         * Example 3: config time 10:00 to 08:00 and today and tomorrow is week_off and panel is deleted at 01:00(next day)
         *   entries{
         *      1.> 10:00 to 23:59:59  WEEK_OFF
         *      2.> 00:00:00 to 01:00:00 WEEK_OFF
         *      2.> 01:00 to 08:00 NOT_APPLICABLE
         *     }
         * Example 4: same day deleted case like config is 10:00 to 18:00 and panel is created at 12:00 and deleted at 12:30 then
         * entries{
         *  10:00 -12:00 NOT_APPLICABLE
         *  12:00- 12:30 WEEK_OFF
         *  12:30 to 18:00 NOT_APPLICABLE
         *  }
         */
        if (isPanelCreatedOrDeletedOnWeekOffDay(panel, currentDateConfig, date)) {
            Date panelOnTime = combineUtilDateAndTime(date, currentDateConfig.getPanelOnTime());
            Date panelOffTime = combineUtilDateAndTime(date, currentDateConfig.getPanelOffTime());
            boolean isCreatedUnderConfig =
                    isMeBetweenTheseTwoDates(panel.getCreatedOn(), panelOnTime, panelOffTime);
            Date startTime = isCreatedUnderConfig ? panel.getCreatedOn() : panelOnTime;
            boolean isDeletedUnderConfig =
                    isMeBetweenTheseTwoDates(panel.getDeletedOn(), panelOnTime, panelOffTime);
            Date endTime = isDeletedUnderConfig ? panel.getDeletedOn() : panelOffTime;
            if (isTheseDatesEqualsOrBefore(endTime, DateUtils.getCurrentTime())) {
                return createResponseDTORow(panel, startTime, endTime, reportToken, WEEK_OFF);
            }
        }
        return null;
    }
}

