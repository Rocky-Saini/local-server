package com.digital.signage.specifications;

import com.digital.signage.models.Panel;
import com.digital.signage.models.PanelStatus;
import com.digital.signage.repository.PanelRepository;
import com.digital.signage.repository.PanelStatusRepository;
import com.digital.signage.util.DataCollectionEnum;
import com.digital.signage.util.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import static org.springframework.data.jpa.domain.Specification.where;

import java.util.List;

/**
 * @author -Ravi Kumar created on 1/23/2023 3:34 PM
 * @project - Digital Sign-edge
 */
@Component
public class PanelSpecification {

    @Autowired
    PanelRepository panelRepository;

    @Autowired
    PanelStatusRepository panelStatusRepository;

    public Specification<Panel> getPanelSpecification(Long deviceId, String panelName,
                                                      DataCollectionEnum.PanelStatus panelStatus, Long customerId) {
        Specification<Panel> panelSpecification = null;

        if (deviceId != null) {
            if (panelSpecification != null) {
                panelSpecification = panelSpecification.and(deviceIdEqualTo(deviceId, customerId));
            } else {
                panelSpecification = where(deviceIdEqualTo(deviceId, customerId));
            }
        }

        if (panelName != null) {
            if (panelSpecification != null) {
                panelSpecification = panelSpecification.and(panelNameEqualTo(panelName, customerId));
            } else {
                panelSpecification = where(panelNameEqualTo(panelName, customerId));
            }
        }

        if (panelStatus != null) {
            if (panelSpecification != null) {
                List<PanelStatus> panelStatusList =
                        panelStatusRepository.getPanelStatus(panelStatus.name());
                if (panelStatusList != null && !panelStatusList.isEmpty()) {
                    for (PanelStatus panelStatusObj : panelStatusList) {
                        panelSpecification = panelSpecification.and(
                                panelIdEqualTo(panelStatusObj.getPanel().getId(), customerId));
                    }
                }
            } else {
                List<PanelStatus> panelStatusList = panelStatusRepository.getPanelStatus(panelStatus.name());
                if (panelStatusList != null && !panelStatusList.isEmpty()) {
                    for (PanelStatus panelStatusObj : panelStatusList) {
                        if (panelSpecification != null) {
                            panelSpecification = panelSpecification.or(
                                    panelIdEqualTo(panelStatusObj.getPanel().getId(), customerId));
                        } else {
                            panelSpecification =
                                    where(panelIdEqualTo(panelStatusObj.getPanel().getId(), customerId));
                        }
                    }
                }
            }
        }

        return panelSpecification;
    }

    private Specification<Panel> panelNameEqualTo(String panelName, Long customerId) {
        return (root, query, cb) -> cb.and(
                cb.equal(root.get(Panel.JSON_KEY_PANEL_NAME), panelName),
                cb.equal(root.get(Panel.JSON_KEY_CUSTOMER_ID), customerId),
                cb.notEqual(root.get(Panel.JSON_KEY_STATUS), Status.DELETED));
    }

    private Specification<Panel> deviceIdEqualTo(Long deviceId, Long customerId) {
        return (root, query, cb) -> cb.and(
                cb.equal(root.get(Panel.JSON_KEY_DEVICE), deviceId),
                cb.equal(root.get(Panel.JSON_KEY_CUSTOMER_ID), customerId),
                cb.notEqual(root.get(Panel.JSON_KEY_STATUS), Status.DELETED));

    }

    private Specification<Panel> panelIdEqualTo(Long panelId, Long customerId) {
        return (root, query, cb) -> cb.and(
                cb.equal(root.get(Panel.JSON_KEY_PANEL_ID), panelId),
                cb.equal(root.get(Panel.JSON_KEY_CUSTOMER_ID), customerId),
                cb.notEqual(root.get(Panel.JSON_KEY_STATUS), Status.DELETED));

    }

}
