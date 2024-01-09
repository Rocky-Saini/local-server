package com.digital.signage.constants;

/**
 * @author -Ravi Kumar created on 1/19/2023 9:57 AM
 * @project - Digital Sign-edge
 */
public interface UrlPaths {
    String PREFIX = "/";
    String RESET_PASSWORD = "/password";
    String FORGOT_PASSWORD = "/password-forgot";
    String API_VERSION = "/api-version";
    String SERVER_STATUS = "/server-status";
    String UNREGISTERED_DEVICE = "/api/unregistereDdevice";
    String PATH_DEVICE = "/device";
    String PATH_ONBOARDING = "/onboarding";
    String PATH_CUSTOMER = "/customer";
    String PATH_BRANDING = "/branding";
    String PATH_IMAGE = "/image";
    String PATH_TEXT = "/text";
    String PATH_SDN = "/sdn";
    String PATH_PDN = "/pdn";
    String PATH_QMS = "/qms/**";
    String PATH_CUSTOMER_ON_BOARDING = PATH_CUSTOMER + "/onboarding/";
    String PATH_LOGIN = "/login";
    String PATH_CUSTOMER_DEFAULT_IMAGE = PATH_CUSTOMER + "/files/";
    String PATH_CONTENT_FILES = "/files/";
    String PATH_FILE = "/file";
    String PATH_CONTENT_THUMB_FILES = "/files/thumb/";
    String PATH_DEFAULT_THUMBNAILS = "/thumb/default/";
    String OFFLINE_CONTENT_TEXT_FILE = "/offline-txt-file";
    String OFFLINE_CONTENT_FILE = "/offline-content-file";
    String PATH_APP_UPGRADE = "/app-upgrade";
    String PATH_NOTIFY = "/notify";
    String PATH_LOCAL_SERVER_APP_UPGRADE = "/app-upgrade/local-server";
    String PATH_DOWNLOAD = "/download";
    String PATH_DOWNLOAD_2 = "/download2";
    String PATH_ANGULAR_DOWNLOAD = "/angular/download";
    String PATH_ANALYTICS = "/analytics";
    String PATH_REPORTS = "/reports";
    String PATH_REPORTS_XLS = PATH_REPORTS + "/xls/";
    String PATH_PUSH_WEB_KEYS = "/push/web/keys";
    String PATH_SNAPSHOT_FILE = "/snapshot/file/";
    String PATH_LOG_FILE = "/device-logs/file/";
    String PATH_THUMBNAIL_FILE = "/thumbnail-file/";
    String PATH_CURRENT_MILLIS = "/currentMilliSeconds";
    String PATH_SERVER_TIME_ZONE = "/server-time";
    String PATH_TIME = "/time";
    String PATH_COUNTRY_CODES = "/country-codes";
    String PATH_WEB_NOTIFICATIONS = "/push/web/notifications";
    String PATH_WEB_NOTIFICATIONS_MARK_AS_READ = "/push/web/read";
    String PATH_WEB_NOTIFICATIONS_REGISTER = "/push/web";
    String PATH_CONTENT_TYPES = "/contentTypes";
    String PATH_WIDGET_TYPES = "/widgetTypes";
    String PATH_STATE = "/state";
    String PATH_USER_ACTIVITY_MODULES = "/user-activity/modules";
    String PATH_LOCALIZATION = "/localization";
    String PATH_WEB_APP = "/webapp";
    String PATH_CONTENT_LATEST_VERSION = "/content/version/";
    String PATH_CONTENT_LATEST_VERSION2 = "/content/version2/";
    String PATH_CUSTOMER_CONFIG = "/customer-config";
    String PATH_PANASONIC_CONFIG = "/pan-config";
    String PATH_PANEL_CONTROL = "/panel-control";
    String PATH_FACEBOOK_ACCESS_TOKEN = "/facebook/access-token";
    String PATH_STOP_ALL_EMAILS = "/stop-all-emails";
    String PATH_FIX_CONTENT_PLAYBACK_DATA = "/fix-content-playback-data";
    String PATH_DEVICE_SNAPSHOT_NOW = "/device-snapshots/now/";
    String PATH_DEVICE_SNAPSHOT_NOW_IMAGE = PATH_DEVICE_SNAPSHOT_NOW + "image/";
    String PATH_DEVICE_SNAPSHOT_NOW_THUMB = PATH_DEVICE_SNAPSHOT_NOW_IMAGE + "thumb/";
    String PATH_CUSTOMER_ID_FROM_MASK = PATH_CUSTOMER + "/id-from-mask";
    String PATH_ONPREMISES = "/premises";
    String PATH_ONPREMISES_CUSTOMER = PATH_ONPREMISES + "/customer";
    String PATH_JENKINS = "/jenkins";
    String PATH_ONPREMISES_UPDATE = "/status";
    String PATH_PREMISE_ID = "/{premiseId}";
    String PATH_PREMISE_UPDATE_JSON = "/json";
    String PATH_TEST_SES_EMAIL = "/email-test-ses";
    String PATH_TOKEN_PAIRING = "/token-pairing";
    String PATH_ONPREMISES_CUSTOMER_ID = PATH_ONPREMISES_CUSTOMER + PATH_PREMISE_ID + "/{customerId}";

