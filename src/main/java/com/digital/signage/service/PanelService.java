package com.digital.signage.service;

import com.digital.signage.dto.DeletedDataDTO;
import com.digital.signage.dto.DevicePanelDTO;
import com.digital.signage.dto.PanelsStatusRequestDTO;
import com.digital.signage.models.Device;
import com.digital.signage.exceptions.InvalidRequestDataException;
import com.digital.signage.models.Panel;
import com.digital.signage.models.PanelExt;
import com.digital.signage.models.Response;
import com.digital.signage.models.User;
import com.digital.signage.util.DataCollectionEnum;
import com.digital.signage.util.SortEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 1/2/2023 2:11 AM
 * @project - Digital Sign-edge
 */
public interface PanelService extends BaseService {

    /**
     *
     */
    Response<?> addPanel(Panel panel, HttpServletRequest request);

    /**
     *
     */

    Response<?> updatePanel(Long panelId, Panel panel, HttpServletRequest request);

    /**
     *
     */
    Response<List<PanelExt>> getPanelList(HttpServletRequest request, SortEnum sortEnum);


    Response<PanelExt> getPanel(Long panelId, HttpServletRequest request);

    /**
     *
     */

    Panel createNewDefaultPanel(Device device);

    /**
     *
     */
    Response<?> updateDevicePanels(DevicePanelDTO panel, HttpServletRequest request);

    /**
     *
     */
    Response<?> validateDevicePanels(DevicePanelDTO devicePanelDTO,
                                     Map<Long, ArrayList<String>> linkedPanel, HttpServletRequest request);

    /**
     *
     */
    Response<?> updatePanelStatuses(PanelsStatusRequestDTO panelStatusReqDTO,
                                    HttpServletRequest request);

    /**
     * @param panelStatusReqDTO with timezone
     */
    Response<?> updatePanelStatusesVersion2(PanelsStatusRequestDTO panelStatusReqDTO,
                                            HttpServletRequest request);

    /**
     * Displing the all panel status using with deviceId
     */
    Response<?> getPanelStatusByDeviceId(Long deviceId);

    Response deletePanel(long deviceId, long panelId);

    Response<?> getPanels(
            Long deviceId,
            String panelName,
            DataCollectionEnum.PanelStatus panelStatus,
            SortEnum sortEnum
    );

    List<Long> getDeviceListByLocation(User user,String mediaPlayerName);

    Response<?> updateDeletedPanelStatus(
            Long panelId,
            List<DeletedDataDTO> panelDeletedStatusDTOS,
            HttpServletRequest httpServletRequest
    ) throws InvalidRequestDataException;

    PanelExt getPanelExtFromPanel(Panel panel);
}
