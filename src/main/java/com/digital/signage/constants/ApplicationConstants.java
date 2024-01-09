package com.digital.signage.constants;






import com.digital.signage.util.BuildOs;
import com.digital.signage.util.DeviceOs;
import com.digital.signage.util.MediaType;
import com.digital.signage.util.TpaOs;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;

public class ApplicationConstants {
  public static final String SLUG_ID = "slugId";

  public static final String EN = "en";
  public static final String TENANT_IDENTIFIER = "TENANT_IDENTIFIER";

  public static final String HEADER_TENANT_ID = "X-Tenant-Id";

  public static final List<String> ALL_SPREADSHEET_EXTENSIONS = Arrays.asList(".xls", ".xlsx");

  public static final String LOCAL_CONTEXT = "/lsm";

  public static final String DEFAULT_TENANT = "tenant_default";
  public static final boolean DEVICE_HIT_LOGS = true;

  public static final String ALPHA_NUMERIC_WITH_SPACE_REGEX = "^[a-zA-Z0-9\\s]+$";

  public static final String TIME24HOURS_PATTERN =
      "^(?:(?:([01]?\\d|2[0-3]):)?([0-5]?\\d):)?([0-5]?\\d)$";

  public static final String PAN_NUM_REGEX = "[A-Z]{5}[0-9]{4}[A-Z]{1}";

  public static final Integer STATUS_ACTIVE = 1;

  public static final Integer STATUS_INACTIVE = 2;

  public static final int PRIVACY_FLAG_OFF = 0;

  public static final int PRIVACY_FLAG_ON = 1;

  public static final int STATUS_DELETED = 3;

  public static final String UPLOADED_FOLDER = "/etc/upload/";

  public static final String ROLES_DELIMITER = ",";

  public static final int DWELL_TIME_DIVISION = 10;

  /**
   * FIXED ROLES
   */
  public static final String ROLE_PANASONIC_ADMIN = "PANASONIC_ADMIN";

  public static final String ROLE_PANASONIC_CUST_REP = "PANASONIC_CUST_REP";

  public static final String ROLE_CUSTOMER_ADMIN = "CUSTOMER_ADMIN";

  public static final String ROLE_VIEW_ONLY = "VIEW_ONLY";

  public static final String ROLE_MAKER = "MAKER";

  public static final String ROLE_APPROVER = "APPROVER";

  public static final String ROLE_PDN_SERVER = "PDN_SERVER";

  public static final int MAX_ERROR_CODE = 9;

  public static final int MINUTES_FOR_CHECKING_DEVICE_NETWORK_AFTER_LISTEN = 2;

  public static final long TWO_MINS_IN_MILLIS = 2L * 60L * 1000L;

  public static final long MAX_DURATION = MILLISECONDS.convert(
      MINUTES_FOR_CHECKING_DEVICE_NETWORK_AFTER_LISTEN, MINUTES);

  public static final int STOP_LISTENING_MINS = 30;

  public static final Set<String> SET_PANASONIC_ROLES = new HashSet<>(3);

  public static final Set<String> SET_PANASONIC_ROLES_EXCLUDING_PDN_SERVER = new HashSet<>(2);

  public static final Set<String> SET_PANASONIC_ADMIN_ROLES = new HashSet<>(1);

  public static final Set<String> SET_PANASONIC_ADMIN_ROLES_FOR_USER_CUSTOMER_ASSOCIATION =
      new HashSet<>(2);

  public static final Set<String> SET_PAN_CUST_REP_ROLES = new HashSet<>(1);

  public static final Set<String> SET_CUSTOMER_ADMIN_ROLES = new HashSet<>(1);

  public static final Set<String> SET_ALL_ADMIN_ROLES = new HashSet<>(3);

  public static final Set<String> SET_ALL_SYSTEM_ROLES = new HashSet<>(7);

  public static final Set<MediaType> SET_ALL_EDITABLE_CONTENT_TYPES_POST_PUBLISH =
      new HashSet<>(2);

  public static final Set<MediaType> SET_ALL_EDITABLE_DURATION_ON_CONTENT_TYPES_POST_PUBLISH =
      new HashSet<>(7);

  public static final Set<MediaType> SET_ALL_DOWNLOADABLE_CONTENT_TYPES =
      new HashSet<>(7);

  public static final Set<String> SET_ALL_ON_PREMISES_PROFILES = new HashSet<>(3);

  public static final Set<String> NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER = new HashSet<>();

