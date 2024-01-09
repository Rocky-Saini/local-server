package com.digital.signage.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * {
 * "multicast_id": 6191783887815551302,
 * "success": 1,
 * "failure": 0,
 * "canonical_ids": 0,
 * "results": [
 * {
 * "message_id": "0:1527687461236695%e609af1cf9fd7ecd"
 * }
 * ]
 * }
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FcmPushResponseModel {
    //Unique ID (number) identifying the multicast message.
    @JsonProperty("multicast_id")
    private Long multicastId;
    //Number of messages that were processed without an error.
    private Integer success;
    //Number of messages that could not be processed.
    private Integer failure;
    /**
     * Array of objects representing the status of the messages processed.
     * The objects are listed in the same order as the request (i.e., for each registration
     * ID in the request, its result is listed in the same index in the response).
     *
     * message_id: String specifying a unique ID for each successfully processed message.
     *
     *
     * registration_id: Optional string specifying the canonical registration token for the
     * client app that the message was processed and sent to. Sender should use this value as
     * the registration token for future requests. Otherwise, the messages might be rejected.
     *
     *
     * error: String specifying the error that occurred when processing the message for the
     * recipient. The possible values can be found in table 9.
     */
    private List<FcmMessageId> results;

    public Long getMulticastId() {
        return multicastId;
    }

    public void setMulticastId(Long multicastId) {
        this.multicastId = multicastId;
    }

    public boolean isSuccess() {
        return success != null && success.equals(1);
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessageId() {
        String message = null;
        if (isSuccess() && getResults() != null && !getResults().isEmpty()) {
            message = getResults().get(0).getMessageId();
        }
        return message;
    }

    public boolean isRegistrationTokenInvalid() {
        boolean isRegistrationTokenInvalid = false;
        if (isFailure() && getResults() != null && !getResults().isEmpty()) {
            isRegistrationTokenInvalid = getResults().get(0).isRegistrationTokenValid();
        }
        return isRegistrationTokenInvalid;
    }

    public boolean isMismatchSenderId() {
        boolean isMismatchSenderId = false;
        if (isFailure() && getResults() != null && !getResults().isEmpty()) {
            isMismatchSenderId = getResults().get(0).isMismatchSenderId();
        }
        return isMismatchSenderId;
    }

    public Integer getFailure() {
        return failure;
    }

    public boolean isFailure() {
        return failure != null && failure.equals(1);
    }

    public void setFailure(Integer failure) {
        this.failure = failure;
    }

    public List<FcmMessageId> getResults() {
        return results;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FcmPushResponseModel)) return false;
        FcmPushResponseModel that = (FcmPushResponseModel) o;
        return new EqualsBuilder().append(multicastId, that.multicastId)
                .append(success, that.success)
                .append(failure, that.failure)
                .append(results, that.results)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(multicastId)
                .append(success)
                .append(failure)
                .append(results)
                .toHashCode();
    }

    public void setResults(List<FcmMessageId> results) {
        this.results = results;
    }

    @Override public String toString() {
        return new ToStringBuilder(this)
                .append("multicastId", multicastId)
                .append("success", success)
                .append("failure", failure)
                .append("results", results)
                .toString();
    }

    public static class FcmMessageId {
        public static final String ERROR_INVALID_REGISTRATION = "InvalidRegistration";
        public static final String ERROR_MISMATCH_SENDER_ID = "MismatchSenderId";
        //  String specifying a unique ID for each successfully processed message.
        @JsonProperty("message_id")
        private String messageId;
        //String specifying the error that occurred when processing the message for the recipient.
        // The possible values can be found in table 9.
        private String error;

        public boolean isSuccess() {
            return messageId != null && !messageId.isEmpty();
        }

        public boolean isRegistrationTokenValid() {
            return ERROR_INVALID_REGISTRATION.equals(error);
        }

        public boolean isMismatchSenderId() {
            return ERROR_MISMATCH_SENDER_ID.equals(error);
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
            this.messageId = messageId;
        }

        @Override public String toString() {
            return new ToStringBuilder(this)
                    .append("messageId", messageId)
                    .append("error", error)
                    .toString();
        }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FcmMessageId)) return false;
            FcmMessageId that = (FcmMessageId) o;
            return new EqualsBuilder().append(getMessageId(),
                    that.getMessageId()).append(getError(), that.getError()).isEquals();
        }

        @Override public int hashCode() {
            return new HashCodeBuilder(17, 37).append(getMessageId()).append(getError()).toHashCode();
        }
    }
}
