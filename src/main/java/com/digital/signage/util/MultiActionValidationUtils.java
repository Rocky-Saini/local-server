package com.digital.signage.util;

import com.digital.signage.models.FieldMessageHelper;
import com.digital.signage.models.MultiEntityStatusChangeRequest;
import com.digital.signage.models.ValidationErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author -Ravi Kumar created on 1/28/2023 4:22 PM
 * @project - Digital Sign-edge
 */
@Service
public class MultiActionValidationUtils {
    @Autowired
    private Message message;

    public synchronized ValidationErrorResponse validateCommonMultiActionRequest(
            MultiEntityStatusChangeRequest multiEntityStatusChangeRequest, boolean isStatusRequired) {
        Status requestStatus = multiEntityStatusChangeRequest.getStatus();
        // check if request is correct
        if (isStatusRequired && requestStatus == null) {
            ValidationErrorResponse r = new ValidationErrorResponse(new FieldMessageHelper()
                    .addFieldList(MultiEntityStatusChangeRequest.JSON_KEY_STATUS,
                            message.get(Message.Common.COMMON_FIELD_VALIDATION_MESSAGE))
                    .get(), message);
            r.setHttpStatusCode(200);
            return r;
        }
        if (multiEntityStatusChangeRequest.getIds() == null
                || multiEntityStatusChangeRequest.getIds().isEmpty()) {
            ValidationErrorResponse r = new ValidationErrorResponse(new FieldMessageHelper()
                    .addFieldList(MultiEntityStatusChangeRequest.JSON_KEY_IDS,
                            message.get(Message.Common.COMMON_FIELD_VALIDATION_MESSAGE))
                    .get(), message);
            r.setHttpStatusCode(200);
            return r;
        }
        //Remove Duplicate
        List<Long> ids =
                multiEntityStatusChangeRequest.getIds().stream().distinct().collect(
                        Collectors.toList());
        multiEntityStatusChangeRequest.setIds(ids);
        return null;
    }
}
