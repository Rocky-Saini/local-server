package com.digital.signage.models;

import com.digital.signage.util.JacksonUtilsKt;
import com.digital.signage.util.Message;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 1/16/2023 5:35 PM
 * @project - Digital Sign-edge
 */
public class Response<T> {

    public static final String EMAIL_TOKEN = "emailToken";

    public static final String INVALID_EMAIL_TOKEN = "InvalidEMailToken";

    public static final String PASSWORD_NOT_COMPLEX_ENOUGH = "PasswordNotComplexEnough";

    public static final String PASSWORD = "password";

    public static final String USER_NOT_EXISTS = "UserNotExist";

    public static final String USER_ROLE_UPDATED = "UserRoleUpdated";

    public static final String USER_CREATED_AND_EMAIL_SENT = "UserCreatedAndEmailSent";

    public static final String RESET_PASSWORD_LINK_SENT_TO_EMAIL = "ResetPasswordLinkSentToEmail";

    public static final String INVALID_REQUEST_PARAMETER = "InvalidRequestParameter";

    public static final String ENTITY_NOT_FOUND = "EntityNotFound";

    public static final String USER_INACTIVE = "UserInactive";

    public static final String CUSTOMER_INACTIVE = "CustomerInactive";

    public static final String USER_LINKED_TO_ANOTHER_CUSTOMER = "UserLinkedToAnotherCustomer";

    public static final String INVALID_EMAIL = "InvalidEmail";

    public static final String BAD_CREDENTIALS = "BadCredentials";

    public static final String UNKNOWN_ERROR = "UnknownError";

    public static final String PASSWD_UPDATE_FAILED = "PasswordUpdateFailed";

    public static final String PASSWD_UPDATE_SUCCESSFUL = "PasswordUpdateSuccess";

    public static final String EXPIRED_EMAIL_TOKEN = "ExpiredEmailToken";

    public static final String PASSWD_NOT_SET = "PasswdNotSet";

    public static final String SUCCESSFULLY_SAVED = "SuccessFullySaved";

    public static final String SUCCESSFULLY_UPDATED = "SuccessFullyUpdated";

    public static final String PLANOGRAM_PRIORITY = "PlanogramPriority";

    public static final String PLANOGRAM_START_DATE = "PlanogramStartDate";

    public static final String PLANOGRAM_END_DATE = "PlanogramEndDate";

    public static final String PLANOGRAM_START_TIME = "PlanogramStartTime";

    public static final String PLANOGRAM_END_TIME = "PLanogramEndTime";

    public static final String LAYOUT = "layout";

    public static final String DEVICE_LOGIC = "deviceLogic";

    public static final String BAD_REQUEST = "BadRequest";

    private T result;
    private T data;

    private Pagination pagination;

    private String name;

    private Integer code;

    private String message;

    @JsonIgnore
    private Integer httpStatusCode;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> debugInfoMap;

    public Map<String, String> getDebugInfoMap() {
        return debugInfoMap;
    }

    public void setDebugInfoMap(Map<String, String> debugInfoMap) {
        this.debugInfoMap = debugInfoMap;
    }

