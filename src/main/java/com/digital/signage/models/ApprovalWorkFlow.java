package com.digital.signage.models;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 1/22/2023 11:50 PM
 * @project - Digital Sign-edge
 */
public enum ApprovalWorkFlow {
    LAYOUT(1), LAYOUT_STRING(2), PLANOGRAM(3), PLANOGRAM_AND_LAYOUT(4), NONE(5);

    private final int value;

    ApprovalWorkFlow(int value) {
        this.value = value;
    }

    private static final Map<Integer, ApprovalWorkFlow> MAP = new HashMap<>();

    static {
        for (ApprovalWorkFlow pageType : ApprovalWorkFlow.values()) {
            MAP.put(pageType.value, pageType);
        }
    }

    public static ApprovalWorkFlow valueOf(int approverWorkFlow) {
        return MAP.get(approverWorkFlow);
    }

    public int getValue() {
        return value;
    }

    @Converter(autoApply = true)
    public static class ApprovalWorkFlowConverter
            implements AttributeConverter<ApprovalWorkFlow, Integer> {

        @Override
        public Integer convertToDatabaseColumn(ApprovalWorkFlow approvalWorkFlow) {
            if (null == approvalWorkFlow) return null;
            return approvalWorkFlow.getValue();
        }

        @Override
        public ApprovalWorkFlow convertToEntityAttribute(Integer dbData) {
            if (null == dbData) return null;
            return ApprovalWorkFlow.valueOf(dbData);
        }
    }
}