    String PATH_ANGULAR = "/angular";
    String PATH_ADMIN = "/admin";
    String PATH_BUILD = "/build";
    String PATH_BUILD_VERSION = PATH_BUILD + "/version";
    String PATH_ONPREMISES_ID = "/id";
    String PATH_FILE_NAME = "/{file-name}";
    String PATH_PLANO_STOP = "/stop";
    String _PATH_PUSH_DEVICE = "push/device";
    String _PATH_PUSH_V2_DEVICE = "push/v2/device";
    String PATH_PUSH_DEVICE = "/" + _PATH_PUSH_DEVICE;
    String PATH_PUSH_V2_DEVICE = "/" + _PATH_PUSH_V2_DEVICE;
    String _PATH_PUSH_ACK = "push/acknowledge";
    String PATH_PUSH_ACK = "/" + _PATH_PUSH_ACK;
    String PATH_AD_ENABLED = "/advertisement/enabled";
    String PATH_ENVIRONMENT_PROPERTIES = "/environment-properties";
    String PATH_DEVICE_OS = "/deviceOs";
    String PATH_BUILD_OS = "/buildOs";
    String DATA_COLLECTION_CONFIG = "/dataCollection/config";
    String DATA_COLLECTION_CONFIG_ALL = "/dataCollection/config/all";
    String PATH_REPORTS_PDF = PATH_REPORTS + "/pdf/";
    String PATH_ADVANCE_SEARCH_REPORT = "/advance/search";
    String PATH_ADVANCE_SEARCH_REPORT_PDF = PATH_ADVANCE_SEARCH_REPORT + "/pdf/";
    String PATH_ADVANCE_SEARCH_REPORT_XLS = PATH_ADVANCE_SEARCH_REPORT + "/xls/";
    String PATH_BUILD_FILE = "build-file";
    String PATH_IFRAME_URL = "/iframe-url";
    String PATH_TPAPP_URL = "/tpapp";
    String PATH_TPAPP_VALIDATE = "/tpapp/validate";
    String PATH_TPAPP_SCHEMA = PATH_TPAPP_URL + "/schemas";
    String PATH_TPA_LOGIN = "/tpa/login";
    String PATH_TPA_SERVER = "/tpa/server";
    String PATH_TPA_BUILD_DOWNLOAD = "/tpa/build/download";
    String PATH_TPA_REPORT = "/tpa/reports";
    String PATH_TPA_VERSION = "/tpa/versions";
    String PATH_TPAPP_OS = "/tpapp/os";
    String PATH_TPA_VERSION_REPORTS = "/tpa-versions";
    String PATH_STOP_SERVER = "/shut-down";
    String PATH_LOCAL_SERVER_URL = "/localserver";
    String PATH_REQUEST_FETCH_FILE = "/file-download-request";
    String PATH_DOWNLOAD_REQUESTED_FILE = "/file-download";