    public Response(
            T result,
            Pagination pagination,
            String name,
            Integer code,
            String message,
            Integer httpStatusCode
    ) {
        this.result = result;
        this.pagination = pagination;
        this.name = name;
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    public Response() {
    }

    public Response(
            T result,
            Pagination pagination,
            String name,
            Integer code,
            String message
    ) {
        this.result = result;
        this.pagination = pagination;
        this.name = name;
        this.code = code;
        this.message = message;
    }

    public Response(
            Integer code,
            String name,
            String message,
            Integer httpStatusCode
    ) {
        this.httpStatusCode = httpStatusCode;
        this.name = name;
        this.code = code;
        this.message = message;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private static final Response<Void> DEPRECATED_API_RESPONSE = new Response<>(
            null,
            null,
            "ApiDeprecated",
            200,
            "This API is deprecated",
            200
    );

    public static Response<Void> getDeprecatedApiResponse() {
        return DEPRECATED_API_RESPONSE;
    }

    public static <F> Response<F> copyExceptResultFromResponse(Response<?> response) {
        Response<F> r = new Response<>();
        r.setHttpStatusCode(response.httpStatusCode);
        r.setMessage(response.message);
        r.setCode(response.code);
        r.setName(response.name);
        return r;
    }

    public static <F> Response<F> createInternalServerErrorResponseFromException(Exception e) {
        Response<F> r = new Response<>();
        r.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        r.setMessage(ExceptionUtils.getFullStackTrace(e));
        r.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        r.setName("InternalServerError");
        return r;
    }

    public static Response<Void> createInternalServerErrorVoidResponseFromException(Exception e) {
        Response<Void> r = new Response<>();
        r.setHttpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        r.setMessage(ExceptionUtils.getFullStackTrace(e));
        r.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        r.setName("InternalServerError");
        return r;
    }

    public static <F> Response<F> createBadRequestResponse(String message) {
        Response<F> r = new Response<>();
        r.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        r.setMessage(message);
        r.setCode(HttpStatus.BAD_REQUEST.value());
        r.setName("BadRequest");
        return r;
    }

    public static <F> Response<F> createBadRequestResponse(String name, String message) {
        Response<F> r = new Response<>();
        r.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        r.setMessage(message);
        r.setCode(HttpStatus.BAD_REQUEST.value());
        r.setName(name);
        return r;
    }

    public static Response<?> createBadRequestResponseFromException(Exception e) {
        Response<?> r = new Response<>();
        r.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        r.setMessage(e.getMessage());
        r.setCode(HttpStatus.BAD_REQUEST.value());
        r.setName("BadRequest");
        return r;
    }

    public static <F> Response<F> createBadRequestResponse(Message message, String messageCode) {
        Response<F> r = new Response<>();
        r.setHttpStatusCode(HttpStatus.BAD_REQUEST.value());
        r.setMessage(message.get(messageCode));
        r.setCode(HttpStatus.BAD_REQUEST.value());
        r.setName("BadRequest");
        return r;
    }

    public static <F> Response<F> createNotFoundRequestResponse(Message message, String messageCode) {
        Response<F> r = new Response<>();
        r.setHttpStatusCode(HttpStatus.NOT_FOUND.value());
        r.setMessage(message.get(messageCode));
        r.setCode(HttpStatus.NOT_FOUND.value());
        r.setName("NotFound");
        return r;
    }

    /**
     * @param httpStatusCode in 5XX range
     * @param message        {@link String} message to send
     * @return {@link Response} Response object
     */
    public static <F> Response<F> createServerErrorResponse(int httpStatusCode, String message) {
        Response<F> r = new Response<>();
        r.setHttpStatusCode(httpStatusCode);
        r.setMessage(message);
        r.setCode(httpStatusCode);
        r.setName("ServerError");
        return r;
    }

    public static <F> Response<F> notSupported(Message message, String messageCode) {
        Response<F> r = new Response<>();
        r.setHttpStatusCode(HttpStatus.FORBIDDEN.value());
        r.setMessage(message.get(messageCode));
        r.setCode(HttpStatus.FORBIDDEN.value());
        r.setName("NotSupported");
        return r;
    }

    public static <F> Response<F> notSupported() {
        Response<F> r = new Response<>();
        r.setHttpStatusCode(HttpStatus.FORBIDDEN.value());
        r.setMessage("Not supported");
        r.setCode(HttpStatus.FORBIDDEN.value());
        r.setName("NotSupported");
        return r;
    }

    public static <F> Response<F> notSupportedForBasicCustomer(Message message) {
        Response<F> r = new Response<>();
        r.setHttpStatusCode(HttpStatus.FORBIDDEN.value());
        r.setMessage(message.get(Message.CustomerTiers.FEATURE_NOT_SUPPORTED_FOR_BASIC_CUSTOMER));
        r.setCode(HttpStatus.FORBIDDEN.value());
        r.setName("NotSupportedInBasicCustomer");
        return r;
    }

    public static <F> Response<F> notSupportedForPdnCustomer() {
        Response<F> r = new Response<>();
        r.setHttpStatusCode(HttpStatus.FORBIDDEN.value());
        r.setMessage("Advertisement Disabled.");
        r.setCode(HttpStatus.FORBIDDEN.value());
        r.setName("NotSupported");
        return r;
    }

    public static <F> Response<F> createUnAuthorizedResponse() {
        return new Response<>(null, null,
                "UnAuthorized", null,
                null, HttpStatus.UNAUTHORIZED.value());
    }

    public static <F> Response<F> empty(Integer httpStatusCode) {
        return new Response<>(null, null,
                "UnAuthorized", null,
                null, httpStatusCode);
    }

    public static Response<?> parse(String string, Integer httpStatusCode, ObjectMapper objectMapper)
            throws IOException {
        Response<?> response =
                objectMapper.readValue(string, JacksonUtilsKt.getTypeRefOfResponseOfOutAny());
        response.setHttpStatusCode(httpStatusCode);
        return response;
    }

    public static Response<?> parse(
            InputStream inputStream,
            Integer httpStatusCode,
            ObjectMapper objectMapper
    ) throws IOException {
        objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
        Response<?> response =
                objectMapper.readValue(inputStream, JacksonUtilsKt.getTypeRefOfResponseOfOutAny());
        response.setHttpStatusCode(httpStatusCode);
        return response;
    }

    public static class FieldMessageBuilder extends Builder<List<FieldMessage>> {
        public FieldMessageBuilder(List<FieldMessage> fieldMessages) {
            if (fieldMessages.isEmpty()) {
                throw new IllegalArgumentException("Messages list cannot be empty");
            }
            result(fieldMessages);
            init();
        }

        public FieldMessageBuilder(FieldMessageHelper fieldMessageHelper) {
            if (fieldMessageHelper.get().isEmpty()) {
                throw new IllegalArgumentException("Messages list cannot be empty");
            }
            result(fieldMessageHelper.get());
            init();
        }

        public FieldMessageBuilder() {
            result(new ArrayList<>());
            init();
        }

        private void init() {
            code(400);
            name("BadRequest");
            message(null);
        }

        public FieldMessageBuilder addFieldMessage(String field, String message) {
            getResult().add(new FieldMessage(field, message));
            return this;
        }

        public FieldMessageBuilder addFieldMessage(FieldMessage fieldMessage) {
            getResult().add(fieldMessage);
            return this;
        }
    }

    public static class Builder<T> {

        private T result;
        private Integer code;
        private String name;
        private String message;
        private Integer httpStatusCode;
        private Map<String, String> debugInfoMap;
        private Pagination pagination;

        public Builder() {
        }

        public static Builder<Void> voidResponseBuilder() {
            return new Builder<>();
        }

        public static Builder<String> stringResponseBuilder() {
            return new Builder<>();
        }

        public static Builder<List<FieldMessage>> fieldMessageResponseBuilder() {
            return new FieldMessageBuilder();
        }

        public Builder(
                T result,
                Integer code,
                String name,
                String message,
                Integer httpStatusCode,
                Map<String, String> debugInfoMap,
                Pagination pagination
        ) {
            this.result = result;
            this.code = code;
            this.name = name;
            this.message = message;
            this.httpStatusCode = httpStatusCode;
            this.debugInfoMap = debugInfoMap;
            this.pagination = pagination;
        }

        protected T getResult() {
            return result;
        }

        public Builder<T> result(T result) {
            this.result = result;
            return this;
        }

        public Builder<T> code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder<T> name(String name) {
            this.name = name;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> badRequest() {
            this.httpStatusCode = HttpStatus.BAD_REQUEST.value();
            return this;
        }

        public Builder<T> notFound() {
            this.httpStatusCode = HttpStatus.NOT_FOUND.value();
            return this;
        }

        public Builder<T> forbidden() {
            this.httpStatusCode = HttpStatus.FORBIDDEN.value();
            return this;
        }

        public Builder<T> success() {
            this.httpStatusCode = HttpStatus.OK.value();
            return this;
        }

        public Builder<T> internalServerError() {
            this.httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            return this;
        }

        public Builder<T> unauthorized() {
            this.httpStatusCode = HttpStatus.UNAUTHORIZED.value();
            return this;
        }

        public Builder<T> httpStatusCode(Integer httpStatusCode) {
            this.httpStatusCode = httpStatusCode;
            return this;
        }

        public Builder<T> debugInfoMap(Map<String, String> debugInfoMap) {
            this.debugInfoMap = debugInfoMap;
            return this;
        }

        public Builder<T> pagination(Pagination pagination) {
            this.pagination = pagination;
            return this;
        }

        public Response<T> build() {
            return new Response<>(this);
        }
    }

    private Response(Builder<T> builder) {
        this.result = builder.result;
        this.code = builder.code;
        this.name = builder.name;
        this.message = builder.message;
        this.httpStatusCode = builder.httpStatusCode;
        this.debugInfoMap = builder.debugInfoMap;
        this.pagination = builder.pagination;
    }

    @Override
    public String toString() {
        return "Response{" +
                "result=" + result +
                ", pagination=" + pagination +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", httpStatusCode=" + httpStatusCode +
                '}';
    }

    @JsonIgnore
    public boolean is2xxSuccessful() {
        return (code != null && (code / 100 == 2) || httpStatusCode == null && code == null);
    }

    @JsonIgnore
    public boolean is4xxClientError() {
        return (code != null && (code / 100 == 4));
    }

    @JsonIgnore
    public boolean is5xxServerError() {
        return (code != null && (code / 100 == 5));
    }

    @JsonIgnore
    public boolean isError() {
        return is4xxClientError() || is5xxServerError();
    }
}