  public static final String PDN_ENV_KEY_DON_HIT = "LEMMA_DONT_HIT";
  public static final String PDN_ENV_KEY_SANDBOX = "LEMMA_SANDBOX";
  public static final String PDN_ENV_KEY_PROD = "LEMMA_PROD";
  public static final Map<String, String> PDN_API_BASE_URLS = new HashMap<>(3);
  //hack to check plano get performance
  public static final boolean HACK_FOR_PLANOGRAM_GET_LOGIC = true;
  public static final int CUSTOMER_ID_MISSING_IN_HEADER = 1000;
  public static final int MAX_PLANOGRAM_LIST_IN_DEVICE_SECHEDULE = 5000;
  public static final int MAX_ITEMS_PER_PAGE_FOR_PAGINATION = 5000;
  public static final int MAX_ITEM_PER_PAGE_FOR_CAMPAIGN = 10;
  public static final int MAX_ITEMS_PER_PAGE_FOR_PAGINATION_FOR_CRON = 50000;
  public static final int MAX_ITEMS_PER_PAGE_FOR_REPORTS_PAGINATION = 500;
  public static final int DEFAULT_PAGE_NO_FOR_REPORTS_PAGINATION = 1;
  public static final String IDS = "Ids";
  public static final int MAX_NUMBER_OF_LOOPS_FOR_CAMPAIGN_STRING = 10;
  public static final String SUPER_PRIORITY_COLOR_CODE = "#000000";
  /** NUMBER OF ITEMS FOR FB, TWITTER, RSS FEED to fetch */
  public static final int NUMBER_OF_ITEMS_FOR_FB_TWITTER_RSS_FEED_TO_FETCH = 10;
  public static final int SCROLL_INTERVAL_IN_SECONDS_PER_FEED_ITEM = 10;
  public static final String PANASONIC_DEFAULT_CUSTOMER_IMAGE_FILENAME =
      "panasonic-default-image.jpg";
  public static final String PANASONIC_DEFAULT_BRANDING_CENTER_LOGO_FILENAME =
      "panasonic-center-logo.png";
  public static final String PANASONIC_DEFAULT_BRANDING_CENTER_THUMBNAIL_FILENAME =
      "panasonic-center-thumbnail.png";
  public static final String PANASONIC_DEFAULT_BRANDING_FAV_ICON_THUMBNAIL_FILENAME =
      "panasonic-fav-icon-thumbnail.ico";
  public static final String PANASONIC_DEFAULT_BRANDING_FAV_ICON_LOGO_FILENAME =
      "panasonic-fav-icon-logo.ico";
  public static final String PANASONIC_DEFAULT_BRANDING_LEFT_NAV_THUMBNAIL_FILENAME =
      "panasonic-left-nav-thumbnail.png";
  public static final String PANASONIC_DEFAULT_BRANDING_LEFT_NAV_LOGO_FILENAME =
      "panasonic-left-nav-logo.png";
  public static final String PANASONIC_DEFAULT_BRANDING_LOGIN_CENTER_THUMBNAIL_FILENAME =
      "panasonic-login-center-thumbnail.png";
  public static final String PANASONIC_DEFAULT_BRANDING_LOGIN_CENTER_LOGO_FILENAME =
      "panasonic-login-center-logo.png";
  public static final String PANASONIC_DEFAULT_BRANDING_LOGIN_TOP_RIGHT_THUMBNAIL_FILENAME =
      "panasonic-login-top-right-thumbnail.png";
  public static final String PANASONIC_DEFAULT_BRANDING_LOGIN_TOP_RIGHT_LOGO_FILENAME =
      "panasonic-login-top-right-logo.png";
  public static final String PANASONIC_DEFAULT_BRANDING_SDN_THUMBNAIL_FILENAME =
      "panasonic-sdn-thumbnail.png";
  public static final String PANASONIC_DEFAULT_BRANDING_SDN_LOGO_FILENAME =
      "panasonic-sdn-logo.png";
  public static final String PANASONIC_DEFAULT_CUSTOMER_PORTRAIT_IMAGE_FILENAME =
      "panasonic-default-portrait-image.jpg";
  public static final Long FIREBASE_WEB_PUSH_TIME_TO_LEAVE_SECONDS = 15L;
  public static final String TTL = "4500";

  public static final int DEFAULT_ZOOM_WEBVIEW = 0;
  public final static double MAX_ALLOWED_USABLE_BANDWIDTH = 100D * 1024D;
  public static final String ASYNC_EMAIL_WORKERS = "email-worker";
  public static final String ASYNC_CONFIG_WORKERS = "config-worker";
  public static final String ASYNC_DELETE_DB_WORKER = "delete-db-worker";
  public static final String ASYNC_UPLOAD_SNAPSHOT_WORKERS = "upload-snapshot-worker";
  public static final String ASYNC_PUSH_WORKERS = "push-worker";
  public static final String ASYNC_WEB_PUSH_WORKERS = "web-push-worker";
  public static final String ASYNC_ON_PREM_SERVER_PUSH_WORKERS = "onprem-server-push-worker";
  public static final String DEVICE_DATA_SAVE_WORKER = "device-data-save-worker";
  public static final String ASYNC_STATISTICS_REPORT_WORKERS = "statistics-report-worker";
  public static final String ASYNC_CONTENT_PLAYBACK_REPORT_WORKERS =
      "content-playback-report-worker";
  public static final String ASYNC_DEMOGRAPHY_RAW_DATA_REPORT_WORKERS =
      "demography-raw-data-report-worker";
  public static final String ASYNC_STATISTICS_EMAIL_WORKERS = "statistics-email-worker";
  public static final String ASYNC_CONTENT_PLAYBACK_EMAIL_WORKERS = "content-playback-email-worker";
  public static final String ASYNC_DEVICE_EMAIL_WORKERS = "MP-email-worker";
  public static final String ASYNC_PANEL_EMAIL_WORKERS = "Panel-email-worker";
  public static final String ASYNC_STORE_EXCEPTION_WORKER = "store-exception-worker";
  public static final String ASYNC_TPA_BUILD_DOWNLOAD_WORKERS = "TPA-build-download-workers";
  public static final String ASYNC_STATISTICS_FILE_CONVERSION_WORKERS = "file-conversion-worker";
  public static final String ASYNC_PANEL_DEVICE_WORKERS = "panel-device-worker";
  public static final String ASYNC_DATA_DELETION_CRON_WORKERS = "data-deletion-corn-worker";
  public static final String ASYNC_LICENCE_HISTORY_SYNC_WORKERS =
      "customer-licence-history-sync-workers";
  public static final String ASYNC_CUSTOMER_LICENCE_SYNC_WORKERS = "customer-licence-sync-workers";

