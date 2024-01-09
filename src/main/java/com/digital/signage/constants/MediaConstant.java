package com.digital.signage.constants;


public interface MediaConstant {
    String MEDIA_TYPES = "/v1/media/types";
    String MEDIA_TAGS = "/v1/media/tags/{mediaDetailId}";
    String WIDGET_TYPES = "/v1/media/widget/types";
    String MEDIA_FORMATS = "/v1/media/formats";
    String ADD_MEDIA_VIA_FILE = "/v1/media/file";

    String ADD_BOARD_MEDIA_VIA_FILE = "/v1/board/media/file";

    String GET_BACKGROUND_MEDIA = "/v1/media/background";

    String GET_FOREGROUND_MEDIA = "/v1/media/foreground";

    String ADD_MEDIA_VIA_CONTENT = "/v1/media";
    String ARCHIVE_MEDIA = "/v1/media/archive";
    String UNARCHIVE_MEDIA = "/v1/media/unarchive";
    String GET_ARCHIVE_MEDIA = "/v1/media/archive";
    String SEARCH_MEDIA = "/v1/media/search";
    String DELETE_MEDIA = "/v1/media";
    String UPDATE_MEDIA_VIA_CONTENT = "/v1/media/{mediaDetailId}";
    String UPDATE_MEDIA_VIA_FILE = "/v1/media/file/{mediaDetailId}";
    String GET_MEDIA_UPLOAD_ANALYTICS = "/v1/media/upload-analytics";
    String GET_MEDIA_LATEST_VERSION = "/v1/media/version";
    String GET_MEDIA_THUMB_FILE = "/v1/media/files/thumb";
    String GET_MEDIA_FILES = "/v1/media/files";
    String TOTAL_MEDIA_AND_ARCHIVE_COUNT = "/v1/total-media-archive-count";
    String GET_MEDIA_DETAIL = "/v1/media/{mediaDetailId}";
    String GET_ALL_MEDIA_BY_TAG = "/v1/media/tags/{tagId}/media";//TODO
    String SUCCESS = "success";
    int MAX_ENTRIES_PER_PAGE = 5000;
    int DEFAULT_DURATION_IN_SECONDS = 5;
    String X_FORWARDED_FOR = "X-FORWARDED-FOR";
    boolean HACK_ENABLE_CONTENT_UPLOAD_ANALYTICS = true;
    String ATTACHMENT = "attachment; filename=";
    String INLINE = "inline";
    String INLINE_WITH_FILENAME = "inline; filename=";
    String CONTENT_DISPOSITION = "Content-Disposition";
    String MEDIA_NAME_ALREADY_IN_USE = "Media name already in use";

    String MARQUEE_SCROLL_AMOUNT = "marqueeScrollAmount";

    String MARQUEE_DIRECTION = "marqueeDirection";
}
