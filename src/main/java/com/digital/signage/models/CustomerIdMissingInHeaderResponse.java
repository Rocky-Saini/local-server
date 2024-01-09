package com.digital.signage.models;

import com.digital.signage.util.ApplicationConstants;
import com.digital.signage.util.Message;

/**
 * @author -Ravi Kumar created on 1/29/2023 9:11 PM
 * @project - Digital Sign-edge
 */
public class CustomerIdMissingInHeaderResponse<T> extends Response<T> {
    public CustomerIdMissingInHeaderResponse(Message message) {
        super(null, null, "CustomerIdMissingInHeader",
                ApplicationConstants.CUSTOMER_ID_MISSING_IN_HEADER,
                message.get(Message.Common.CUSTOMER_ID_MISSING_IN_HEADER),
                400);
    }
}