  public static final String ASYNC_LAST_API_HIT_WORKERS = "last-api-hit-worker";

  public static final String JENKINS_BEARER_TOKEN = "a9641f06-bc3a-48a7-b253-eeab81db8a13";
  public static final String ON_PREMISE_SERVER_BEARER_TOKEN =
      "d8ee3d9f-b8fe-4ab2-9c08-856abd0281b3";

  public static final String WNS_CERT_FILE_EXTENSION = ".pfx";
  public static final String WNS_PACKAGE_FILE_EXTENSION = ".xml";
  public static final String WNS_PACKAGE_APP_MANIFEST_FILE_EXTENSION = ".appxmanifest";
  public static final String WNS_PACKAGE_STORE_ASSOCIATION_XML_FILE_EXTENSION = ".xml";
  public static final String JSON_FILE_EXTENSION = ".json";
  public static final String TS_FILE_EXTENSION = ".ts";
  public static  final String SQL_FILE_EXTENTION=".sql";
  public static  final String JAR_FILE_EXTENTION=".jar";
  public static  final String ZIP_FILE_EXTENTION=".zip";

  public static final String WNS_CERT_FILE_NAME =
      "windowsStoreCertificateFile" + WNS_CERT_FILE_EXTENSION;
  public static final String WNS_PACKAGE_APP_MANIFEST_FILE_NAME =
      "Package" + WNS_PACKAGE_APP_MANIFEST_FILE_EXTENSION;
  public static final String WNS_PACKAGE_FILE_NAME =
      "Package" + WNS_PACKAGE_FILE_EXTENSION;
  public static final String WNS_PACKAGE_STORE_ASSOCIATION_XML_FILE_NAME =
      "Package.StoreAssociation" + WNS_PACKAGE_STORE_ASSOCIATION_XML_FILE_EXTENSION;
  public static final String SERVER_FIREBASE_JSON_FILE_NAME = "serverFirebaseJson" + JSON_FILE_EXTENSION;
  public static final String ANGULAR_JSON_FILE_NAME = "angularFirebaseJson" + TS_FILE_EXTENSION;
  public static final String ANDROID_JSON_FILE_NAME = "androidFirebaseJson" + JSON_FILE_EXTENSION;
  public static final String SERVER_LAUNCH_CONFIG_JSON_FILE_NAME = "serverLaunchConfigJson" + JSON_FILE_EXTENSION;
  public static final String ANGULAR_BUILD_FILE_NAME= "angularBuild" + ZIP_FILE_EXTENTION;
  public static final String SERVER_BUILD_FILE_NAME = "serverBuild" + JAR_FILE_EXTENTION;
  public static final String SERVER_SEED_FILE_NAME = "serverSeed" + SQL_FILE_EXTENTION;
  public static final String SSL_CERT_ZIP_FILE_NAME = "sslCertificates" + ZIP_FILE_EXTENTION;

  public static final boolean OKHTTP_ALLOW_UNTRUSTED_CONNECTION = true; // disable for production
  public static final long DEVICE_SETTING_MAX_TIME_MIN = 120;
  public static final long DEVICE_SETTING_MAX_TIME_SEC = 7200;
  public static final long SCREEN_CAPTURE_MIN_TIME_IN_MINUTE = 15;
  public static final long ERROR_UPDATE_MIN_TIME_IN_MINUTE = 30;
  public static final long DISPLAY_LOG_MIN_TIME_IN_MINUTE = 60;
  public static final long LOG_UPLOAD_MIN_TIME_IN_MINUTE = 60;
  public static final long PANEL_STATUS_MIN_TIME_IN_SECOND = 1800;
  public static final long SNAPSHOT_UPDATE_MIN_TIME_IN_SECOND = 3600;
  public static final long TOUCH_TIME_MIN_TIME_IN_SECOND = 15;
  public static final long DATA_COLLECTION_MIN_TIME_IN_SECOND = 3600;

  public static final long MIN_DURATION_APP_UPGRADE_MSG_TIME_IN_SEC = 10;

