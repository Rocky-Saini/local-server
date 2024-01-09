package com.digital.signage.util;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 12/23/2022 12:09 AM
 * @project - Digital Sign-edge
 */
public enum PanelControl {

    RS232_RM_HDMI_ONLY(1),
    PANEL_OTHERS(2),
    RJ45(3),
    PROJECTOR_RZ660(4),
    RJ45_QM(5),
    RS232_QM(6),
    PROJECTOR_TX410D(7),
    PROJECTOR_RZ660_WITH_ARDUINO(8),
    PROJECTOR_TX410D_WITH_ARDUINO(9),
    RS232_VIDEO_WALL(10),
    RS232_UMP(11),
    RS232_IWB(12);
    private final int value;

    PanelControl(int value) {
        this.value = value;
    }

    private static final Map<Integer, PanelControl> MAP = new HashMap<>();

    static {
        for (PanelControl panelControl : PanelControl.values()) {
            MAP.put(panelControl.value, panelControl);
        }
    }

    public static PanelControl valueOf(int panelControl) {
        return MAP.get(panelControl);
    }

    public int getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class PanelControlConverter implements AttributeConverter<PanelControl, Integer> {

        @Override
        public Integer convertToDatabaseColumn(PanelControl panelControl) {
            if (null == panelControl) return null;
            return panelControl.getValue();
        }

        @Override
        public PanelControl convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return PanelControl.valueOf(dbData);
        }
    }
}