    String PATH_DEFAULT_THUMBNAIL = "/default-thumbnail";
    String PATH_DATA_COLLECTION_CONFIG_V2 = "/dataCollection/v2/config";
    String PATH_DATA_COLLECTION_CONFIG_ALL_V2 = "/dataCollection/v2/config/all";
    String PATH_CUSTOMER_TIERS_REPORTS = "/customer-tiers-switch";
    String PATH_CUSTOMER_TIER_DASHBOARD = "/customer-tiers";
    String STOP_SERVER = "/stop-server";

    String PATH_DETAILS_SERVER = "/details/server";

    String PATH_WHO_AM_I = "/who-am-i";

    String PATH_SERVER_FILE = "/server-file";

    String PATH_FA = "/fa";

    String PATH_GENDER_REPORT = "/gender-report";

    String PATH_AGE_REPORT = "/age-report";

    String PATH_EMOTION_REPORT = "/emotion-report";

    String PATH_DWELL_GENDER_WISE_REPORT = "/dwell-gender-wise";

    String PATH_DWELL_AGE_WISE_REPORT = "/dwell-age-wise";

    String PATH_EMOTION = "/emotion";

    String PATH_AGE = "/age";

    String PATH_GENDER = "/gender";

    String PATH_GENDER_VIEWS = "/gender-views";

    String PATH_DEVICES_WITH_CAMERA = "/devices-with-camera";

    String PATH_EMOTION_GRAPH_REPORT = "/emotion-graph";

    String PATH_RAW_DATA_REPORT_PDF_XLS = "/raw-data";

    String PATH_CUSTOMER_SELF_REGISTRATION = "/customer/self-registration";

    String PATH_RUN_DELETE_SERVER_FILES_CRON = "/server-files-delete-cron";
    String PATH_SEND_LIC_EXP_EMAILS = "/send-lic-exp-emails";
    String PATH_API_THAT_TIMES_OUT = "/api-that-times-out";
    String PATH_DASHBOARD = "/dashboard";
    String PATH_BASIC_VS_ADVANCE = "/basic-vs-advance";
    String PATH_DASHBOARD_BASIC_VS_ADVANCE = PATH_DASHBOARD + PATH_BASIC_VS_ADVANCE;
    String PATH_BASIC_TO_ADVANCE_CONVERSION = "/basic-to-advance-conversion";
    String PATH_DASHBOARD_BASIC_TO_ADVANCE_CONVERSION =
            PATH_DASHBOARD + PATH_BASIC_TO_ADVANCE_CONVERSION;

    String PATH_ASPECT_CALCULATE = "/aspectRatio/calculate";

    String PATH_CAMERA = "/camera";

    String PATH_DEMOGRAPHY = "/demography";
    String PATH_AGE_ENUM = "/age-enum";
    String PATH_RULE_DEVICES = "/rule/devices";
    String PATH_CONTENT_PLAYBACK = "/content-playback";

    String PATH_CUSTOM = "/custom";
    String PATH_DEFAULT = "/default";

    String PATH_CUSTOMER_BRANDING_DEFAULT_FILE =
            PATH_CUSTOMER
                    + PATH_BRANDING
                    + PATH_IMAGE
                    + PATH_DEFAULT
                    + PATH_FILE;

    String PATH_CUSTOMER_BRANDING_DEFAULT_THUMBNAIL_FILE =
            PATH_CUSTOMER
                    + PATH_BRANDING
                    + PATH_IMAGE
                    + PATH_DEFAULT
                    + PATH_THUMBNAIL_FILE;

    String PATH_CUSTOMER_BRANDING_CUSTOM_FILE =
            PATH_CUSTOMER
                    + PATH_BRANDING
                    + PATH_IMAGE
                    + PATH_CUSTOM
                    + PATH_FILE;