  public static final long MAX_DURATION_APP_UPGRADE_MSG_TIME_IN_SEC = 3600;

  public static final long CONTENT_PLAYBACK_MIN_TIME_IN_SECOND = 3600;
  public static final long THREAD_SLEEP_TIME_FOR_REPORT = 100;
  public static final long MAX_ITEM_PER_PAGE_ON_DASHBOARD = 25;

  public static final int MAX_ITEM_PER_PAGE_ON_FA_DASHBOARD = 50;

  public static final int DEFAULT_PAGE_FOR_REPORT_ON_DASHBOARD = 1;

  public static final int DEMOGRAPHY_DASHBOARD_REPORT_FOR_LAST_SEVEN_DAY = 6;

  public static final int DEMOGRAPHY_DASHBOARD_REPORT_FOR_LAST_MONTH = 31;

  public static final int DEMOGRAPHY_DASHBOARD_REPORT_FOR_LAST_DAY = 1;

  public static final long CAMPAIGN_ADVERTISEMENT_DURATION_MAX_LENGTH = 86400;
  public static final int NUMBER_OF_DECIMAL_PLACES_TO_ROUND_OFF_FOR_PERCENTAGE_REPORTS = 2;
  public static final int PLANOGRAM_PUBLISHED_TIME_IN_HOURS = 24;
  public final static String THUMBNAIL_EXTENSION = "png";
  public static final boolean HACK_ENABLE_CONTENT_UPLOAD_ANALYTICS = true;
  public static final Integer PAN_CONFIG_SESSION_EXPIRING_POPUP_INTERVAL_IN_SECONDS = 600;
  public static final Integer PAN_CONFIG_DEVICE_DETAIL_REFRESH_INTERVAL_IN_SECONDS = 1800;
  public static final long TWENTY_FOUR_HRS_IN_MINS = 1440L;
  public static final long TWENTY_FOUR_HRS_IN_SECONDS = 86400L;
  public static final long TWENTY_FOUR_HRS_IN_MILLIS = 86400000L;
  public static final String APPROVE = "APPROVE";
  public static final String RESOURCE_NAME = "resourceName";
  public static final String ACTIONS = "actions";
  public static final String RESOURCE_GROUP_NAME = "groupName";
  public static final String REPORT_DIR = "/reports";
  public static final String REPLACE_CUSTOMER_ID = "{customer-tenant-id}";

  //Customer Data Saving Related Paths
  public static final String ROOT_CONTENT_DIRECTORY = "content";
  public static final String LOCAL_SERVER_BUILD_DIR = "local-server-build";
  public static final String DECRYPTED_CONETNT_DIR = "decrypted-contents";
  public static final String ROOT_TEMP_CONVERSION_DIRECTORY = "temp-content-conversion";
  public static final String ROOT_THUMBNAIL_DIRECTORY = "thumbnails";
  public static final String ROOT_DEFAULT_THUMBNAIL_DIRECTORY = "default_thumbnails";
  public static final String ROOT_OFFLINE_DOWNLOADS_DIRECTORY = "offline-downloads";
  public static final String ROOT_SNAPSHOTS_DIRECTORY = "snapshots";
  public static final String ROOT_SCRIPT_RUNNER_DIRECTORY = "script-runner";
  public static final String ROOT_EMAIL_TEMPLATE_DIRECTORY = "emailTemplate";
  public static final String ROOT_DEVICE_LOGS_DIRECTORY = "device-logs";
  public static final String ROOT_LOCAL_SERVER_BUILD_DIRECTORY = "local-server-build";
  public static final String ROOT_MAIL_IMAGES_DIRECTORY = "mail-images";
  public static final String ROOT_SNAPSHOT_TEMP_DIRECTORY = "snapshot-temp";
  public static final String ROOT_CONTENT_THUMBNAIL_DIRECTORY =
      ROOT_CONTENT_DIRECTORY + File.separator + ROOT_THUMBNAIL_DIRECTORY;
  public static final String ROOT_CONTENT_CUSTOMER_DIRECTORY =
      "content" + File.separator + REPLACE_CUSTOMER_ID;
  public static final String ROOT_CONTENT_THUMBNAIL_CUSTOMER_DIRECTORY =
      ROOT_CONTENT_THUMBNAIL_DIRECTORY +
          File.separator + REPLACE_CUSTOMER_ID;
  public static final String ROOT_CONTENT_TEMP_CONVERSION_CUSTOMER_DIRECTORY =
      ROOT_CONTENT_DIRECTORY +
          File.separator + ROOT_TEMP_CONVERSION_DIRECTORY + File.separator + REPLACE_CUSTOMER_ID;
  public static final String ROOT_CONTENT_DEFAULT_THUMBNAIL_DIRECTORY =
      ROOT_CONTENT_DIRECTORY + File.separator + ROOT_DEFAULT_THUMBNAIL_DIRECTORY;
  public static final String ROOT_CONTENT_TEMP_CONVERSION_DIRECTORY =
      ROOT_CONTENT_DIRECTORY + File.separator + ROOT_TEMP_CONVERSION_DIRECTORY;

