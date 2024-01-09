package com.digital.signage.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;

/**
 * @author -Ravi Kumar created on 12/28/2022 2:26 AM
 * @project - Digital Sign-edge
 * <p>
 * Helper to simplify accessing i18n messages in code.
 * <p>
 * This finds messages automatically found from src/main/resources (files named
 * messages_*.properties)
 * <p>
 * This example uses hard-coded English locale.
 */
@Component
public class Message {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    public void injectMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @PostConstruct
    public void init() {
        accessor = new MessageSourceAccessor(messageSource, new Locale("en", "US"));
    }

    public String get(String code) {
        return accessor.getMessage(code);
    }

    public String get(String key, Object... params) {
        try {
            return MessageFormat.format(accessor.getMessage(key), params);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    public interface ResponseMessages {
        String INVALID_REQUEST_PARAMETER = "response_messages.invalid_request_parameter";
        String INVALID_EMAIL_TOKEN_MESSAGE = "response_messages.invalid_email_token";
        String PASSWORD_ALREADY_SET = "response_messages.password_already_set";
        String EMAIL_TOKEN_EXPIRED_MESSAGE = "response_messages.email_token_expired";
        String PASSWORD_NOT_COMPLEX_ENOUGH_MESSAGE = "response_messages.password_not_complex_enough";
        String USER_DOES_NOT_EXIST_MESSAGE = "response_messages.user_not_exists";
        String USER_ROLE_UPDATED_MESSAGE = "response_messages.user_role_updated";
        String USER_CREATED_EMAIL_SENT_MESSAGE = "response_messages.user_created_email_sent";
        String RESET_PASSWORD_LINK_SENT_TO_EMAIL_MESSAGE = "response_messages.reset_password_link_sent";
        String COMMON_CANNOT_BE_NULL_OR_EMPTY = "common.cannot.be.null.or.empty";

        String ENTITY_NOT_FOUND = "response_messages.entity_not_found";
        String USER_INACTIVE_MESSAGE = "response_messages.poc_inactive_user";
        String USER_NOT_LINKED_TO_CUSTOMER_MESSAGE = "response_messages.user_not_linked_cust";
        String USER_LINKED_TO_ANOTHER_CUSTOMER_MESSAGE = "response_messages.user_linked_another_cust";
        String USER_LINKED_TO_ANOTHER_CUSTOMER_MESSAGE_BUT_CUSTOMER_CREATED =
                "response_messages.user_linked_another_cust.customomer_created_success";
        String EMAIL_NOT_REGISTERED = "response_messages.email_not_registered";
        String BAD_CREDENTIALS = "response_messages.bad_credentials";
        String UNKNOWN_ERROR = "response_messages.unknown_error";
        String PASSWD_UPDATE_FAILED_MSG = "response_messages.passwd_update_failed";
        String PASSWD_UPDATE_SUCCESSFUL_MSG = "response_messages.passwd_update_successful";
        String PASSWD_NOT_SET_MESSAGE = "response_messages.passwd_not_set";
        String INVALID_EMAIL_STRING_Message = "response_messages.invalid_email_string";
        String CANNOT_CREATE_USER_WITH_NO_RULES_MESSAGE =
                "response_messages.cannot_create_user_with_no_roles";
        String SUPER_USER_NOT_ASSIGNED_CUSTOMER_ROLES =
                "response_message.super_user_cannot_be_assigned_customer_roles";
        String NO_PRIVILEGES_TO_CREATE_SUPER_ADMIN =
                "response_message.no_privileges_to_create_super_admin_user";
        String EMAIL_ALREADY_TAKEN = "response_message.email_already_taken";

        String USER_CANNOT_CHANGE_SELF_STATUS = "response_message.cannot_change_self_status";
        String CUSTOMER_USER_CANNOT_BE_ASSIGNED_SUPER_ROLE =
                "response_message.customer.user.cannot.be.assigned.super.role";
        String INVALID_WORKFLOW_OPERATION = "response_message.invalid_workflow_operation";
        String ENTITY_NOT_SUBMITTED = "response_message.entity_not_submitted";
        String INVALID_APPROVER_LEVEL = "response_message.invalid_approver_level";
        String COMMENT_REMARKS_ARE_MANDATORY = "comment.remarks.are.mandatory";
        String COMMENT_REMARKS_LIMIT_VALIDATION = "comment.remarks.limit.validation";
        String USER_OR_CUSTOMER_IS_INACTIVE_MSG = "response.messages.user.or.customer.inactive";
        String USER_INACTIVE_CANNOT_SET_PASSWORD = "user.inactive.cannot.set.password";
        String CUSTOMER_INACTIVE_CANNOT_SET_PASSWORD = "customer.inactive.cannot.set.password";
        String EMAIL_EXISTS_IN_WRONG_FORMAT = "email.wrong.format";
        String DEVICE_PANEL_INCOMPLETE_RECORD_VALIDATION_ERROR = "device.panel.incomplete.record";
    }

    public interface Common {
        String NO_MESSAGE = "";
        String COMMON_FIELD_VALIDATION_MESSAGE = "common.field.validation.message";
        String COMMON_NOT_FOUND = "common.not.found";
        String COMMON_SUCCESSFULLY_MULTIPLE_SAVED =
                "common.successfully.multiple.saved";
        String COMMON_SUCCESSFULLY_SAVED_KEY = "common.successfully.saved.key";
        String COMMON_SUCCESSFULLY_DELETED_KEY = "common.successfully.Deleted.key";
        String COMMON_FETCH_SUCCUESS = "common.fetch.success";
        String COMMON_SAVE_MESSAGE = "common.save.message";
        String COMMON_UPDATE_MESSAGE = "common.update.successfully";
        String CUSTOMER_ID_MISSING_IN_HEADER = "customer.id.required.in.header";
        String LICENSE_CODE_IS_MISSING = "license.code.required";
        String COMMON_CANNOT_BE_NULL_OR_EMPTY = "common.cannot.be.null.or.empty";
        String COMMON_CANNOT_BE_NULL = "common.cannot.be.null";
        String COMMON_CANNOT_BE_DUPLICATE = "common.cannot.be.duplicate";
        String COMMON_INVALID_TOKEN = "common.invalid.token";
        String COMMON_CANNOT_BE_NULL_ERROR = "common.cannot.be.null.error";
        String COMMON_CANNOT_NULL_ERROR = "common.cannot.null.error";
        String COMMON_CAN_NOT_SMALLER_ERROR = "common.can.not.smaller.error";
        String COMMON_JSON_PARSING_ERROR = "generic.json.parsing.error.message";
        String COMMON_ALREADY_IN_USE = "common.is.already.in.use";
        String COMMON_HTTP_METHOD_NOT_SUPPORTED = "common.http.method.not.supported";
        String COMMON_DELETION_WARNING = "deletion.warning";
        String COMMON_INVALID_VALUE_FOR_KEY_ERROR = "common.invalid.value.for.key.error";
        String COMMON_ADMINS = "common.admins";
    }

    public interface JWT {
        String JWT_EXPIRED = "jwt.expired";
        String JWT_INVALID = "jwt.invalid";
    }

    public interface User {
        String USER_ID_NOT_FOUND = "user.id.not.found";
        String USER_ID_MISSING = "user.id.missing";
        String PANSONIC_USERS_LOCATION_RESTRICTION_ERROR = "user.location.restriction.panasonic";
        String CUSTOMER_ADMIN_USERS_LOCATION_RESTRICTION_ERROR =
                "user.location.restriction.customer.admin";
        String FORBIDDEN_USER = "user.cannot.access";
        String LOCATION_IDS_NOT_FOUND = "user.location.ids.not.found";
        String USER_CANNOT_DELETE_DEACTIVATE_ITSELF = "User.cannot.delete.deactivate.itself";
        String USER_STATUS_CHANGE_PASSWORD_NOT_SET_MSG = "user.status.change.password.not.set.message";
        String USER_STATUS_SHOULD_BE_NULL = "user.status.should.be.null";
        String USER_NOT_PAN_ADMIN = "user.not.panasonic.admin";
        String NON_CUSTREP_USER = "user.non.custrep.user";
        String USER_NOT_EXIST = "user.not.exists";
        String USER_PROVIDE_OLD_PASSWORD = "user.provide.old.password";
        String USER_PROVIDE_NEW_PASSWORD = "user.provide.new.password";
        String USER_OLD_PASSWORD_NOT_CORRECT = "user.old.password.not.correct";
        String USER_CURRENT_PASSWORD_NOT_CORRECT = "user.current.password.not.correct";
        String USER_PROVIDE_VALID_NEW_PASSWORD = "user.provide.valid.new.password";
        String CANNOT_ASSIGN_ADMIN_ROLES_LOCATION_RESTRICTED_USER =
                "cannot.assign.admin.roles.Location.restricted.user";
        String CANNOT_ASSIGN_LOCATION_RESTRICTION_TO_THIS_USER =
                "cannot.assign.location.restriction.to.this.user";

        String PANASONIC_USER_CANNOT_BE_MADE_POC = "user.pan.user.cannot.be.poc";
    }

    public interface Planogram {
        String PLANOGRAM_ID_REQUIRED = "planogram.id.required";
        String PLANOGRAM_NOT_EXISTS = "planogram.not.exists";
        String DEVICE_LOGIC_ID_NOT_REQUIRED = "device.id.not.required";
        String PLANOGRAM_ID_FOR_PRIORITY_NOT_FOUND = "planogram.priority.planogramId.not.found.message";
        String PAST_DATED_PLANOGRAM_FOUND = "planogram.pastdated.planogram.found";
        String PLANOGRAM_ID_PRIORITY_UNIQUENESS_MESSAGE = "planogramId.priority.uniqueness.message";
        String PLANOGRAM_ID_MISSING_MESSAGE_FOR_PRIORITY = "planogramId.missing.message.for.priority";
        String PLANOGRAM_ID_NULL_OR_EMPTY_CHECK = "planogramId.null.or.emptyc.checck.message";
        String CUSTOMER_DOES_NOT_HAVE_ANY_PLANOGRAMS = "customer.does.not.have.any.planograms";
        String NUMBER_OF_PLANOS_OF_CUST_AND_REQUEST_DO_NOT_MATCH =
                "number.of.planos.of.cust.and.request.do.not.match";
        String PLANOGRAM_ADD_PLANOGRAMNAME_EXIST = "planogram.add.planogramName.exist";
        String DUPLICATE_PLANO_IDS_IN_REQUEST = "duplicate.plano.ids.in.request";
        String PRIORITY_NOT_SET = "planogram.priority.not.set";
        String START_DATE_NOT_SET = "planogram.startdate.not.set";
        String END_DATE_NOT_SET = "planogram.enddate.not.set";
        String START_TIME_NOT_SET = "planogram.starttime.not.set";
        String END_TIME_NOT_SET = "planogram.endtime.not.set";
        String NO_LAYOUTS_IN_PLANOGRAM = "no.layouts.in.planogram";
        String NO_DEVICE_OR_LOCATION_LOGIC_IN_PLANOGRAM = "no.device.location.in.planogram";
        String CANNOT_STOPPED_NON_PUBLISHED_PLANOGRAM = "cannot.stopped.published.planogram";
        String PLANOGRAM_START_DATE_BEFORE_CURRENT_DATE_ERROR =
                "planogram.start.date.before.current.date.error";
        String PLANOGRAM_LICENCE_EXPIRY_FUTURE_ERROR = "planogram.licence.expiry.future.error";
        String PLANOGRAM_LICENCE_EXPIRY_TODAY_ERROR = "planogram.licence.expiry.today.error";
        String PLANOGRAM_AND_LOGIC_MISMATCH = "planogram.logic.mismatch";
    }

    public interface Location {
        String LOCATION_HIERARCHY_EXCEEDED = "location.hierarchy.exceeded";
        String LOCATION_HIERARCHY_LESS_THAN_LEVEL = "location.hierarchy.less.than.level";
        String LOCATION_IDS_ERROR = "location.id.doesnt.exist";
        String LOCATION_BASIC_CUSTOMER_ERROR = "location.basic.customer.error";
    }

    public interface UnregisteredDevice {
        String DEVICE_KEY_ALREADY_IN_USE = "device.key.already.in.use";
        String DEVICE_KEY_ALREADY_IN_USED = "device.key.already.in.useed";
        String DEVICE_KEY_ALREADY_IN_USED_WITH_ANOTHER_LICENSE = "device.key.already.in.used.with.another.license.code";
        String DEVICE_KEY_ALREADY_WAITING_TO_ADD = "device.key.already.waiting.to.add";
        String DEVICE_KEY_ALREADY_WAITING_TO_ADD_WITH_ANOTHER_LICENSE = "device.key.already.waiting.to.add.with.another.license.code";
        String UNREGISTERED_DEVICE_NOT_FOUND = "unregistered.device.not.found";
        String UNREGISTERED_DEVICE_ERROR_BASIC_TIER_ERROR =
                "unregistered.device.error.basic.tier.error";
    }

    public interface DeviceConsumable {
        String FROM_DATE_REQUIRED = "from.date.required";
    }

    public interface PanelStatus {
        String SAME_TIME_OF_STATUS = "same.time.of.status";
        String TIME_STAMP_ALREADY_EXIST = "time.stamp.already.exist";
        String MAX_PANEL_STATUS_DATA_SIZE_EXCEEDED_ERROR = "max.panel.status.data.size.exceeded.error";
    }

    public interface CustomerConfig {
        String PANCONFIG_NOT_FOUND_MESSAGE = "customer.config.not.found.message";
        String FIREBASE_WPTTLS_NULL = "firebaseWPTTLS.is.null";
        String CUSTOMER_CONFIG_UPDATED_SUCCESSFULLY = "customer.config.update.success";
        String CUSTOMER_CONFIG_PLANOGRAM_PUBLISH_TIME_ERROR_IN_HOURS =
                "customer.config.planogram.publish.time.error.in.hours";
    }

    public interface StatsReport {
        String MORE_THAN_X_DAYS_MESSAGE = "stats.report.email.only.x.days.message";
        String MORE_THAN_X_DAYS_AND_MORE_THAN_ONE_DEVICE_MESSAGE =
                "stats.report.email.only.x.days.and.more.devices.message";
        String MORE_THAN_ONE_DEVICE_MESSAGE = "stats.report.email.only.more.devices.message";

        String REPORT_DATA_IS_EMPTY = "report data is empty";

    }

    public interface DevicePanelReport {
        String MORE_THAN_LIMIT_DATA_MESSAGE = "panel.report.email.more.data.message";
        String DEVICE_TOTAL_ON_STRING = "device.report.totalOnString";
        String DEVICE_TOTAL_OFF_STRING = "device.report.totalOffString";
        String DEVICE_TOTAL_WEEK_OFF_STRING = "device.report.totalWeekOffString";
        String DEVICE_TOTAL_NOT_APP_STRING = "device.report.totalNotApplString";
        String DEVICE_TOTAL_NOT_AVAIL_STRING = "device.report.totalNotAvailString";
        String DEVICE_TOTAL_DATA_DELETED_STRING = "device.report.totalDataDeletedString";
        String DEVICE_TOTAL_IN_ACTIVE_STRING = "device.report.totalInActiveString";
        String DEVICE_TOTAL_DISCONNECTED_STRING = "device.report.totalDisconnected";
    }

    public interface Onpreimises {
        String ON_PREMISES_BASE_URL_EXISTS = "on.premises.base.url.exists";
        String ON_PREMISES_NON_EDITABLE_ERROR = "on.premises.non.editable.error";
        String ON_PREMISES_ON_BOARDED_CHANGE_ERROR = "on.premises.on.boarded.change.error";
        String ON_PREMISES_NON_NUMERIC_ERROR = "on.premises.non.numeric.error";
    }

    public interface DeviceBusinessConfig {
        String BUSINESS_ON_TIME_NOT_EXISTS = "business.on.time.not.exists";
        String BUSINESS_OFF_TIME_NOT_EXISTS = "business.off.time.not.exists";
        String BUSINESS_CONFIG_DATE_NOT_EXISTS = "business.config.date.not.exists";
    }

    public interface ValidationMessages {
        String MAX = "validation.messages.max.error";
        String MIN = "validation.messages.min.error";
        String NULL_ERROR = "validation.messages.null.error";
        String MALFORMED_URL_ERROR = "validation.messages.malformed.url.error";
    }

    public interface IFrameMessage {
        String INVALID_IFRAME_URL = "invalid.iFrame.url";
        String HTTPS_IFRAME_URL = "https.iFrame.url";
        String INVALID_IFRAME_URL_ID = "invalid.iFrame.url.Id";
        String INVALID_IFRAME_ORDER = "invalid.iFrame.order";
        String DUPLICATE_IFRAME_ORDER = "duplicate.iFrame.order";
    }

    public interface TpAppMessage {
        String INVALID_TP_APP_ID = "invalid.tp.app.id";
        String INVALID_TP_APP_NAME = "invalid.tp.app.name";
        String UNIQUE_TP_APP_NAME_ERROR = "unique.tp.app.name.error";
        String INVALID_TP_APP_DP_SCHEMA = "invalid.tp.app.schema";
        String VALIDATION_ERROR_TP_APP_DP_SCHEMA = "validation.error.tp.app.schema";
        String INVALID_TP_WINDOW_DIR = "invalid.tp.app.window.dir";
        String INVALID_TP_ANDROID_ID = "invalid.tp.app.android.id";
        String INVALID_TP_APP_OS = "invalid.tp.app.os";
        String INVALID_CUSTOMER_ANDROID_ID = "invalid.customer.android.id";
        String INVALID_CUSTOMER_WINDOWS = "invalid.customer.window.dir";
        String USED_TP_APP_SCHEMA_DELETE = "invalid.tp.app.schema.delete";

        String EMPTY_ERROR_WINDOWS_ANDROID = "empty.error.android.window";
        String UNIQUE_ERROR_TP_APP_DP_SCHEMA = "unique.error.tp.app.schema";

        String DEVICE_IDS_EMPTY = "tpa.builds.device.ids.empty";
        String INVALID_DEVICE_IDS = "tpa.builds.invalid.device.ids";
        String BUILD_DOWNLOAD_URL_EMPTY = "tpa.builds.download.url.empty";
        String INVALID_BUILD_DOWNLOAD_URL = "tpa.builds.download.url.invalid";
        String INVALID_TPAPP_ID = "tpa.builds.invalid.tpapp.id";
        String INVALID_DEVICE_OS = "tpa.builds.invalid.device.os";
        String DEVICE_OS_MISMATCH = "tpa.builds.device.os.mismatch";
        String INVALID_UPDATE_TYPE = "tpa.builds.invalid.update.type";
        String INVALID_MD5 = "tpa.builds.invalid.md5";
        String INVALID_TPAPP_VERSION = "tpa.builds.invalid.tpapp.version";
        String TPAPP_NOT_FOUND = "tpa.builds.tpapp.not.found";

        String TPAPP_SERVER_ADD_REQUIRED = "tpa.server.add.required.fields";
        String TPAPP_SERVER_KEY_NOT_COMPLEX_ENOUGH = "tpa.server.key.not.complex.enough";

        String TPA_USED_IN_TPA_SERVER = "tpa.used.in.tpa.server.warning";
        String TPA_USED_IN_DEVICE_CONFIG = "tpa.used.in.device.config.warning";
        String TPA_USED_IN_TPA_SERVER_AND_DEVICE_CONFIG =
                "tpa.used.in.tpa.server.and.device.config.warning";
        String TPA_DELETE_VALIDATION_SUCCESS = "tpa.delete.validation.success";
        String TPA_CANNOT_CHANGE_SCHEMA = "tpa.cannot.change.schema";
    }

    public interface PairToken {
        String RANDOM_ID_NULL_OR_EMPTY = "random.id.invalid";
        String RANDOM_ID_NOT_FOUND = "random.id.not.found";
        String USER_AUTH_LOGGED_OUT_ERROR = "user.logged.out.error";
    }

    public interface OfflineDownload {
        String OFFLINE_FILE_NOT_FOUND = "offline.file.not.found";
    }

    public interface ContentDownloadFailedEmail {
        String CONTENT_DOWNLOAD_FAILED_EMAIL_START_STRING = "content.download.failed.email.start";
        String CONTENT_DOWNLOAD_FAILED_EMAIL_START_STRING_FOR_CUST_REP =
                "content.download.failed.email.start.for.cust.rep";
        String TABLE_COLUMN_MEDIA_PLAYER_NAME = "content.download.failed.email.media.player.name";
        String TABLE_COLUMN_CONTENT_NAME = "content.download.failed.email.content.name";
        String TABLE_COLUMN_REASON_FOR_FAILURE = "content.download.failed.email.reason.for.failure";
        String TABLE_COLUMN_TIME_OF_FAILURE = "content.download.failed.email.time.of.failure";
    }

    public interface PhoneNumber {
        String COUNTRY_CODE_OR_PHONE_NUMBER_IS_INVALID = "country.code.or.phone.number.is.invalid";
    }

    public interface CustomerBranding {
        String BRANDING_CUSTOMER_ID_REQUIRED = "branding.customer.id.required";
        String BRANDING_REQUEST_OBJECT_REQUIRED = "branding.request.object.required";
        String BRANDING_CANNOT_SET_TO_CUSTOM_IMAGE = "branding.cannot.set.to.custom.image";
        String BRANDING_SMTP_HOST_REQUIRED = "branding.smtp.host.required";
        String BRANDING_SMTP_PORT_REQUIRED = "branding.smtp.port.required";
        String BRANDING_SMTP_PORT_RANGE_ERROR = "branding.smtp.port.range.error";
        String BRANDING_SMTP_SENDER_EMAIL_REQUIRED = "branding.smtp.sender.email.address.required";
        String BRANDING_SMTP_RECEIVER_EMAIL_REQUIRED = "branding.smtp.receiver.email.address.required";
        String BRANDING_SMTP_USE_TLS_REQUIRED = "branding.smtp.use.tls.required";
        String BRANDING_CANNOT_DELETE_BRANDING_URL_AS_LOGIN_LOGOS_ARE_PRESENT = "branding.cannot.delete.url.as.login.logos.are.present";
        String BRANDING_URL_PROTOCOL_NOT_SPECIFIED = "branding.url.protocol.not.specified";
        String BRANDING_URL_ALREADY_IN_USE = "branding.url.already.in.use";
        String BRANDING_URL_PROTOCOL_SHOULD_BE_HTTPS = "branding.url.protocol.should.be.https";
        String BRANDING_URL_PROTOCOL_SHOULD_BE_HTTP = "branding.url.protocol.should.be.http";
        String BRANDING_URL_SHOULD_NOT_BE_LOCALHOST = "branding.url.should.not.be.localhost";
        String BRANDING_URL_CANNOT_BE_SAME_AS_THE_SERVER_OR_ANGULAR_URL = "branding.url.cannot.be.same.as.the.server.or.angular.url";
        String BRANDING_URL_REQUIRED_FOR_LOGIN_LOGOS = "branding.url.required.for.login.logos";
        String BRANDING_LOGO_HEIGHT_MISMATCH = "branding.logo.height.mismatch";
        String BRANDING_LOGO_WIDTH_MISMATCH = "branding.logo.width.mismatch";
        String BRANDING_CUSTOMER_AD_PLATFORM_URL_REQUIRED = "branding.customer.ad.platform.required";
        String BRANDING_CUSTOMER_NAME_REQUIRED = "branding.customer.name.required";
        String BRANDING_VALID_PAGE_TITLE_REQUIRED = "branding.valid.page.title.required";
        String BRANDING_VALID_FOOTER_TEXT_REQUIRED = "branding.valid.footer.text.required";
        String BRANDING_VALID_SMTP_HOST_REQUIRED = "branding.valid.smtp.host.required";
        String BRANDING_VALID_SMTP_PASSWORD_REQUIRED = "branding.valid.smtp.password.required";
        String BRANDING_SMTP_PASSWORD_DECRYPTION_FAIL = "branding.smtp.password.decryption.fail";
        String BRANDING_VALID_SMTP_SENDER_EMAIL_REQUIRED = "branding.valid.smtp.sender.email.required";
        String BRANDING_VALID_SDN_AD_PLATFORM_URL_REQUIRED = "branding.valid.sdn.ad.platform.url.required";
        String BRANDING_VALID_SDN_NAME_OF_CUSTOMER_REQUIRED = "branding.valid.sdn.name.of.customer.required";
        String BRANDING_SDN_URL_SHOULD_NOT_BE_LOCALHOST = "branding.sdn.url.should.not.be.localhost";
        String BRANDING_SDN_URL_CANNOT_BE_SAME_AS_DEFAULT_URL = "branding.sdn.url.cannot.be.same.as.default.url";
        String BRANDING_SDN_URL_PROTOCOL_NOT_SPECIFIED = "branding.sdn.url.protocol.not.specified";
        String BRANDING_INVALID_IMAGE_FORMAT_ERROR = "branding.invalid.image.format.error";
        String BRANDING_CUSTOMER_NOT_FOUND = "branding.customer.not.found";
        String BRANDING_LOGO_NOT_FOUND = "branding.invalid.version.not.found";
    }

    public static class DeviceConfig {
        public static final String DEVICECONFIG_FETCH_SUCCESS = "deviceconfig.fetch.success";

        public static final String DEVICECONFIG_FETCH_NOT_FOUND = "deviceconfig.fetch.notfound";

        public static final String DEVICECONFIG_FETCH_ERROR = "deviceconfig.fetch.error";

        public static final String DEVICECONFIG_CREATE_SUCCESS = "deviceconfig.create.success";

        public static final String DEVICECONFIG_DEVICE_NULL_ERROR = "deviceconfig.device.null.error";

        public static final String DEVICECONFIG_DEVICE_CUSTOMER_MISMATCH_ERROR =
                "deviceconfig.device.customer.mismatch.error";

        public static final String DEVICECONFIG_REQUEST_NOT_FOUND_ERROR =
                "deviceconfig.request.not.found.error";

        public static final String DEVICECONFIG_PANEL_TIME_MISMATCH_ERROR =
                "deviceconfig.panel.time.mismatch.error";

        public static final String DEVICECONFIG_PANEL_TIMES_SAME_ERROR =
                "deviceconfig.panel.times.same.error";

        public static final String DEVICECONFIG_BUSSINESS_TIME_MISMATCH_ERROR =
                "deviceconfig.bussiness.times.mismatch.error";

        public static final String DEVICECONFIG_BUSSINESS_TIMES_SAME_ERROR =
                "deviceconfig.bussiness.times.same.error";

        public static final String DEVICECONFIG_PANEL_BUSSINESS_TIME_MISMATCH_ERROR =
                "deviceconfig.panel.bussiness.time.mismatch.error";

        public static final String DEVICECONFIG_GLOBAL_CONFIG_MISSING_CUSTID =
                "deviceconfig.globalconfig.nullCustId.error";

        public static final String DEVICECONFIG_PANELON_BUSSINESON_TIME_MISMATCH_ERROR =
                "deviceconfig.panelon.bussinesson.time.mismatch.error";

        public static final String DEVICECONFIG_PANELOFF_BUSSINESOFF_TIME_MISMATCH_ERROR =
                "deviceconfig.paneloff.bussinessoff.time.mismatch.error";

        public static final String DEVICECONFIG_SCREEN_CAPTURE_INTERVAL_TIME_ERROR =
                "deviceconfig.screen.capture.interval.time.error";

        public static final String DEVICECONFIG_ERROR_UPDATE_INTERVAL_TIME_ERROR =
                "deviceconfig.error.update.interval.time.error";

        public static final String DEVICECONFIG_DISPLAY_LOG_INTERVAL_TIME_ERROR =
                "deviceconfig.display.log.interval.time.error";

        public static final String DEVICECONFIG_LOG_UPLOAD_INTERVAL_TIME_ERROR =
                "deviceconfig.log.upload.interval.time.error";

        public static final String DEVICECONFIG_PLANOGRAM_SYNC_INTERVAL_INMINUTES_ERROR =
                "deviceconfig.planogram.sync.interval.time.error";

        public static final String DEVICECONFIG_PANEL_STATUS_UPDATE_INTERVAL_INSEC_ERROR =
                "deviceconfig.panel.status.update.interval.insec.error";

        public static final String DEVICECONFIG_SNAPSHOT_UPDATE_INTERVAL_INSEC_ERROR =
                "deviceconfig.snapshot.update.interval.insec.error";

        public static final String TOUCHSCREEN_UPDATE_INTERVAL_INSEC_ERROR =
                "deviceconfig.touchscreen.update.interval.insec.error";

        public static final String DATACOLLECTION_UPDATE_INTERVAL_INSEC_ERROR =
                "deviceconfig.datacollection.update.interval.insec.error";

        public static final String CONTENTPLAYBACK_UPDATE_INTERVAL_INSEC_ERROR =
                "contentplayback.update.interval.insec.error";

        public static final String DEVICECONFIG_PANEL_ON_OFF_TIME_NULL_ERROR =
                "deviceconfig.panel.onoff.null.error";

        public static final String DEVICECONFIG_BUSSINESS_TIMES_NULL_ERROR =
                "deviceconfig.bussiness.times.null.error";

        public static final String DEVICECONFIG_NEGATIVE_VALUE_AND_ZERO_NOT_ALLOWED_SEC =
                "deviceconfig.negative.value.and.zero.not.allowed.insec";

        public static final String DEVICECONFIG_BUSSINESS_TIMES_GAP_LESS_ERROR =
                "deviceconfig.bussiness.times.gap.less.error";

        public static final String RESET_DEVICE_CONFIG = "reset.device.config";

        public static final String CUSTOMER_LEVEL_DEVICE_CONFIG_ALREADY_PRESENT =
                "customer.level.device.config.already.present";
    }

    public static class Customer {
        public static final String CUSTOMER_CREATE_SUCCESS = "customer.create.success";

        public static final String CUSTOMER_CREATE_VALIDATION_NAME = "customer.create.validation.name";

        public static final String PAN_NUMBER_VALIDATION = "pan.number.validation";

        public static final String CUSTOMER_CREATE_VALIDATION_NAME_TOO_LONG =
                "customer.create.validation.name.too.long";

        public static final String CUSTOMER_CREATE_VALIDATION_NAME_REQ =
                "customer.create.validation.name.required";

        public static final String CUSTOMER_CREATE_NAME_EXIST = "customer.create.name.exist";

        public static final String POC_CREATE_NAME_EXIST = "poc.create.name.exist";

        public static final String POC_CREATE_EMAIL_EXIST = "poc.create.email.exist";

        public static final String CUSTOMER_UPDATE_CUSTOMER_ID_EXIST =
                "customer.update.customerId.exist";

        public static final String CUSTOMER_UPDATE_DEVICE_COUNT = "customer.update.device.count";

        public static final String CUSTOMER_ADD_DEVICE_COUNT = "customer.add.device.count";

        public static final String CUSTOMER_DELETE_SUCCESS = "customer.delete.success";

        public static final String CUSTOMER_NOT_FOUND_MESSAGE = "customer.not.found.message";

        public static final String CUSTOMER_STATUS_IS_REQUIRED = "customer.status.is.required";

        public static final String CUSTOMER_STATUS_IS_NOT_VALID = "customer.status.is.not.valid";

        public static final String CUSTOMER_IDS_ARE_REQUIRED = "customer.ids.are.required";

        public static final String CUSTOMER_NUMBER_APPOVER_LEVEL = "customer.number.of.approver.level";

        public static final String CAN_NOT_UPADTE_CUSTOMER_LICENCE = "can.not.update.customer.licence";

        public static final String CAN_NOT_UPDATE_CUSTOMER_LICENCE_INFORMATION =
                "can.not.update.customer.licence.information";

        public static final String CUSTOMER_CREATE_VALIDATION_NO_OF_DEVICE_CANNOT_NAGATIVE_INEGER =
                "no.of.devices.can.not.nagative.integer";

        public static final String CAN_NOT_UPDATE_CUSTOMER_NAME = "can.not.update.customer.name";

        public static final String CAN_NOT_UPDATE_CUSTOMER_NAME_INFORMATION =
                "can.not.update.customer.name.information";

        public static final String CUST_USER_CANNOT_MODIFY_LICENCE_START_DATE =
                "cust.user.cannot.modify.lic.start.date";

        public static final String CUSTOMER_CREATE_VALIDATION_INVALID_PHONENUMBER =
                "can.not.enter.invalid.phonenumber";

        public static final String CUST_USER_CANNOT_MODIFY_LICENCE_END_DATE =
                "cust.user.cannot.modify.lic.end.date";

        public static final String CUST_USER_CANNOT_MODIFY_NUMBER_OF_DEVICES =
                "cust.user.cannot.modify.num.of.devices";

        public static final String CUSTOMER_LIC_START_DATE_REQUIRED
                = "customer.lic.start.required";

        public static final String CUSTOMER_LIC_END_DATE_REQUIRED
                = "customer.lic.end.required";

        public static final String CUSTOMER_LIC_START_AND_END_DATE_CANNOT_BE_SAME
                = "customer.lic.start.and.end.date.cannot.be.same";

        public static final String CUSTOMER_LIC_END_DATE_CANNOT_BE_OF_PAST_OR_TODAY
                = "customer.lic.end.date.cannot.be.of.past.or.today";

        public static final String CUSTOMER_LIC_END_DATE_CANNOT_BE_LESS_THAN_START_DATE
                = "customer.lic.end.date.cannot.be.less.than.start.date";

        public static final String CUST_USER_CANNOT_MODIFY_STATUS =
                "cust.user.cannot.modify.status";

        public static final String CUST_DETAILS_MISSING = "cust.details.missing";

        public static final String CUST_CANNOT_SET_WORKFLOW_AFTER_CUSTOMER_IS_ONBOARDED =
                "cust.Cannot.set.workflow.after.customer.onboarded";

        public static final String BASIC_CUST_CANNOT_SET_WORKFLOW_EXCEPT_ON_PLANOGRAM =
                "basic.cust.cannot.set.workflow.except.on.planogram";

        public static final String CUST_APPROVER_LEVEL_AND_WORKFLOW_MISMATCHED =
                "cust.approver.level.and.workflow.mismatched";

        public static final String CUST_APPROVER_LEVEL_NOT_SET_YET = "cust.approver.level.not.set.yet";

        public static final String CUST_APPROVER_WORKFLOW_NOT_SET_YET =
                "cust.approver.Workflow.not.set.yet";

        public static final String NUMBER_OF_DEVICES_SHOULD_BE_GREATER_THAN_ZERO =
                "no.of.devices.should.be.greater.than.zero";

        public static final String CUST_STATUS_CHANGE_WHEN_LICENSE_EXPIRED =
                "cust.status.change.when.license.expired";

        public static final String NUMBER_OF_DEVICES_SHOULD_BE_GREATER_THAN_CONSUMED_DEVICES =
                "no.of.devices.should.be.greater.than.consumed.devices";

        public static final String CANNOT_CREATE_POC_ALREADY_ACTIVE_CUSTOMER_ADMIN_EXISTS =
                "cannot.create.poc.already.active.customer.admin.exists";

        public static final String NUMBER_OF_DEVICES_SHOULD_BE_LESS_THAN_MAX =
                "no.of.devices.should.be.less.than.max";

        public static final String CANNOT_CREATE_POC_ALREADY_ACTIVE_CUSTOMER_ADMIN_EXISTS_USER =
                "cannot.create.poc.already.active.customer.admin.exists.user";

        public static final String CUSTOMER_ID_INVALID = "customer.id.invalid";

        public static final String USER_IS_SAME = "user.is.same";

        public static final String INVALID_PHONE_NUMBER = "invalid.phone.number";

        public static final String INVALID_COUNTRY_CODE = "invalid.country.code";

        public static final String BOTH_COUNTRY_CODE_AND_PHONE_NUMBER_SHOULD_BE_PRESENT =
                "country.code.phone.number.not.present";

        public static final String NO_OF_DEVICE_RANGE_ERROR = "no.of.devices.range.should.be";

        public static final String PAN_NUMBER_VALIDATION_ERROR = "customer.pan.validation.error";

        public static final String PAN_NUMBER_CANNOT_BE_DELETED = "customer.pan.cannot.be.deleted";

        public static final String PAN_NUMBER_FORMAT_VALIDATION_ERROR =
                "customer.pan.format.validation.error";

        public static final String CUSTOMER_LOGO_FILE_REQUIRED = "customer.logo.file.required";

        public static final String BRANDING_ITEM_TO_DELETE_REQUIRED =
                "customer.branding.item.to.delete.required";

        public static final String CUSTOMER_ALREADY_ONBOARDED = "customer.already.onboarded";
        public static final String CUSTOMER_ID_MISSING = "branding.customer.id.required";
        public static final String INVALID_CUSTOMER = "branding.customer.id.invalid";
        public static final String CUSTOMER_ACTIVATE_FAIL_LICENCE_EXPIRED =
                "customer.activite.fail.license.expired";
    }

    public static class Device {
        public static final String DEVICE_ADD_SUCCESS = "device.add.success";

        public static final String DEVICE_ADD_DEVICEKEY_EXIST = "device.add.deviceKey.exist";

        public static final String DEVICE_ADD_DEVICEKEY_EXIST_WITH_ANOTHER_LICENSE_KEY = "device.add.deviceKey.exist.with.another.license.key";

        public static final String DEVICE_ADD_DEVICENAME_EXIST = "device.add.deviceName.exist";

        public static final String DEVICE_ADD_DEVICE_WAITING_TO_ADDED =
                "device.add.device.waiting.added";

        public static final String DEVICE_ADD_LOCATION_NOTEXIST = "device.add.location.notExist";

        public static final String DEVICE_ADD_LOCATION_NOT_LEAF = "device.add.location.notLeaf";

        public static final String DEVICE_ADD_DEVICEGROUP_NOTEXIST = "device.add.deviceGroup.notExist";

        public static final String DEVICE_ADD_ALL_DEVICE_CONSUMED = "device.add.all.device.consumed";

        public static final String DEVICE_ADD_LICENSED_EXPIRED = "device.add.licensed.expired";

        public static final String DEVICE_OS_INVALID_FOR_BASIC_CUSTOMER =
                "device.os.invalid.for.basic.customer";

        public static final String DEVICE_CANNOT_BE_NULL = "device.cannot.be.null";

        public static final String DEVICE_INTERNET_SPEED_NOT_PROVIDED =
                "device.internet.speed.not.provided";

        public static final String DEVICE_UPDATE_SUCCESS = "device.update.success";

        public static final String DEVICE_UPDATE_DEVICEID_NOTEXIST = "device.update.deviceId.notExist";

        public static final String DEVICE_FETCH_SUCCESS = "device.fetch.success";

        public static final String DEVICE_FETCH_SUCCESS_NORMAL = "device.fetch.success.normal";

        public static final String DEVICE_FETCH_NOT_FOUND = "device.fetch.notfound";

        public static final String DEVICE_FETCH_ALL_NOT_FOUND = "device.fetch.All.notfound";

        public static final String DEVICE_STATUS_UPDATE_SUCCESS = "device.status.update.success";
        public static final String DEVICE_STATUS_UPDATE = "device.status.update";
        public static final String DEVICE_REPLACE_SUCCESS = "device.replace.success";

        public static final String DEVICE_ADD_SUCCESS_LOCAL_SERVER_IP =
                "device.add.success.local.server.ip";

        public static final String DEVICE_INVALID_DATES = "device.invalid.date.range";

        public static final String DEVICE_ERROR_EMPTY_ERROR = "device.errors.empty";

        public static final String DEVICE_ERROR_MAX = "device.errors.errorcode.max";

        public static final String DEVICE_ANALYTICS_EMPTY = "device.analytics.empty";

        public static final String DEVICE_EVENT_NOT_IN_PROGRESS = "device.event.not.in.progress";

        public static final String DEVICE_DATA_NOT_FOUND = "device.data.not.found";

        public static final String DEVICE_BATCH_NOT_FOUND = "device.batch.not.found";

        public static final String DEVICE_CUSTOMER_MAPPING_NOT_FOUND =
                "device.customer.mapping.not.found";

        public static final String DEVICE_PUSH_SENT_CONNECTED_MEDIA_PLAYER =
                "device.push.sent.connected.media.player";

        public static final String DEVICE_CANNOT_ADD_LINUX_AS_IT_IS_NOT_ENABLED =
                "device.cannot.add.linux.as.it.is.not.enabled";
    }

    public static class Content {
        public static final String CONTENT_CREATE_SUCCESS = "content.create.success";

        public static final String CONTENT_CREATE_FAIL = "content.create.fail";

        public static final String CONTENT_FETCH_SUCCESS = "content.fetch.success";

        public static final String CONTENT_FETCH_FAIL = "content.fetch.fail";

        public static final String CONTENT_FETCH_ERROR = "content.fetch.error";

        public static final String CONTENT_UPDATE_SUCCESS = "content.update.success";

        public static final String CONTENT_UPDATE_FAIL = "content.update.fail";

        public static final String CONTENT_DELETE_SUCCESS = "content.delete.success";

        public static final String CONTENT_DELETE_FAIL = "content.delete.fail";

        public static final String CONTENT_CONTENT_TYPE_MISMATCH = "content.type.mismatch";

        public static final String CONTENT_MARQUEE_DIRECTION_IS_REQUIRED =
                "content.marquee.direction.is.required";

        public static final String CONTENT_BACKGROUND_IMAGE_CONTENT_ID_NOT_FOUND =
                "content.background.image.id.not.found";

        public static final String CONTENT_ID_NOT_FOUND = "content.id.not.found";

        public static final String CONTENT_TYPE_NOT_IMAGE = "content.type.not.image";

        public static final String CONTENT_TYPE_NOT_AUDIO = "content.type.not.audio";

        public static final String CONTENT_CONTENT_NAME_TOO_LONG = "content.name.too.long";

        public static final String CONTENT_NAME_ALREADY_IN_USED = "content.name.already.in.used";

        public static final String CONTENT_NOT_DOWNLABLE = "content.not.downlable";

        public static final String CONTENT_TYPE_NOT_PROCESSED_ENCRYPTED =
                "content.type.not.processed.encrypted";

        public static final String CONTENT_TYPE_NOT_ENCRYPTED = "content.type.not.encrypted";

        public static final String CONTENT_TYPE_NOT_SUPPORTED_FOR_BASIC_CUST =
                "content.type.not.supported.for.basic.cust";

        public static final String SCROLLING_SPEED_SET_WHEN_MARQUEE_IS_NONE =
                "scrolling.speed.set.when.marquee.is.none";
    }

    public static class DataCollection {
        public static final String DATA_COLLECTION_ADD_SUCCESS = "data.collection.add.success";
        public static final String DATA_COLLECTION_TIME_OF_STATUS_ERROR =
                "data.collection.timeOfStatus.error";
        public static final String DATA_COLLECTION_CANNOT_BE_NULL_OR_EMPTY_ERROR =
                "data.collection.cannot.be.null.or.empty.error";
        public static final String DATA_COLLECTION_INVALID_DATE_FORMAT_ERROR =
                "data.collection.invalid.date.format.error";
        public static final String MAX_DATA_COLLECTION_SIZE_EXCEEDED_ERROR = "max.data.collection.size.exceeded.error";
    }

    public static class Panel {
        public static final String PANEL_NAME_EXISTS = "panel.add.name.exists";

        public static final String PANEL_NAME_EMPTY = "panel.name.empty";

        public static final String PANEL_IP_EMPTY = "panel.ip.empty";

        public static final String PANEL_DEVICE_NOT_FOUND = "panel.device.not.found";

        public static final String PANEL_ID_NOT_REQUIRED = "panel.id.not.required";

        public static final String PANEL_NOT_EXISTS = "panel.not.exists";

        public static final String PANEL_ID_REQUIRED = "panel.id.required";

        public static final String PANEL_UPDATE_SUCCESS = "panel.update.success";

        public static final String PANEL_ADD_SUCCESS = "panel.add.success";

        public static final String PANEL_ASPECT_RATIO_NOT_FOUND = "panel.aspect.ratio.not.found";

        public static final String PANEL_DEVICE_ALREADY_LINKED = "panel.device.already.linked";

        public static final String RECORDS_SAVED_SUCCESSFULLY = "records.saved.successfully";

        public static final String PANEL_DEFAULT_MORE_THEN_ONE =
                "panel.verification.default.more.then.one";

        public static final String PANEL_VALIDATION_ALL_DATA_GOOD = "panel.verification.all.data.good";
    }

    public static class RoleCreationValidator {
        public static final String ROLE_NAME_SPACE_ERROR = "role.name.space.error";

        public static final String ROLE_NAME_EMPTY_ERROR = "role.name.empty.error";

        public static final String ROLE_RESOURCE_NAME_SPACE_ERROR = "role.resource.name.space.error";

        public static final String ROLE_RESOURCE_NAME_EMPTY_ERROR = "role.resource.name.empty.error";

        public static final String ROLE_ACTION_EMPTY_ERROR = "role.action.empty.error";

        public static final String ROLE_SAME_AS_SYSTEM_ROLE_ERROR = "role.name.same.as.system.role";

        public static final String ROLE_APPROVE_NOT_ALLOW_ERROR = "role.approve.not.allow.error";

        public static final String ROLE_CUSTOMER_NOT_SAME_ERROR = "role.customer.not.same.error";
    }

    public static class Role {
        public static final String ROLE_NOT_EXIST = "role.id.not.exist";

        public static final String ROLE_ID_NULL = "role.id.null";

        public static final String ROLE_UPDATE_SUCCESS = "role.update.success";

        public static final String ROLE_PRIVLEGES_UPDATE_SUCESS = "role.privleges.update.success";

        public static final String ROLES_NOT_EXISTS = "roles.not.exists";

        public static final String GET_ROLES_SUCCESS = "get.roles.success";

        public static final String RESOURCE_ACTION_PAIR_NOT_MATCH = "resource.action.pair.not.match";

        public static final String APPROVE_PART_OF_ACTIONS =
                "roles.resource.action.approve.part.of.actions";

        public static final String ROLE_ID_DOES_NOT_EXIST = "Role.id.does.not.exist.new";

        public static final String ROLE_DELETE_SUCCESS_MESSAGE = "role.delete.success.message";

        public static final String ROLE_DELETE_ASSOCIATION_VALIDATION_KEY =
                "role.delete.association.validation.key";

        public static final String ROLE_DELETE_ASSOCIATION_VALIDATION_MESSAGE =
                "role.delete.association.validation.message";

        public static final String ROLE_ROLENAME_DUPLICATE_ERROR = "role.rolename.duplicate.error";

        public static final String ROLE_ACCESS_DENIED_ERROR = "role.access.denied.error";

        public static final String ROLE_NON_APPROVER_CONVERSION_ERROR =
                "role.non.approver.conversion.error";
    }

    public static class Logs {
        public static final String LOGS_FILE_NOT_SELECTED_ERROR = "logs.file.not.selected.error";

        public static final String LOGS_FILE_UPLOAD_SUCCESS = "logs.file.upload.success";

        public static final String LOGS_FILE_UPLOAD_FAILURE = "logs.file.upload.failure";

        public static final String LOGS_FIELDS_EMPTY_ERROR = "logs.fields.empty.error";

        public static final String LOGS_URL_FOUND_SUCCESS = "logs.url.found.success";

        public static final String LOGS_FILE_NOT_FOUND_ERROR = "logs.file.not.found.error";

        public static final String LOGS_TIME_MISMATCH_ERROR = "logs.time.mismatch.error";

        public static final String LOGS_TIME_SAME_ERROR = "logs.time.same.error";
    }

    public static class Template {
        public static final String TEMPLATE_ADD_TEMPLATENAME_EXIST = "template.add.templateName.exist";

        public static final String TEMPLATE_ADD_SUCCESS = "template.add.success";

        public static final String TEMPLATE_REGIONS_OVERLAP = "template.regions.overlap";

        public static final String TEMPLATE_STATE_ERROR = "template.state.error";

        public static final String TEMPLATE_NOT_FOUND = "template.not.found";

        public static final String TEMPLATE_ID_ERROR = "template.id.doesnt.exist";

        public static final String TEMPLATE_UPDATE_SUCCESS = "template.update.success";

        public static final String TEMPLATE_IDS_ARE_REQUIRED = "template.ids.are.required";

        public static final String AT_LEAST_ONE_SEARCH_PARAMS_REQUIRED =
                "at.least.one.search.params.required";

        public static final String TEMPLATE_NAME_NOT_FOUND = "template.name.not.found";

        public static final String TEMPLATE_IN_DRAFT_MODE = "template.in.draft";

        public static final String TEMPLATE_CREATED_BY_NOT_FOUND = "template.created.by.not.found";

        public static final String TRANSPARENCY_PERCENTAGE = "transparancy.percentage";
    }

    public static class LayoutTag {
        public static final String LAYOUT_TAG_IDS_ERROR = "layout.tag.id.doesnt.exist";

        public static final String LAYOUT_TAG_TITLE_NOT_FOUND = "layout.tag.title.not.found";
    }

    public static class AspectRatio {
        public static final String ASPECT_RATIO_ID_ERROR = "aspect.ratio.id.doesnt.exist";
        public static final String ASPECT_RATIO_WIDTH_HEIGHT =
                "aspect.ratio.width.height.cannot.be.null.zero";
        public static final String ASPECT_RATIO_INVALID_ERROR = "aspect.ratio.invalid.error";
        public static final String ASPECT_RATIO_DUPLICATE_ERROR = "aspect.ratio.duplicate.error";
        public static final String ASPECT_RATIO_CALCULATION_ERROR = "aspect.ratio.calculation.error";
        public static final String ASPECT_RATIO_EDITABLE_ERROR = "aspect.ratio.editable.error";
        public static final String ASPECT_RATIO_EDITABLE_DELETE_ERROR =
                "aspect.ratio.editable.delete.error";
        public static final String ASPECT_RATIO_DEFAULT_DELETE_ERROR =
                "aspect.ratio.default.delete.error";
        public static final String ASPECT_RATIO_MIN_MAX_ERROR =
                "aspect.ratio.min.max.error";
    }

    public static class TemplateRegion {
        public static final String TEMPLATE_REGION_IDS_ERROR = "template.region.id.doesnt.exist";

        public static final String TEMPLATE_REGION_IDS_ARE_REQUIRED
                = "template.region.ids.are.required";
    }

    public static class DeviceGroup {

        public static final String DEVICE_GROUP_ID_NOT_REQUIRED = "device.group.id.not.required";

        public static final String DEVICE_GROUP_NAME_EMPTY = "device.group.name.empty";

        public static final String DEVICE_GROUP_NAME_EXISTS = "device.group.name.exists";

        public static final String DEVICE_GROUP_ADD_SUCCESS = "device.group.add.success";

        public static final String DEVICE_GROUP_ID_REQUIRED = "device.group.is.required";

        public static final String DEVICE_GROUP_NOT_EXISTS = "device.group.not.exists";

        public static final String DEVICE_GROUP_UPDATE_SUCCESS = "device.group.update.success";

        public static final String DEVICE_GROUP_DELETE_SUCCESS = "device.group.delete.success";

        public static final String DEVICE_GROUP_LINK_SUCCESS = "device.group.link.success";

        public static final String DEVICE_GROUP_DEVICE_CUST_MISMATCH =
                "device.group.link.cust.mismatch";

        public static final String DEVICE_GROUP_FETCH_SUCCESS_NORMAL =
                "device.group.fetch.success.normal";

        public static final String DEVICE_GROUP_FETCH_ALL_NOT_FOUND = "device.group.fetch.All.notfound";

        public static final String DEVICE_GROUP_CANNOT_DELETE_AS_DEVICES_ATTACHED
                = "device.group.cannot.delete.attached.to.it";
    }

    public static class Layout {
        public static final String LAYOUT_ADD_SUCCESS = "layout.add.success";

        public static final String LAYOUT_UPDATE_SUCCESS = "layout.update.success";

        public static final String LAYOUT_ID_ERROR = "layout.id.doesnt.exist";

        public static final String AT_LEAST_ONE_SEARCH_PARAMS_REQUIRED =
                "at.least.one.search.params.required";

        public static final String LAYOUT_NAME_NOT_FOUND = "layout.name.not.found";

        public static final String LAYOUT_CREATED_BY_NOT_FOUND = "layout.created.by.not.found";

        public static final String LAYOUT_PLAYLIST_CANNOT_SAME_ORDER =
                "layout.playlist.cannot.same.order";

        public static final String LAYOUT_IDS_ARE_REQUIRED = "layout.ids.are.required";

        public static final String LAYOUT_IDS_IN_USE = "layout.ids.in.use";

        public static final String LAYOUT_CANNOT_MODIFY = "layout.cannot.modify";

        public static final String LAYOUT_ADD_LAYOUTNAME_EXIST = "layout.add.layoutName.exist";

        public static final String CAMPAIGN_ALREADY_IN_USED_MSG = "campaign.already.in.used.message";

        public static final String LAYOUT_ADVERTISEMENT_UPDATE_ERROR
                = "layout.advertisement.update.error";

        public static final String LAYOUT_ADVERTISEMENT_DELETE_ERROR
                = "layout.advertisement.delete.error";

        public static final String LAYOUT_ADVERTISEMENT_LAYOUT = "layout.not.advertisement";

        public static final String ADVERTISEMENT_LAYOUT_DISABLE_ERROR =
                "advertisement.layout.disable.error";

        public static final String ADVERTISE_LAYOUT_STATE_CHANGE_ERROR =
                "advertise.layout.state.change.error";

        public static final String ADVERTISE_LAYOUT_ASPECT_RATIO_ERROR =
                "advertise.layout.aspect.ratio.error";

        public static final String ADVERTISE_LAYOUT_DUPLICATE_NAME_ERROR =
                "advertise.layout.duplicate.name.error";

        public static final String ADVERTISE_LAYOUT_MAX_DURATION_ERROR =
                "advertise.layout.max.duration.error";

        public static final String CANNOT_EDIT_LAYOUT_AS_IN_USE_IN_RA =
                "layout.cannot.edit.as.in.use.in.ra";

        public static final String CANNOT_MOVE_LAYOUT_TO_DRAFT_STATE_AS_IN_USE_IN_RA =
                "layout.cannot.move.to.draft.state.as.in.use.in.ra";
    }

    public static class LayoutRegion {
        public static final String LAYOUT_REGION_IDS_ERROR = "layout.region.id.doesnt.exist";

        public static final String LAYOUT_REGION_IDS_ARE_REQUIRED
                = "layout.region.ids.are.required";

        public static final String LAYOUT_REGION_AUDIO_ENABLED_ERROR =
                "layout.region.audio.enabled.error";
    }

    public static class LayoutRegionContentSchedule {
        public static final String REGION_PLAYLIST_CANNOT_SAME_ORDER =
                "region.playlist.cannot.same.order";

        public static final String REGION_DURATION_CANNOT_BE_ZERO =
                "region.duration.cannot.be.zero";
    }

    public static class Widget {
        public static final String WIDGET_TYPE_ERROR = "widget.type.doesnt.exist";
    }

    public static class LayoutAudioPlaylists {
        public static final String LAYOUT_AUDIO_PLAYLIST_ID_ERROR =
                "layout.audio.playlist.id.doesnt.exist";
    }

    public static class Animation {
        public static final String ANIMATION_ENTRY_ID_NOT_EXIST = "animation.entry.id.not.exists";

        public static final String ANIMATION_EXIT_ID_NOT_EXIST = "animation.exit.id.not.exists";
    }

    public static class SnapShot {
        public static final String SNAPSHOT_FILE_UPLOADED_SUCCESS = "snapshot.file.uploaded.success";

        public static final String SNAPSHOT_FILE_NOT_FOUND_ERROR = "snapshot.file.not.found.error";

        public static final String SNAPSHOT_FILE_NOT_VALID = "snapshot.file.not.valid";

        public static final String SNAPSHOT_ZIP_ENTRY_MISMATCH_ERROR =
                "snapshot.zip.entry.mismatch.error";

        public static final String SNAPSHOT_FILE_DOWNLOAD_SUCCESS = "snapshot.file.download.success";

        public static final String SNAPSHOT_FILE_FORMAAT_ERROR = "snapshot.file.format.error";

        public static final String SNAPSHOT_FILE_SNAPSHOT_TIME_ERROR =
                "snapshot.file.snapshot.time.error";

        public static final String SNAPSHOTS_ARRAY_EMPTY = "snapshot.array.empty";
    }

    public static class LayoutString {
        public static final String LAYOUT_ASPECT_RATIO_MISMATCH = "layout.aspect.ratio.mismatch";

        public static final String LAYOUT_ASPECT_RATIO_MISMATCH_MESSAGE =
                "layout.aspect.ratio.mismatch.message";

        public static final String LAYOUT_ID_NOT_FOUND_MESSAGE = "layout.id.not.found.message";

        public static final String DRAFT_LAYOUT_NOT_ALLOWED = "draft.layout.not.allowed";

        public static final String DRAFT_LAYOUT_NOT_ALLOWED_MESSAGE =
                "draft.layout.not.allowed.message";

        public static final String LAYOUT_ID_NOT_FOUND = "layout.id.not.found";

        public static final String LAYOUT_STRING_NOT_FOUND = "layout.string.not.found";

        public static final String ASPECT_RATIO_ID_NOT_FOUND = "aspect.ratio.id.not.found";

        public static final String ASPECT_RATIO_ID_NOT_FOUND_MESSAGE =
                "aspect.ratio.id.not.found.message";

        public static final String ASPECT_RATIO_ID_IS_REQUIRED = "aspect.ratio.id.is.required";

        public static final String LAYOUT_MUST_REQUIRED = "layout.must.required";

        public static final String LAYOUT_STRING_ID_REQUIRED = "layout.string.id.required";

        public static final String LAYOUT_STRING_NAME_DUPLICATE_MESSAGE =
                "layoutString.name.duplicate.message";

        public static final String LAYOUT_STRING_NAME_EMPTY_MESSAGE = "layoutString.name.empty.message";

        public static final String DISPLAY_ORDER_DUPLICATE_MESSAGE = "displayOrder.duplicate.message";

        public static final String DISPLAY_ORDER_RANGE_VALIDATION_MESSAGE =
                "displayOrder.range.validation.message";

        public static final String NUMBER_OF_LOOPS_RANGE_VALIDATION_MESSAGE =
                "number.of.loops.range.validation.message";

        public static final String LAYOUT_EMPTY_VALIDATION_MESSAGE = "layout.empty.validation.message";

        public static final String LAYOUT_STRING_IDS_IN_USE = "layout.string.ids.in.use";
    }

    public static class Push {
        public static final String PUSH_DEVICE_REGISTRATION_UPDATE_KEY =
                "push.device.registration.update.key";

        public static final String PUSH_DEVICE_REGISTRATION_UPDATE_MESSAGE =
                "push.device.registration.update.message";

        public static final String PUSH_REGISTRATION_NOTIFICATION_WEB_MESSAGE_NOT =
                "push.registration.notification.web.message.not";

        public static final String PUSH_UPGRADE_API_NOTIFICATION_SENT_TO_FOUND_DEVICES_SUCCESSFULLY =
                "push.upgrade.api.notification.sent.successfully";

        public static final String NO_DEVICE_FOUND_TO_SEND_PUSH_NOTIFICATION =
                "no.device.found.to.send.push.notification";
    }

    public static class DeviceSnapshot {
        public static final String DEVICE_SNAPSHOT_PUSH_CURRENT = "device.push.current.snapshot";

        public static final String DEVICE_SNAPSHOT_UPLOAD_SUCCESS = "device.snapshot.upload.success";

        public static final String DEVICE_SNAPSHOT_ERROR_UPLOAD_SUCCESS =
                "device.snapshot.upload.error.success";

        public static final String DEVICE_UNIQUE_REQUEST_MISMATCH_ERROR =
                "device.unique.request.mismatch";
    }

    public interface ContentPlayback {
        String CONTENT_PLAYBACK_NULL_VALIDATION_FOR_WIDGET_ADVERTISEMENT_OR_TP_APP_PLAY =
                "content.playback.null.validation.for.widget.advertisement.or.tp.app.play";
        String CONTENT_PLAYBACK_NULL_VALIDATION_FOR_WIDGET_EXCEPT_ADVERTISEMENT =
                "content.playback.null.validation.for.widget.except.advertisement";
        String CONTENT_PLAYBACK_NULL_VALIDATION_FOR_PLANOGRAM =
                "content.playback.null.validation.for.planogram";
        String CONTENT_PLAYBACK_NULL_REGION_FOR_NON_AUDIO =
                "content.playback.only.audio.can.have.region.null";
        String CONTENT_PLAYBACK_TPA_PLAYED_ERROR =
                "content.playback.tpa.played.error";
        String CONTENT_PLAYBACK_START_TIME_AFTER_OR_SAME_AS_END_TIME =
                "content.playback.start.time.after.or.same.as.end.time";
        String CONTENT_PLAYBACK_ERROR_REASON_EMPTY = "content.playback.error.reason.for.failure.empty";
        String CONTENT_PLAYBACK_ERROR_EMPTY = "content.playback.error.empty";
        String CONTENT_PLAYBACK_START_TIME_OR_END_TIME_NULL =
                "content.playback.start.time.or.end.time.null";
    }

    public interface Camera {
        String CAMERA_IS_NULL = "camera.cannot.be.null";
        String CAMERA_NOT_FOUND = "camera.not.found";
        String CAMERA_AND_DEVICE_MISMATCH = "camera.device.mismatch";
        String CAMERA_TYPE_EMPTY = "camera.type.empty";
        String CAMERA_TYPE_USB_HAVING_IP_ERROR = "camera.type.usb.having.ip.error";
        String CAMERA_TYPE_IP_HAVING_NO_IP_ERROR = "camera.type.ip.having.no.ip.error";
        String CAMERA_TYPE_INVALID = "camera.type.invalid";
        String CAMERA_ADDED = "camera.added";
        String CAMERA_BOTH_CAMERA_ID_AND_DEVICE_ID_MISSING =
                "camera.both.camera.id.and.device.id.missing";
        String CAMERA_REQUEST_IS_NULL = "camera.request.cannot.be.null";
        String CAMERA_DELETED = "camera.deleted";
        String CAMERA_PURPOSE_EMPTY = "camera.purpose.empty";
    }

    public interface Demography {
        String DEMOGRAPHY_NOT_ENABLED_CANNOT_PERFORM_OPERATION =
                "demography.not.enabled.cannot.perform.operation";
        String DEMOGRAPHY_REPORT_EITHER_FROM_OR_TO_TIME_IS_NULL =
                "demography.report.either.from.or.to.time.is.null";
        String DEMOGRAPHY_REPORT_FROM_TIME_IS_AFTER_TO_TIME =
                "demography.report.from.time.after.two.time";
        String DEMOGRAPHY_REPORT_FROM_TIME_IS_EQUAL_TO_TIME =
                "demography.report.from.time.is.equal.to.two.time";
        String DEMOGRAPHY_REPORT_FROM_TIME_INVALID =
                "demography.report.from.time.invalid";
        String DEMOGRAPHY_REPORT_TO_TIME_INVALID =
                "demography.report.to.time.invalid";
        String DEMOGRAPHY_REPORT_SELECTION_CRITERIA_RESULTS_IN_NO_DEVICES =
                "demography.report.selection.criteria.results.in.no.devices";
        String DEMOGRAPHY_REPORT_AGE_ENUM_VALUE_INCORRECT =
                "demography.report.age.enum.value.incorrect";
        String DEMOGRAPHY_REQUIRED_UNIQUE_LOCATION =
                "demography.location.should.be.unique";
        String DEMOGRAPHY_LOCATION_NOT_FOUND =
                "demography.location.not.found";
    }

    public interface GraphReport {
        String INVALID_DEVICE_ONE_ERROR = "invalid.device.one.error";
    }

    public interface DemographyRule {
        String RULE_ID_INVALID = "digital.signage.models.ruleModel.ruleId.is.invalid";
        String RULE_NAME_INVALID = "digital.signage.models.ruleModel.ruleName.is.invalid";
        String EMOTION_INVALID = "digital.signage.models.ruleModel.emotion.is.invalid";
        String AGEBAND_INVALID = "digital.signage.models.ruleModel.ageBand.is.invalid";
        String GENDER_INVALID = "digital.signage.models.ruleModel.gender.is.invalid";
        String RULE_NAME_ALREADY_EXIST = "digital.signage.models.ruleModel.ruleName.is.already.exist";
        String REQUIRED_AT_LEAST_ONE =
                "digital.signage.models.ruleModel.gender_or_ageBand_or_emotion.is.invalid";
        String RULE_NOT_FOUND = "digital.signage.models.ruleModel.rule.notfound";
        String RULE_DELETED = "digital.signage.models.ruleModel.rule.deleted";
    }

    public interface DemographyRuleAssociation {
        String REQUIRED_KEY = "digital.signage.dto.RuleAssociationRequestDTO.key.is.required";
        String REQUIRED_DEVICE_ID =
                "digital.signage.dto.RuleAssociationRequestDTO.deviceId.is.required";
        String REQUIRED_DEVICE_GROUP =
                "digital.signage.dto.RuleAssociationRequestDTO.deviceGroupId.is.required";
        String REQUIRED_DEVICEGROUP_WITH_LOCATION =
                "digital.signage.dto.RuleAssociationRequestDTO.locationWithDeviceGroup.is.required";
        String REQUIRED_LOCATION =
                "digital.signage.dto.RuleAssociationRequestDTO.locationId.is.required";
        String REQUIRED_RULEASSOCIATION_ID =
                "digital.signage.dto.RuleAssociationModel.ruleAssociationId.is.invalid";
        String REQUIRED_DEVICE_GROUP_WITH_LOCATION =
                "digital.signage.dto.RuleAssociationRequestDTO.locationWithDeviceGroup.is.required";
        String REQUIRED_RULE_ASSOCIATION_ID =
                "digital.signage.dto.RuleAssociationModel.ruleAssociationId.is.invalid";
        String REQUIRED_LAYOUT_ID = "digital.signage.dto.RuleAssociationModel.layout.is.invalid";
        String REQUIRED_NUMBEROFLOOP_ID =
                "digital.signage.dto.RuleAssociationModel.numberOfLoops.is.invalid";
        String REQUIRED_NUMBER_OF_LOOP_ID =
                "digital.signage.dto.RuleAssociationModel.numberOfLoops.is.invalid";
        String DEVICE_NOT_FOUND_ID = "digital.signage.dto.RuleAssociationModel.deviceId.not.found";
        String LAYOUT_NOT_FOUND_ID = "digital.signage.dto.RuleAssociationModel.layoutId.not.found";
        String DEVICE_GROUP_ID_NOT_FOUND =
                "digital.signage.dto.RuleAssociationModel.deviceGroupId.is.invalid";
        String LOCATION_NOT_FOUND =
                "digital.signage.dto.RuleAssociationRequestDTO.locationId.not.found";
        String REQUIRED_UNIQUE_DEVICE_IDS =
                "digital.signage.dto.RuleAssociationRequestDTO.deviceIds.is.unique";
        String REQUIRED_UNIQUE_DEVICE_GROUP =
                "digital.signage.dto.RuleAssociationRequestDTO.deviceGroupIds.is.unique";
        String REQUIRED_UNIQUE_LOCATION =
                "digital.signage.dto.RuleAssociationRequestDTO.locationIds.is.unique";
        String REQUIRED_NONNULL_DEVICE_IDS =
                "digital.signage.dto.RuleAssociationRequestDTO.deviceIds.is.nonnull";
        String REQUIRED_NONNULL_DEVICE_GROUP =
                "digital.signage.dto.RuleAssociationRequestDTO.deviceGroupIds.is.nonnull";
        String REQUIRED_NONNULL_LOCATION =
                "digital.signage.dto.RuleAssociationRequestDTO.locationIds.is.nonnull";
        String RULE_START_AND_END_TIME_CANNOT_BE_SAME =
                "digital.signage.dto.RuleAssociationRequestDTO.startTime.and.endTime.cannot.be.same";
        String RULE_ALREADY_ASSOCIATED_AT_TIME =
                "digital.signage.dto.RuleAssociationRequestDTO.rule.already.associated.time";
        String RULE_WEEKDAYS_INVALID =
                "digital.signage.dto.RuleAssociationRequestDTO.weekDays.not.lying.between.date";
        String RULE_START_OR_END_DATE_CANNOT_BE_NULL =
                "digital.signage.dto.RuleAssociationRequestDTO.startDate.and.endDate.cannot.be.null";
        String REQUIRED_RULE_ASSOCIATION_IDS =
                "digital.signage.dto.RuleAssociationLayoutRequestDTO.ruleAssociationIds.cannot.be.null";
        String REQUIRED_UNIQUE_RULE_ASSOCIATION_IDS =
                "digital.signage.dto.RuleAssociationLayoutRequestDTO.ruleAssociationIds.unique.id";
        String RULE_ASSOCIATION_ID_NOT_FOUND =
                "digital.signage.dto.RuleAssociationLayoutRequestDTO.ruleAssociationIds.not.found";
        String OLD_LAYOUTID_AND_NEW_LAYOUTID_CANT_BE_SAME =
                "digital.signage.dto.RuleAssociationLayoutRequestDTO.ruleAssociationIds.old.layoutId.and.newLayoutId.cannot.be.same";
        String INVALID_WEEK_DAYS =
                "digital.signage.dto.RuleAssociationLayoutRequestDTO.ruleAssociation.invalid.weekdays";
        String ONLY_APPROVED_AND_PUBLISHED_LAYOUTS_ALLOWED =
                "digital.signage.dto.RuleAssociationLayoutRequestDTO.only.approved.and.published.layouts.are.allowed";
        String ONLY_SUBMITTED_AND_PUBLISHED_LAYOUTS_ALLOWED =
                "digital.signage.dto.RuleAssociationLayoutRequestDTO.only.submitted.and.published.layouts.are.allowed";
        String INVALID_DEVICE_IDS_OR_CAMERA =
                "digital.signage.dto.RuleAssociationLayoutRequestDTO.invalid.deviceId.or.camera";
        String REQUIRE_CAMERA_ENABLED_DEVICE_IDS =
                "digital.signage.dto.RuleAssociationLayoutRequestDTO.require.camera.enabled.deviceIds";
        String REQUIRE_AT_LEAST_ONE_ASSOCIATION =
                "digital.signage.dto.RuleAssociationLayoutRequestDTO.at.least.one.association";
        String RULE_COPY_ASSOCIATION_FROM_OR_COPY_ASSOCIATION_TO_CANNOT_BE_NULL =
                "digital.signage.dto.RuleAssociationCopyDTO.ruleIdToCopyAssociationsFrom.and.ruleIdToCopyAssociationsTo.is.required";
        String RULE_ASSOCIATION_NOT_EMPTY = "role.association.not.empty";
    }

    public interface DemographyRawReport {
        String REPORT_REQUEST_NOT_ACCEPTED_ERROR_MSG = "report.not.accepted.report.error.msg";
    }

    public interface DemographyRulePriorities {
        String RULE_DEVICE_ID_AND_DEVICE_GROUP_ID =
                "digital.signage.dto.RulePrioritiesRequestDTO.deviceId.and.deviceGroupId.is.invalid";
        String RULE_PRIORITIES =
                "digital.signage.dto.RulePrioritiesRequestDTO.priorities.is.not.unique";
        String RULE_PRIORITIES_NOT_NULL =
                "digital.signage.dto.RulePrioritiesRequestDTO.priorities.should.not.null";
        String RULE_PROPERTIES = "digital.signage.dto.RulePrioritiesRequestDTO.properties.is.invalid";
        String RULE_IDS = "digital.signage.dto.RulePrioritiesRequestDTO.ruleIds.is.invalid";
        String RULE_ID_UNIQUE = "digital.signage.dto.RulePrioritiesRequestDTO.ruleIds.should.be.unique";
        String RULE_ASSOCIATION_NOT_FOUND =
                "digital.signage.dto.RulePrioritiesRequestDTO.ruleIds.ruleAssociation.not.found";
        String RULE_DEVICE_GROUP_INVALID_REQUEST =
                "digital.signage.dto.RulePrioritiesRequestDTO.ruleAssociation.ruleDeviceGroupId.invalid";
        String RULE_GLOBAL_INVALID_REQUEST =
                "digital.signage.dto.RulePrioritiesRequestDTO.ruleAssociation.global.invalid";
        String RULE_DEVICE_INVALID_REQUEST =
                "digital.signage.dto.RulePrioritiesRequestDTO.ruleAssociation.deviceId.invalid";
        String RULE_DEVICE_INVALID_QUERY_PARAM =
                "digital.signage.dto.RulePrioritiesRequestDTO.invalid.deviceId.in.queryparam";
        String RULE_DEVICE_GROUP_INVALID_QUERY_PARAM =
                "digital.signage.dto.RulePrioritiesRequestDTO.invalid.deviceGroupId.in.queryparam";
        String RULE_ID_NOT_FOUND =
                "digital.signage.dto.RulePrioritiesRequestDTO.ruleId.not.found";
    }

    public interface DeviceSpaceAnalytics {
        String AVAILABLE_SPACE_ON_DEVICE_IN_BYTES_VALIDATION_ERROR =
                "available.space.on.device.in.bytes.validation.error";
        String TOTAL_SPACE_ON_DEVICE_IN_BYTES_VALIDATION_ERROR =
                "total.space.on.device.in.bytes.validation.error";
        String TOTAL_SPACE_NOT_LESS_THAN_AVAILABLE_VALIDATION_ERROR =
                "total.space.not.less.than.available.validation.error";
    }

    public interface CustomerTiers {
        String FEATURE_NOT_SUPPORTED_FOR_BASIC_CUSTOMER =
                "customer.tiers.feature.not.supported.for.basic.customer";
    }

    public interface OnPremToMainServer {
        String ADD_CUSTOMER_MAIN_SERVER_NOT_CONNECTED = "add.customer.main.server.not.connected";
        String EDIT_CUSTOMER_MAIN_SERVER_NOT_CONNECTED = "edit.customer.main.server.not.connected";
    }

    public interface MediaPlayerSummaryReport {
        String MEDIA_PLAYER_SUMMARY_REPORT_TITLE_WITH_LOCATION = "media.player.summary.report.title.with.location";
        String MEDIA_PLAYER_SUMMARY_REPORT_TITLE = "media.player.summary.report.title";
    }
}