    String PATH_CUSTOMER_BRANDING_CUSTOM_THUMBNAIL_FILE =
            PATH_CUSTOMER
                    + PATH_BRANDING
                    + PATH_IMAGE
                    + PATH_CUSTOM
                    + PATH_THUMBNAIL_FILE;

    String PATH_CUSTOMER_BRANDING_CUSTOM_IMAGE_FILE =
            PATH_CUSTOMER_BRANDING_CUSTOM_FILE
                    + "/" + PathParams.CUSTOMER_ID
                    + "/" + PathParams.CUSTOMER_BRANDING_LOGO_TYPE
                    + "/" + PathParams.LOGO_VERSION;

    String PATH_CUSTOMER_BRANDING_CUSTOM_IMAGE_THUMBNAIL_FILE =
            PATH_CUSTOMER_BRANDING_CUSTOM_THUMBNAIL_FILE
                    + PathParams.CUSTOMER_ID
                    + "/" + PathParams.CUSTOMER_BRANDING_LOGO_TYPE
                    + "/" + PathParams.LOGO_VERSION;

    String PATH_CUSTOMER_BRANDING_DEFAULT_IMAGE_FILE =
            PATH_CUSTOMER_BRANDING_DEFAULT_FILE
                    + "/" + PathParams.CUSTOMER_BRANDING_LOGO_TYPE;

    String PATH_CUSTOMER_BRANDING_DEFAULT_IMAGE_THUMBNAIL_FILE =
            PATH_CUSTOMER_BRANDING_DEFAULT_THUMBNAIL_FILE
                    + PathParams.CUSTOMER_BRANDING_LOGO_TYPE;
    String PATH_APP_UPGRADE_BUILDS_SIGNED_URL = "/builds-signed-url";
    String PATH_APP_UPGRADE_FAILURE_REASONS_URL = "/failure-reasons";

    String PATH_LAYOUT = "/layout";

    String PATH_DEMOGRAPHY_RULE = "/demography/rule";
    String PATH_DEMOGRAPHY_RULE_PUT_OR_GET = PATH_DEMOGRAPHY_RULE + "/{ruleId}";
    String PATH_DEMOGRAPHY_RULE_DEVICES = PATH_DEMOGRAPHY_RULE + "/devices";
    String PATH_RULE_ASSOCIATION = "/demography/rule/association";
    String PATH_RULE_ASSOCIATION_COPY = "/demography/rule/association/copy";
    String PATH_RULE_PRIORITIES = "/demography/rule/priorites";
    String PATH_RULE_LAYOUT = PATH_DEMOGRAPHY_RULE + PATH_LAYOUT + "/" + PathParams.LAYOUT_ID;
    String PATH_DEMOGRAPHY_LIB_VERSION = "/lib-version";

    String PATH_NON_LOGGED_IN = "/non-logged-in";

    interface QueryParams {
        String DEVICE_OS = "device-os";
        String ENCRYPTED_CODE = "encrypted-code";
        String PREMISE_ID = "premise-id";

        String VERSION = "version";
    }

    interface PathParams {
        String ENCRYPTED_ID = "{" + PathVariables.ENCRYPTED_ID + "}";
        String CUSTOMER_ID = "{" + PathVariables.CUSTOMER_ID + "}";
        String CUSTOMER_BRANDING_LOGO_TYPE = "{" + PathVariables.CUSTOMER_BRANDING_LOGO_TYPE + "}";
        String LOGO_VERSION = "{" + PathVariables.LOGO_VERSION + "}";
        String LAYOUT_ID = "{" + PathVariables.LAYOUT_ID + "}";
    }

    interface PathVariables {
        String ENCRYPTED_ID = "encryptedId";
        String CUSTOMER_ID = "customerId";
        String CUSTOMER_BRANDING_LOGO_TYPE = "customerBrandingLogoType";
        String LOGO_VERSION = "logoVersion";
        String LAYOUT_ID = "layoutId";
    }
}