  // AWS  Snapshot upload urls
  public static final String REPLACE_DEVICE_ID = "{device-id}";

  public static final String REPLACE_SNAPSHOT_FILE_ID = "{snapshot-file-id}";

  public static final String S3_SNAPSHOT_KEY = "device-snapshot" + "/" + REPLACE_CUSTOMER_ID
      + "/" + REPLACE_DEVICE_ID + "/file/" + REPLACE_SNAPSHOT_FILE_ID;

  public static final String S3_SNAPSHOT_THUMB_KEY = "device-snapshot" + "/" + REPLACE_CUSTOMER_ID
      + "/" + REPLACE_DEVICE_ID + "/thumb/" + REPLACE_SNAPSHOT_FILE_ID;

  public static final String REPLACE_FILENAME = "{file-name}";
  public static final String S3_DEVICE_LOGS_KEY =
      "device-logs/" + REPLACE_DEVICE_ID + "/" + REPLACE_FILENAME;

  public static final String S3_LIVE_SNAPSHOT_KEY = "live-snapshot" + "/" + REPLACE_CUSTOMER_ID
      + "/" + REPLACE_DEVICE_ID + "/" + REPLACE_SNAPSHOT_FILE_ID;

  public static final String S3_LIVE_SNAPSHOT_THUMB_KEY =
      "live-snapshot-thumb" + "/" + REPLACE_CUSTOMER_ID
          + "/" + REPLACE_DEVICE_ID + "/" + REPLACE_SNAPSHOT_FILE_ID;

  // TPA Constants

  public static final String TPA_BUILDS_DIR = "tpa-builds";

  public static final String DEFAULT_TEMPLATE_JSON = "default-templates";

  public static final String SERVER_CONFIG_DIRECTORY = "server-config";
  public static final String SERVER_CONFIG_JSON_FILENAME = "server-config.json";
  public static final String SERVER_LOGS_DIR = "logs";
  public static final String CURRENT_SNAPSHOT_THUMBNAIL_SUFFIX = "-thumb";
  public static final String TEMP_DIR = "tmp";
  public static final String LIVE_DEVICE_SNAPSHOT_DIR = "LIVE-Snapshot";
  public static final String THUMB_DIR = "thumb";
  public static final String SHELL_SCRIPT_DIR = "shell-script";
  public static final int MAX_CUSTOMER_LICENSE = 1000000;
  public static final long ONE_HOUR_IN_MILLIS = 3600000L;
  public static final long TWO_HOUR_IN_MILLIS = (ONE_HOUR_IN_MILLIS * 2);
  public static final long ONE_DAY_IN_MILLIS = 86400000L;
  public static final long FIFTEEN_MINS_IN_MILLIS = 900000L;
  public static final int MINS_FOR_DELETE_DOWNLOAD_REQUESTS = 90;
  public static final String[] ALLOWED_PATHS_LIST = new String[] {"launch"};
  public static final String TPA_DP_LINK_SCHEMA_CONCAT_STRING = "://launch";
  public static final String LOCAL_SERVER_DEVICE_BUILDS_DIR = "builds";
  public static final int TOTAL_NO_OF_DAYS_FOR_REPORT_CACHE_PROCESS = 40;
  public static final String MAIL_HEARDER_LOGO = "logo.png";
  public static final String MAIL_SIGNATURE_LOGO = "brand-logo.png";
  public static final String NULL_SAFE_STRING_IN_MAIL = "Unknown";

  public static final Map<TpaOs, List<DeviceOs>> TPA_OS_TO_DEVICE_OS_LIST_MAP = new HashMap<>(3);
  public static final Map<TpaOs, String> INSTALLABLE_FILES_TPA_OS_EXTENSIONS = new HashMap<>(3);

  static {
    SET_PANASONIC_ROLES.add(ROLE_PANASONIC_ADMIN);
    SET_PANASONIC_ROLES.add(ROLE_PANASONIC_CUST_REP);
    SET_PANASONIC_ROLES.add(ROLE_PDN_SERVER);

    SET_PANASONIC_ROLES_EXCLUDING_PDN_SERVER.add(ROLE_PANASONIC_ADMIN);
    SET_PANASONIC_ROLES_EXCLUDING_PDN_SERVER.add(ROLE_PANASONIC_CUST_REP);

    SET_PANASONIC_ADMIN_ROLES.add(ROLE_PANASONIC_ADMIN);
    SET_PANASONIC_ADMIN_ROLES_FOR_USER_CUSTOMER_ASSOCIATION.add(ROLE_PANASONIC_ADMIN);
    SET_PANASONIC_ADMIN_ROLES_FOR_USER_CUSTOMER_ASSOCIATION.add(ROLE_PDN_SERVER);

    SET_CUSTOMER_ADMIN_ROLES.add(ROLE_CUSTOMER_ADMIN);

    SET_PAN_CUST_REP_ROLES.add(ROLE_PANASONIC_CUST_REP);
    SET_ALL_ADMIN_ROLES.add(ROLE_PANASONIC_ADMIN);
    SET_ALL_ADMIN_ROLES.add(ROLE_CUSTOMER_ADMIN);
    SET_ALL_ADMIN_ROLES.add(ROLE_PANASONIC_CUST_REP);

    SET_ALL_SYSTEM_ROLES.add(ROLE_PANASONIC_ADMIN);
    SET_ALL_SYSTEM_ROLES.add(ROLE_PANASONIC_CUST_REP);
    SET_ALL_SYSTEM_ROLES.add(ROLE_APPROVER);
    SET_ALL_SYSTEM_ROLES.add(ROLE_MAKER);
    SET_ALL_SYSTEM_ROLES.add(ROLE_VIEW_ONLY);
    SET_ALL_SYSTEM_ROLES.add(ROLE_CUSTOMER_ADMIN);
    SET_ALL_SYSTEM_ROLES.add(ROLE_PDN_SERVER);

    SET_ALL_EDITABLE_CONTENT_TYPES_POST_PUBLISH.add(MediaType.TEXT);
    SET_ALL_EDITABLE_CONTENT_TYPES_POST_PUBLISH.add(MediaType.HTML);
    SET_ALL_EDITABLE_CONTENT_TYPES_POST_PUBLISH.add(MediaType.URL);
    SET_ALL_EDITABLE_CONTENT_TYPES_POST_PUBLISH.add(MediaType.TWITTER);
    SET_ALL_EDITABLE_CONTENT_TYPES_POST_PUBLISH.add(MediaType.FACEBOOK);
    SET_ALL_EDITABLE_CONTENT_TYPES_POST_PUBLISH.add(MediaType.RSS);
    SET_ALL_EDITABLE_CONTENT_TYPES_POST_PUBLISH.add(MediaType.STREAM_URL);

    SET_ALL_EDITABLE_DURATION_ON_CONTENT_TYPES_POST_PUBLISH.add(MediaType.TEXT);
    SET_ALL_EDITABLE_DURATION_ON_CONTENT_TYPES_POST_PUBLISH.add(MediaType.HTML);
    SET_ALL_EDITABLE_DURATION_ON_CONTENT_TYPES_POST_PUBLISH.add(MediaType.URL);
    SET_ALL_EDITABLE_DURATION_ON_CONTENT_TYPES_POST_PUBLISH.add(MediaType.TWITTER);
    SET_ALL_EDITABLE_DURATION_ON_CONTENT_TYPES_POST_PUBLISH.add(MediaType.FACEBOOK);
    SET_ALL_EDITABLE_DURATION_ON_CONTENT_TYPES_POST_PUBLISH.add(MediaType.RSS);
    SET_ALL_EDITABLE_DURATION_ON_CONTENT_TYPES_POST_PUBLISH.add(MediaType.STREAM_URL);

    SET_ALL_DOWNLOADABLE_CONTENT_TYPES.add(MediaType.VIDEO);
    SET_ALL_DOWNLOADABLE_CONTENT_TYPES.add(MediaType.AUDIO);
    SET_ALL_DOWNLOADABLE_CONTENT_TYPES.add(MediaType.IMAGE);
    SET_ALL_DOWNLOADABLE_CONTENT_TYPES.add(MediaType.FLASH);
    SET_ALL_DOWNLOADABLE_CONTENT_TYPES.add(MediaType.PDF);
    SET_ALL_DOWNLOADABLE_CONTENT_TYPES.add(MediaType.DOC);
    SET_ALL_DOWNLOADABLE_CONTENT_TYPES.add(MediaType.PPT);

    SET_ALL_ON_PREMISES_PROFILES.add("pionpremises");
    SET_ALL_ON_PREMISES_PROFILES.add("pionpremises2");
    SET_ALL_ON_PREMISES_PROFILES.add("pionpremises-dev");

    PDN_API_BASE_URLS.put(PDN_ENV_KEY_DON_HIT, "http://localhost");
    PDN_API_BASE_URLS.put(PDN_ENV_KEY_SANDBOX, "http://sandbox.lemmatechnologies.com/");
    PDN_API_BASE_URLS.put(PDN_ENV_KEY_PROD, "https://adpf.in.panasonic.com/");

    NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER.add("ADD_TEMPLATE");
    NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER.add("EDIT_TEMPLATE");
    NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER.add("DELETE_TEMPLATE");
    NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER.add("VIEW_REPORTS");
    NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER.add("ADD_ROLE");
    NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER.add("EDIT_ROLE");
    NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER.add("DELETE_ROLE");
    NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER.add("EDIT_LOCATION");
    NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER.add("DELETE_LOCATION");
    NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER.add("ADD_TPA");
    NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER.add("EDIT_TPA");
    NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER.add("DELETE_TPA");
    NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER.add("VIEW_TPA");
    NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER.add("ADD_TPA_REPORTS");
    NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER.add("EDIT_TPA_REPORTS");
    NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER.add("DELETE_TPA_REPORTS");
    NOT_ALLOWED_PRIVILEGES_FOR_BASIC_TIER.add("VIEW_TPA_REPORTS");

    PDN_API_BASE_URLS.put(PDN_ENV_KEY_DON_HIT, "http://localhost");
    PDN_API_BASE_URLS.put(PDN_ENV_KEY_SANDBOX, "http://sandbox.lemmatechnologies.com/");
    PDN_API_BASE_URLS.put(PDN_ENV_KEY_PROD, "https://adpf.in.panasonic.com/");

    INSTALLABLE_FILES_TPA_OS_EXTENSIONS.put(TpaOs.ANDROID, "apk");
    INSTALLABLE_FILES_TPA_OS_EXTENSIONS.put(TpaOs.WINDOWS, "zip");
    INSTALLABLE_FILES_TPA_OS_EXTENSIONS.put(TpaOs.DESKTOP, "zip");

    List<DeviceOs> tempList = new ArrayList<>(2);
    tempList.add(DeviceOs.ANDROID);
    tempList.add(DeviceOs.ANDROID_TV);
    TPA_OS_TO_DEVICE_OS_LIST_MAP.put(TpaOs.ANDROID, Collections.unmodifiableList(tempList));
    TPA_OS_TO_DEVICE_OS_LIST_MAP.put(TpaOs.WINDOWS,
        Collections.unmodifiableList(Collections.singletonList(DeviceOs.WINDOWS)));
    TPA_OS_TO_DEVICE_OS_LIST_MAP.put(TpaOs.DESKTOP,
        Collections.unmodifiableList(Collections.singletonList(DeviceOs.DESKTOP)));

  }

  public enum TPA_BASIC_SCHEMA {
    http, https, local
  }

  public static final BigDecimal BIG_DECIMAL_KILO = new BigDecimal(1000);

  public interface RawReportCache {
    int HOUR_INTERVAL_FOR_DELETE = 24;
    int HOUR_INTERVAL_FOR_RECHECK = 1;
  }

  public static final int ASPECT_RATIO_DEFAULT_CONSTANT_DIVIDER = 800;

  public interface VideoResolutionConstants {
    int MAX_VIDEO_HEIGHT_DIMENSION = 2160;
    int MAX_VIDEO_WIDTH_DIMENSION = 3840;
    int MIN_VIDEO_HEIGHT_DIMENSION = 720;
    int MIN_VIDEO_WIDTH_DIMENSION = 720;
  }

  public interface Headers {
    String AUTHORIZATION = "Authorization";
    String CURRENT_IMAGE_VERSION = "Current-Image-Version";
    String CUSTOMER_ID = "customerId";
    String DEVICE_ID = "DeviceId";
    String CLIENT_GENERATED_DEVICE_IDENTIFIER = "ClientGeneratedDeviceIdentifier";
    String RESPONSE_LENGTH = "Response-Length";
    String X_FORWARDED_FOR = "X-FORWARDED-FOR";
    String CONTENT_LENGTH = "Content-Length";
    String CONTENT_RANGE = "Content-Range";
  }


  public interface PanelReport {
    String NEW_PERCENTAGE = "PanelPercentageReportNewServiceImpl";
    String OLD_PERCENTAGE = "PanelPercentageReportOldServiceImpl";
    String NEW_LOG_REPORT = "PanelReportNewServiceImpl";
    String OLD_LOG_REPORT = "PanelReportOldServiceImpl";
  }

  public interface DeviceReport {
    String NEW_PERCENTAGE = "DevicePercentageReportNewServiceImpl";
    String OLD_PERCENTAGE = "DevicePercentageReportOldServiceImpl";
    String NEW_LOG_REPORT = "DeviceReportNewServiceImpl";
    String OLD_LOG_REPORT = "DeviceReportOldServiceImpl";
  }

  public interface LoggerFilePrefix {
    String CONTENT_PLAYBACK = "CONTENT_PLAYBACK";
  }

  public static final Map<DeviceOs, String> INSTALLABLE_TPA_EXTENSIONS = new HashMap<>(4);
  public static final Map<BuildOs, String> JSON_KEY_FOR_LATEST_DEVICE_OS_MAP = new HashMap<>(4);
  public static final Map<BuildOs, String> INSTALLABLE_FILES_BUILD_OS_EXTENSIONS = new HashMap<>(7);
  public static final Map<DeviceOs, List<BuildOs>> DEVICE_OS_BUILD_OS_MAP = new HashMap<>(3);
  public static final List<DeviceOs> ALLOWED_DEVICE_OS_FOR_BASIC_CUSTOMER = new ArrayList<>(2);
  public static final Map<DeviceOs, BuildOs> DEFAULT_BUILD_OS_FOR_DEVICE_OS = new HashMap<>(4);

  public static final String ANDROID_APP_VERSION_BELOW_NO_SCHEDULE = "1.0.255";

  public static final String SPREADSHEET_FILE_EXTENSION = ".xlsx";
  static {
    INSTALLABLE_TPA_EXTENSIONS.put(DeviceOs.ANDROID, "apk");
    INSTALLABLE_TPA_EXTENSIONS.put(DeviceOs.WINDOWS, "zip");
    INSTALLABLE_TPA_EXTENSIONS.put(DeviceOs.DESKTOP, "zip");
    INSTALLABLE_TPA_EXTENSIONS.put(DeviceOs.ANDROID_TV, "apk");

    JSON_KEY_FOR_LATEST_DEVICE_OS_MAP.put(BuildOs.ANDROID, "latestAndroidVersion");
    JSON_KEY_FOR_LATEST_DEVICE_OS_MAP.put(BuildOs.ANDROID_WATCH_DOG, "latestAndroidWatchDogVersion");
    JSON_KEY_FOR_LATEST_DEVICE_OS_MAP.put(BuildOs.ANDROID_TV, "latestAndroidTvVersion");
    JSON_KEY_FOR_LATEST_DEVICE_OS_MAP.put(BuildOs.WINDOWS, "latestWindowsVersion");
    JSON_KEY_FOR_LATEST_DEVICE_OS_MAP.put(BuildOs.DESKTOP_APP_CLIENT, "latestDesktopAppClientVersion");
    JSON_KEY_FOR_LATEST_DEVICE_OS_MAP.put(BuildOs.DESKTOP_APP_SERVER, "latestDesktopAppServerVersion");
    JSON_KEY_FOR_LATEST_DEVICE_OS_MAP.put(BuildOs.DESKTOP_APP_NATIVE, "latestDesktopAppNativeVersion");

    INSTALLABLE_FILES_BUILD_OS_EXTENSIONS.put(BuildOs.ANDROID, "apk");
    INSTALLABLE_FILES_BUILD_OS_EXTENSIONS.put(BuildOs.ANDROID_WATCH_DOG, "apk");
    INSTALLABLE_FILES_BUILD_OS_EXTENSIONS.put(BuildOs.ANDROID_TV, "apk");
    INSTALLABLE_FILES_BUILD_OS_EXTENSIONS.put(BuildOs.WINDOWS, "zip");
    INSTALLABLE_FILES_BUILD_OS_EXTENSIONS.put(BuildOs.DESKTOP_APP_SERVER, "zip");
    INSTALLABLE_FILES_BUILD_OS_EXTENSIONS.put(BuildOs.DESKTOP_APP_CLIENT, "zip");
    INSTALLABLE_FILES_BUILD_OS_EXTENSIONS.put(BuildOs.DESKTOP_APP_NATIVE, "zip");

    List<BuildOs> desktopBuildOses = new ArrayList<>(3);
    desktopBuildOses.add(BuildOs.DESKTOP_APP_NATIVE);
    desktopBuildOses.add(BuildOs.DESKTOP_APP_SERVER);
    desktopBuildOses.add(BuildOs.DESKTOP_APP_CLIENT);
    DEVICE_OS_BUILD_OS_MAP.put(DeviceOs.DESKTOP, Collections.unmodifiableList(desktopBuildOses));

    List<BuildOs> androidBuildOses = new ArrayList<>(3);
    androidBuildOses.add(BuildOs.ANDROID_WATCH_DOG);
    androidBuildOses.add(BuildOs.ANDROID);
    DEVICE_OS_BUILD_OS_MAP.put(DeviceOs.ANDROID, Collections.unmodifiableList(androidBuildOses));

    List<BuildOs> androidTvBuildOses = new ArrayList<>(1);
    androidTvBuildOses.add(BuildOs.ANDROID_TV);
    DEVICE_OS_BUILD_OS_MAP.put(DeviceOs.ANDROID_TV, Collections.unmodifiableList(androidTvBuildOses));

    List<BuildOs> windowsBuildOses = new ArrayList<>(1);
    windowsBuildOses.add(BuildOs.WINDOWS);
    DEVICE_OS_BUILD_OS_MAP.put(DeviceOs.WINDOWS, Collections.unmodifiableList(windowsBuildOses));

    ALLOWED_DEVICE_OS_FOR_BASIC_CUSTOMER.add(DeviceOs.DESKTOP);
    ALLOWED_DEVICE_OS_FOR_BASIC_CUSTOMER.add(DeviceOs.ANDROID_TV);

    DEFAULT_BUILD_OS_FOR_DEVICE_OS.put(DeviceOs.DESKTOP, BuildOs.DESKTOP_APP_CLIENT);
    DEFAULT_BUILD_OS_FOR_DEVICE_OS.put(DeviceOs.ANDROID, BuildOs.ANDROID);
    DEFAULT_BUILD_OS_FOR_DEVICE_OS.put(DeviceOs.ANDROID_TV, BuildOs.ANDROID_TV);
    DEFAULT_BUILD_OS_FOR_DEVICE_OS.put(DeviceOs.WINDOWS, BuildOs.WINDOWS);
  }

  public static final String ROOT_CONTENT_PLAYBACK_TEMP_DIRECTORY = "content-playback-temp";

  public static final String RECORDS_FETCHED_NAME = "SuccessfullyFetched";

  public static final String RECORDS_FETCHED_MSG = "Records fetched successfully";
}
