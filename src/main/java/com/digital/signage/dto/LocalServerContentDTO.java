package com.digital.signage.dto;


import com.digital.signage.annotations.PdfColumn;
import com.digital.signage.annotations.ReportColumn;
import com.digital.signage.util.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Convert;
import java.awt.*;
import java.util.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalServerContentDTO {
    public static final Set<MediaType> SET_ALL_EDITABLE_DURATION_ON_CONTENT_TYPES_POST_PUBLISH = new HashSet<>(7);

    public static final String CONTENT_LOCAL_MEDIA_URL = "localMediaUrl";
    public static final String PATH_DEFAULT_THUMBNAILS = "/thumb/default/";

    public static final String CONTENT_MEDIA_URL = "mediaUrl";

    private static final String REPLACEBLE_TEXT_TYPE = "valign= \"%s\"";
    private static final String MARQUEE_WITH_BEHAVIOR =
            "<marquee direction=\"%s\" scrollamount=\"%d\" behavior=\"%s\" ";
    private static final String MARQUEE_WITHOUT_BEHAVIOR =
            "<marquee direction=\"%s\" scrollamount=\"%d\" ";
    private static int DEFAULT_ZOOM_WEBVIEW = 0;

    private static String END_DIV = "</div>";

    private static String END_MARQUEE = "</marquee>";

    private static final String JSON_MARQUEE_ALIGNMENT = "marqueeAlignment";

    private static final String REPLACEABLE_SCROLL_AMOUNT_KEY = "{{marqueeScrollAmount}}";

    private static final String REPLACEABLE_BACKGROUND_COLOR_KEY = "{{backGroundColor}}";

    private static final String REPLACEABLE_STYLE_KEY =
            "background-color:" + REPLACEABLE_BACKGROUND_COLOR_KEY + ";";

    private static final String REPLACEABLE_HTML_KEY = "{{serverHtml}}";

    private static final String WINDOWS_HTML_WITHOUT_MARQUEE =
            "<!-- The \"Background Color\"  of Text should be set here in div \"divBGColor\" dynamically. -->\n"
                    + "<div id=\"divBGColor\" style=\"height:100%;overflow:hidden;"
                    + REPLACEABLE_STYLE_KEY
                    + "\">\n"
                    + "<table style=\"height:100%\"><tr><td id=\"tdContainer\" " + REPLACEBLE_TEXT_TYPE + "/>"
                    + "\n"
                    + "        <!-- The Server side \"TEXT/CONTENT\" should be started to merge from here. -->\n"
                    + "        <!-- The Server side \"TEXT/CONTENT\" should be ended here. -->\n"
                    + "        "
                    + REPLACEABLE_HTML_KEY
                    + "\n"
                    + " </td></tr></table>"
                    + "</div>";

    private static final String WINDOWS_HTML_WITH_MARQUEE =
            "<!-- The \"Scroll Amount/Speed\" of marquee should be set here in label \"lblScrollAmount\" dynamically. -->\n"
                    + "<label id=\"lblScrollAmount\" hidden=\"hidden\">"
                    + REPLACEABLE_SCROLL_AMOUNT_KEY
                    + "</label>\n"
                    + "<!-- The \"Background Color\"  of Text should be set here in div \"divBGColor\" dynamically. -->\n"
                    + "<div id=\"divBGColor\" style=\"height:100%;overflow:hidden;"
                    + REPLACEABLE_STYLE_KEY
                    + "\">\n"
                    + "<table style=\"height:100%\"><tr><td id=\"tdContainer\" " + REPLACEBLE_TEXT_TYPE + "/>"
                    + "  <div class=\"marquee\">\n"
                    + "    <div class=\"move-ele\">\n"
                    + "\n"
                    + "        <!-- The Server side \"TEXT/CONTENT\" should be started to merge from here. -->\n"
                    + "        <!-- The Server side \"TEXT/CONTENT\" should be ended here. -->\n"
                    + "        "
                    + REPLACEABLE_HTML_KEY
                    + "\n"
                    + "\n"
                    + "    </div>\n"
                    + "  </div>\n"
                    + " </td></tr></table>"
                    + "</div>";

    private Long mediaDetailId;
    @JsonIgnore
    private Long customerId;
    @PdfColumn(columnName = "Content Name", order = 1)
    @ReportColumn(columnName = "Content Name", order = 1)
    private String name;
    private Access accessRight;
    private DisplayMode displayMode;
    private Orientation orientation;
    private MediaType type;
    private String description;
    private String twitterHandle;
    private String rssUrl;
    private String streamUrl;
    private String facebookPageId;
    private String html;
    private Long createdBy;
    private Date createdOn;
    private Date modifiedOn;
    private Status status;
    @JsonProperty("tags")
    private Set<MediaTagResponse.MediaTag> mediaTags;
    private String fullName;
    private Integer numberOfItems;
    private Long autoScrollDurationInSeconds;
    private MarqueeDirection marqueeDirection;
    private Integer marqueeScrollAmount;
    private String marqueeBehavior;
    private String backgroundColor;
    @JsonProperty("isBackgroundColor")
    private Boolean isBgColor;
    private Boolean isArchived;
    private Date archivedTime;
    private Integer zoomPercentForWebview;
    private String htmlTemplateForWindows;
    private Boolean useAsyncContentDownload;
    private EncryptionStatus encryptionStatus;

    private String localThumbnailUrl;  // for API getter only


    @JsonIgnore
    public void setUseAsyncContentDownload(Boolean useAsyncContentDownload) {
        this.useAsyncContentDownload = useAsyncContentDownload;
    }


    @JsonProperty("useAsyncContentDownload")
    public Boolean getUseAsyncContentDownload() {
        return this.useAsyncContentDownload;
    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    private MediaProcessStatus conversionStatus = MediaProcessStatus.PROCESSED;

    private String fileExtension;

    private String filepath;

    @JsonInclude(JsonInclude.Include.NON_NULL)

    private String mediaHex;


    private Long mediaSize;

    private int version = 1;

    private String thumbnailFilePath;


    private String localFileUrl;
    private String thumbnailUrl;
    private String fileUrl;
    private Integer defaultDurationInSeconds;
    private TextAlignment marqueeAlignment;
    private String url;
    private String mediaDimension;

    @JsonIgnore
    @Convert(converter = FFmpegProbeResultDbConverter.class)
    private FFmpegProbeResult fFmpegProbeResult;
    @JsonProperty(value = "fFmpegProbeResult")
    private com.digital.signage.dto.FfmpegProbResult.FFmpegProbeResult fFmpegProbeResultResult;


    @JsonProperty(value = JSON_MARQUEE_ALIGNMENT, access = JsonProperty.Access.WRITE_ONLY)
    public void setMarqueeAlignment(TextAlignment marqueeAlignment) {
        this.marqueeAlignment = marqueeAlignment;
    }

    @JsonProperty(value = JSON_MARQUEE_ALIGNMENT, access = JsonProperty.Access.READ_ONLY)
    public TextAlignment getMarqueeAlignment() {
        if (!MediaType.TEXT.equals(type)
                || marqueeDirection == null
                || marqueeDirection.equals(MarqueeDirection.DOWN)
                || marqueeDirection.equals(MarqueeDirection.UP)) {
            return null;
        } else {
            return marqueeAlignment;
        }
    }

    @JsonIgnore
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @JsonIgnore
    public String getFileUrl() {
        return fileUrl;
    }

    public void setHtmlTemplateForWindowsUsingMarqueeDirection(Map<WindowMarqueeHtmlTemplate, String> windowHtmlTemplateByWindowHtmlDirection) {
        if (MediaType.TEXT.equals(this.getType())) {
            MarqueeDirection marqueeDirection = this.getMarqueeDirection();
            this.setHtmlTemplateForWindows(MarqueeDirection.UP.equals(marqueeDirection) ? windowHtmlTemplateByWindowHtmlDirection.get(WindowMarqueeHtmlTemplate.WINDOWS_MARQUEE_UP_HTML_TEMPLATE_FILE_NAME) :
                    MarqueeDirection.DOWN.equals(marqueeDirection) ? windowHtmlTemplateByWindowHtmlDirection.get(WindowMarqueeHtmlTemplate.WINDOWS_MARQUEE_DOWN_HTML_TEMPLATE_FILE_NAME) :
                            MarqueeDirection.LEFT.equals(marqueeDirection) ? windowHtmlTemplateByWindowHtmlDirection.get(WindowMarqueeHtmlTemplate.WINDOWS_MARQUEE_LEFT_HTML_TEMPLATE_FILE_NAME) :
                                    MarqueeDirection.RIGHT.equals(marqueeDirection) ? windowHtmlTemplateByWindowHtmlDirection.get(WindowMarqueeHtmlTemplate.WINDOWS_MARQUEE_RIGHT_HTML_TEMPLATE_FILE_NAME) :
                                            null);
        }
    }

    @JsonProperty("isFileReadyForPlay")
    public MediaProcessStatus isFileReadyForPlay() {
        MediaProcessStatus fileReadyForPlay = null;
        if (Objects.nonNull(conversionStatus) && Objects.nonNull(encryptionStatus)) {
            if ((MediaProcessStatus.PROCESSED.equals(conversionStatus)
                    || MediaProcessStatus.NOT_APPLICABLE.equals(conversionStatus))
                    && (EncryptionStatus.ENCRYPTION_SUCCESS.equals(encryptionStatus)
                    || EncryptionStatus.NOT_APPLICABLE.equals(encryptionStatus)))
                fileReadyForPlay = MediaProcessStatus.PROCESSED;
            else if ((MediaProcessStatus.PROCESSING_FAILED.equals(conversionStatus))
                    || EncryptionStatus.ENCRYPTION_FAILED.equals(encryptionStatus))
                fileReadyForPlay = MediaProcessStatus.PROCESSING_FAILED;
            else if (MediaProcessStatus.PROCESSING.equals(conversionStatus)
                    || EncryptionStatus.ENCRYPTION_PROCESSING.equals(encryptionStatus))
                fileReadyForPlay = MediaProcessStatus.PROCESSING;
        } else
            fileReadyForPlay = MediaProcessStatus.PROCESSING;

        return Objects.isNull(fileReadyForPlay) ? MediaProcessStatus.PROCESSING : fileReadyForPlay;
    }

    public void sanitizeMediaDetailViaMediaType() {
        if (MediaType.RSS.equals(this.getType())) {
            this.setRssUrl(this.getRssUrl());
            this.setHtml(null);
            this.setUrl(null);
            this.setStreamUrl(null);
            this.setFilepath(null);
            this.setThumbnailFilePath(null);
            this.setTwitterHandle(null);
            this.setFacebookPageId(null);
            this.setMarqueeDirection(null);
            this.setMarqueeScrollAmount(null);
            this.setMarqueeBehavior(null);
            this.setEncryptionStatus(EncryptionStatus.NOT_APPLICABLE);
            this.setConversionStatus(MediaProcessStatus.NOT_APPLICABLE);
        }
        if (MediaType.TWITTER.equals(this.getType())) {
            this.setRssUrl(null);
            this.setHtml(null);
            this.setUrl(null);
            this.setStreamUrl(null);
            this.setFilepath(null);
            this.setThumbnailFilePath(null);
            this.setTwitterHandle(this.getTwitterHandle());
            this.setFacebookPageId(null);
            this.setMarqueeDirection(null);
            this.setMarqueeScrollAmount(null);
            this.setMarqueeBehavior(null);
            this.setEncryptionStatus(EncryptionStatus.NOT_APPLICABLE);
            this.setConversionStatus(MediaProcessStatus.NOT_APPLICABLE);
        }
        if (MediaType.URL.equals(this.getType())) {
            if (this.getZoomPercentForWebview() == null) {
                this.setZoomPercentForWebview(DEFAULT_ZOOM_WEBVIEW);
            }
            this.setUrl(this.getUrl());
            this.setStreamUrl(null);
            this.setFilepath(null);
            this.setThumbnailFilePath(null);
            this.setTwitterHandle(null);
            this.setFacebookPageId(null);
            this.setRssUrl(null);
            this.setMarqueeDirection(null);
            this.setMarqueeScrollAmount(null);
            this.setMarqueeBehavior(null);
            this.setHtml(null);
            this.setEncryptionStatus(EncryptionStatus.NOT_APPLICABLE);
            this.setConversionStatus(MediaProcessStatus.NOT_APPLICABLE);
        }

        if (MediaType.STREAM_URL.equals(this.getType())) {
            this.setStreamUrl(this.getStreamUrl());
            this.setUrl(null);
            this.setFilepath(null);
            this.setThumbnailFilePath(null);
            this.setTwitterHandle(null);
            this.setFacebookPageId(null);
            this.setRssUrl(null);
            this.setMarqueeDirection(null);
            this.setMarqueeScrollAmount(null);
            this.setMarqueeBehavior(null);
            this.setHtml(null);
            this.setEncryptionStatus(EncryptionStatus.NOT_APPLICABLE);
            this.setConversionStatus(MediaProcessStatus.NOT_APPLICABLE);
        }
        if (MediaType.FACEBOOK.equals(this.getType())) {
            this.setUrl(null);
            this.setStreamUrl(null);
            this.setFilepath(null);
            this.setThumbnailFilePath(null);
            this.setTwitterHandle(null);
            this.setFacebookPageId(this.getFacebookPageId());
            this.setRssUrl(null);
            this.setHtml(null);
            this.setMarqueeDirection(null);
            this.setMarqueeScrollAmount(null);
            this.setMarqueeBehavior(null);
            this.setEncryptionStatus(EncryptionStatus.NOT_APPLICABLE);
            this.setConversionStatus(MediaProcessStatus.NOT_APPLICABLE);
        }
        if (MediaType.HTML.equals(this.getType())
                || MediaType.TEXT.equals(this.getType())) {
            if (MediaType.HTML.equals(this.getType())) {
                this.setMarqueeDirection(null);
                this.setMarqueeScrollAmount(null);
                this.setMarqueeBehavior(null);
            }
            this.setEncryptionStatus(EncryptionStatus.NOT_APPLICABLE);
            this.setConversionStatus(MediaProcessStatus.NOT_APPLICABLE);
            this.setStreamUrl(null);
            this.setUrl(null);
            this.setFilepath(null);
            this.setThumbnailFilePath(null);
            this.setTwitterHandle(null);
            this.setFacebookPageId(null);
            this.setRssUrl(null);
            this.setHtml(FileUtility.removeWhiteSpaces(this.getHtml()));
            if (this.getIsBgColor() == null || !this.getIsBgColor()) {
                this.setBackgroundColor(null);
            }
        }

        if (MediaType.DOC.equals(this.getType()) || MediaType.PDF.equals(this.getType()) ||
                MediaType.AUDIO.equals(this.getType()) || MediaType.IMAGE.equals(this.getType()) ||
                MediaType.VIDEO.equals(this.getType()) || MediaType.PPT.equals(this.getType())) {
            this.setUrl(null);
            this.setStreamUrl(null);
            this.setTwitterHandle(null);
            this.setFacebookPageId(null);
            this.setRssUrl(null);
            this.setHtml(null);
            this.setMarqueeDirection(null);
            this.setMarqueeScrollAmount(null);
            this.setMarqueeBehavior(null);
            if (MediaType.DOC.equals(this.getType()) ||
                    MediaType.PPT.equals(this.getType())) {
                this.setConversionStatus(MediaProcessStatus.NOT_APPLICABLE);
            }
        }
    }

    @JsonProperty("encryptionStatus")
    public EncryptionStatus getEncryptionStatus() {
        return encryptionStatus;
    }

    public void setUrlInFilePath(String url, String localServerUrl) {
        if (StringUtils.isNotBlank(this.getFilepath())
                && (MediaProcessStatus.PROCESSED.equals(this.getConversionStatus())
                || MediaProcessStatus.NOT_APPLICABLE.equals(this.getConversionStatus()))
                && (EncryptionStatus.ENCRYPTION_SUCCESS.equals(this.getEncryptionStatus())
                || EncryptionStatus.NOT_APPLICABLE.equals(this.getEncryptionStatus()))
                && (MediaType.PDF.equals(this.getType())
                || MediaType.AUDIO.equals(this.getType())
                || MediaType.IMAGE.equals(this.getType())
                || MediaType.VIDEO.equals(this.getType())
                || MediaType.DOC.equals(this.getType())
                || MediaType.PPT.equals(this.getType()))) {
            boolean isDevice = /*TenantContext.isDevice()*/true;//TODO
            Long userId = isDevice ? null : /*TenantContext.getCurrentUserId()*/2L;//TODO
            Long deviceId = isDevice ? /*TenantContext.getLoggedInDeviceId()*/3L : null;
            if (isDevice) {
                if (localServerUrl != null) {
                    if (!(localServerUrl.startsWith("http://")
                            || localServerUrl.startsWith("https://"))) {
                        localServerUrl = "http://" + localServerUrl;
                    }
                    /*localServerUrl = localServerUrl
                            + PATH_CONTENT_FILES
                            + toJwtToken(
                            TenantContext.getCustomerId(),
                            content.getContentId(),
                            content.getName(),
                            content.getFileExtension(),
                            content.getFilepath(),
                            null,
                            deviceId)
                            + "/"
                            + content.getContentId()
                            + "?download=" + isDevice;*///TODO
                }
            }
            this.setLocalFileUrl(localServerUrl);
            this.setFileUrl(url);
        }
    }

    public void setThumbnailUrl(String url, String localThumbnailUrl, String serverBaseUrlForAngularDownloadLinks) {//TODO local server
        /*boolean isDevice = TenantContext.isDevice();
        if (isDevice) {
            Long deviceId = TenantContext.getLoggedInDeviceId();
        }*/
        if (this.getThumbnailFilePath() != null
                && (MediaProcessStatus.PROCESSED.equals(this.getConversionStatus())
                || MediaProcessStatus.NOT_APPLICABLE.equals(this.getConversionStatus()))
                && (EncryptionStatus.ENCRYPTION_SUCCESS.equals(this.getEncryptionStatus())
                || EncryptionStatus.NOT_APPLICABLE.equals(this.getEncryptionStatus()))
                && (MediaType.IMAGE.equals(this.getType())
                || MediaType.VIDEO.equals(this.getType()))) {
            /*Long userId = isDevice ? null : TenantContext.getCurrentUserId();
            Long deviceId = isDevice ? TenantContext.getLoggedInDeviceId() : null;*/

            this.setThumbnailUrl(url);
            //for Local Server
            /*if (localThumbnailUrl != null) {
                String localUrl = localThumbnailUrl
                        + UrlPaths.PATH_CONTENT_THUMB_FILES
                        + (TenantContext.isDevice() ?
                        toJwtToken(
                                TenantContext.getCustomerId(),
                                content.getContentId(),
                                content.getName(),
                                content.getFileExtension(),
                                content.getFilepath(),
                                null,
                                TenantContext.getLoggedInDeviceId()
                        ) :
                        toJwtToken(
                                TenantContext.getCustomerId(),
                                content.getContentId(),
                                content.getName(),
                                content.getFileExtension(),
                                content.getFilepath(),
                                TenantContext.getCurrentUserId(),
                                null
                        ))
                        + "/"
                        + content.getContentId()
                        + "?download=" + String.valueOf(TenantContext.isDevice());
                this.setLocalThumbnailUrl(localUrl);
            }*/
        } else if (MediaType.PDF.equals(this.getType())
                || MediaType.TWITTER.equals(this.getType())
                || MediaType.FACEBOOK.equals(this.getType())
                || MediaType.RSS.equals(this.getType())
                || MediaType.PPT.equals(this.getType())
                || MediaType.DOC.equals(this.getType())) {
            this.setThumbnailUrl(removeSlashFromEndOfUrlIfRequired(
                    serverBaseUrlForAngularDownloadLinks)
                    + PATH_DEFAULT_THUMBNAILS
                    + this.getType().name().toLowerCase()
                    + "?download=" + String.valueOf(/*TenantContext.isDevice()*/true));//TODO set the value via tenantcontext
            //For Local Server//TODO need to check for local server
            if (localThumbnailUrl != null) {
                String localUrl = localThumbnailUrl
                        + PATH_DEFAULT_THUMBNAILS
                        + this.getType().name().toLowerCase()
                        + "?download=" + /*TenantContext.isDevice()*/true;//TODO set the value via tenantcontext
                this.setLocalThumbnailUrl(localUrl);
            }
        }
    }

    public void setMediaDetail(MediaDetailDto detail) {
        if (Objects.nonNull(this.getStatus())) {
            detail.setStatus(this.getStatus());
        }
        if (Objects.nonNull(this.getAccessRight())) {
            detail.setAccessRight(this.getAccessRight());
        }
        if (Objects.nonNull(this.getDisplayMode())) {
            detail.setDisplayMode(this.getDisplayMode());
        }
        if (Objects.nonNull(this.getOrientation())) {
            detail.setOrientation(this.getOrientation());
        }
        if (StringUtils.isNotBlank(this.getUrl())) {
            detail.setUrl(this.getUrl());
        }
        if (StringUtils.isNotBlank(this.getStreamUrl())) {
            detail.setStreamUrl(this.getStreamUrl());
        }
        if (StringUtils.isNotBlank(this.getDescription())) {
            detail.setDescription(this.getDescription());
        }
        if (StringUtils.isNotBlank(this.getFacebookPageId())) {
            detail.setFacebookPageId(this.getFacebookPageId());
        }
        if (StringUtils.isNotBlank(this.getHtml())) {
            detail.setHtml(this.getHtml());
        }
        if (Objects.nonNull(this.getRssUrl())) {
            detail.setRssUrl(this.getRssUrl());
        }
        if (Objects.nonNull(this.getTwitterHandle())) {
            detail.setTwitterHandle(this.getTwitterHandle());
        }
        detail.setVersion(detail.getVersion() + 1);
        if (Objects.nonNull(this.getAutoScrollDurationInSeconds())) {
            detail.setAutoScrollDurationInSeconds(this.getAutoScrollDurationInSeconds());
        }
        if (Objects.nonNull(this.getNumberOfItems())) {
            detail.setNumberOfItems(this.getNumberOfItems());
        }
        //Update Default Duration
        if (SET_ALL_EDITABLE_DURATION_ON_CONTENT_TYPES_POST_PUBLISH.contains(detail.getType())) {
            detail.setDefaultDurationInSeconds(this.getDefaultDurationInSeconds());
        }

        if (Objects.nonNull(this.getMarqueeDirection())) {
            detail.setMarqueeDirection(this.getMarqueeDirection());
        }
        if (Objects.nonNull(this.getMarqueeScrollAmount())) {
            detail.setMarqueeScrollAmount(this.getMarqueeScrollAmount());
        }

        if (Objects.nonNull(this.getMarqueeBehavior())) {
            detail.setMarqueeBehavior(this.getMarqueeBehavior());
        }

        if (Objects.nonNull(this.getIsBgColor())) {
            detail.setIsBgColor(this.getIsBgColor());
        }

        if (Objects.nonNull(this.getBackgroundColor())) {
            detail.setBackgroundColor(this.getBackgroundColor());
        }

        if (Objects.nonNull(this.getMarqueeAlignment())) {
            detail.setMarqueeAlignment(this.getMarqueeAlignment());
        }

        if (Objects.nonNull(this.getDefaultDurationInSeconds())) {
            detail.setDefaultDurationInSeconds(this.getDefaultDurationInSeconds());
        }

        if (Objects.nonNull(this.getZoomPercentForWebview())) {
            detail.setZoomPercentForWebview(this.getZoomPercentForWebview());
        }
    }

    @JsonProperty("videoUrl")
    public String getVideoUrl() {
        return MediaType.VIDEO.equals(type) ? fileUrl : null;
    }

    @JsonProperty("imageUrl")
    public String getImageUrl() {
        return MediaType.IMAGE.equals(type) ? fileUrl : null;
    }

    @JsonProperty("audioUrl")
    public String getAudioUrl() {
        return MediaType.AUDIO.equals(type) ? fileUrl : null;
    }

    @JsonProperty("docUrl")
    public String getDocUrl() {
        return MediaType.DOC.equals(type) ? fileUrl : null;
    }

    @JsonProperty("pdfUrl")
    public String getPdfUrl() {
        return MediaType.PDF.equals(type) ? fileUrl : null;
    }

    @JsonProperty("pptUrl")
    public String getPptUrl() {
        return MediaType.PPT.equals(type) ? fileUrl : null;
    }

    @JsonProperty("localVideoUrl")
    public String getLocalVideoUrl() {
        return MediaType.VIDEO.equals(type) ? localFileUrl : null;
    }

    @JsonProperty("localImageUrl")
    public String getLocalImageUrl() {
        return MediaType.IMAGE.equals(type) ? localFileUrl : null;
    }

    @JsonProperty("localAudioUrl")
    public String getLocalAudioUrl() {
        return MediaType.AUDIO.equals(type) ? localFileUrl : null;
    }

    @JsonProperty("localDocUrl")
    public String getLocalDocUrl() {
        return MediaType.DOC.equals(type) ? localFileUrl : null;
    }

    @JsonProperty("localPdfUrl")
    public String getLocalPdfUrl() {
        return MediaType.PDF.equals(type) ? localFileUrl : null;
    }

    @JsonProperty("localPptUrl")
    public String getLocalPptUrl() {
        return MediaType.PPT.equals(type) ? localFileUrl : null;
    }

    public Integer getNumberOfItems() {
        switch (type) {
            case RSS:
            case FACEBOOK:
            case TWITTER:
                return numberOfItems;
            default:
                return null;
        }
    }

    public void setNumberOfItems(Integer numberOfItems) {
        switch (type) {
            case RSS:
            case FACEBOOK:
            case TWITTER:
                this.numberOfItems = numberOfItems;
        }
    }

    public Long getAutoScrollDurationInSeconds() {
        switch (type) {
            case RSS:
            case FACEBOOK:
            case TWITTER:
                return autoScrollDurationInSeconds;
            default:
                return null;
        }
    }

    public void setAutoScrollDurationInSeconds(Long autoScrollDurationInSeconds) {
        switch (type) {
            case RSS:
            case FACEBOOK:
            case TWITTER:
                this.autoScrollDurationInSeconds = autoScrollDurationInSeconds;
        }
    }

    @JsonProperty(CONTENT_MEDIA_URL)
    public String getMediaUrl() {
        String mediaUrl = null;
        if (type != null) {
            switch (type) {
                case AUDIO:
                case VIDEO:
                case IMAGE:
                case PDF:
                case DOC:
                case PPT:
                    if ((MediaProcessStatus.PROCESSED.equals(conversionStatus)
                            || MediaProcessStatus.NOT_APPLICABLE.equals(conversionStatus))
                            && (EncryptionStatus.ENCRYPTION_SUCCESS.equals(encryptionStatus))
                            || EncryptionStatus.NOT_APPLICABLE.equals(encryptionStatus)) {
                        mediaUrl = fileUrl;
                    }
                    break;
            }
        }
        return mediaUrl;
    }

    @JsonProperty(CONTENT_LOCAL_MEDIA_URL)
    public String getLocalMediaUrl() {
        String mediaUrl = null;
        if (type != null) {
            switch (type) {
                case AUDIO:
                case VIDEO:
                case IMAGE:
                case PDF:
                case DOC:
                case PPT:
                    if ((MediaProcessStatus.PROCESSED.equals(conversionStatus)
                            || MediaProcessStatus.NOT_APPLICABLE.equals(conversionStatus))
                            && (EncryptionStatus.ENCRYPTION_SUCCESS.equals(encryptionStatus))
                            || EncryptionStatus.NOT_APPLICABLE.equals(encryptionStatus)) {
                        mediaUrl = localFileUrl;
                    }
                    break;
            }
        }
        return mediaUrl;
    }

    @JsonProperty("htmlForWindows")
    public String getHtmlForWindows() {
        if (MediaType.HTML.equals(type)) {
            return html;
        } else if (MediaType.TEXT.equals(type)) {
            String marquee = getMarquee();
            if (marquee == null) {
                // the windows Html without marquee
                return replacedWindowsHtml(null, html, backgroundColor, isBgColor,
                        null, null);
            } else {
                String s = replacedWindowsHtml(marqueeScrollAmount, html, backgroundColor, isBgColor,
                        marqueeAlignment, marqueeDirection);
                return htmlTemplateForWindows + s;
            }
        } else {
            return null;
        }
    }

    @JsonProperty("htmlForWindowsOld")
    @Deprecated
    public String getHtmlForWindowsOld() {
        if (MediaType.HTML.equals(type)) {
            return html;
        } else if (MediaType.TEXT.equals(type)) {
            String marquee = getMarquee();
            if (marquee == null) {
                return html;
            } else {
                return marquee + html + "</marquee>";
            }
        } else {
            return null;
        }
    }

    @JsonProperty("htmlForLinux")
    public String getHtmlForLinux() {
        if (html == null) {
            return null;
        }
        return html.replaceAll("<p>", "").replaceAll("</p>", "");
    }


    private String getMarqueeForAndroid() {
        if (MediaType.TEXT.equals(type) && !MarqueeDirection.NONE.equals(marqueeDirection)) {
            if (marqueeBehavior != null) {
                // marqueeBehavior should be present
                return String.format(MARQUEE_WITH_BEHAVIOR, "up", 2, marqueeBehavior)
                        + ">";
            } else if (marqueeDirection != null && marqueeScrollAmount != null) {
                // both marqueeDirection and marqueeBehavior should be present
                return String.format(MARQUEE_WITHOUT_BEHAVIOR,
                        marqueeDirection.getMarqueeDirectionForHTML(), marqueeScrollAmount)
                        + ">";
            } else {
                // all three are not present
                return null;
            }
        } else {
            return null;
        }
    }
    private static String replacedWindowsHtml(
            Integer marqueeScrollAmount,
            String html,
            String backgroundColor,
            Boolean isBGColor,
            TextAlignment textContentAlignment,
            MarqueeDirection marqueeDirection
    ) {
        String s;
        if (marqueeDirection != null && !marqueeDirection.equals(MarqueeDirection.NONE)) {
            s =
                    WINDOWS_HTML_WITH_MARQUEE.replace(REPLACEABLE_SCROLL_AMOUNT_KEY,
                            String.valueOf(marqueeScrollAmount));
        } else {
            s = WINDOWS_HTML_WITHOUT_MARQUEE;
        }
        if (!org.springframework.util.StringUtils.isEmpty(backgroundColor) && isBGColor != null && isBGColor) {
            s = s.replace(REPLACEABLE_BACKGROUND_COLOR_KEY, backgroundColor);
        } else {
            s = s.replace(REPLACEABLE_STYLE_KEY, "");
        }
        s = s.replace(REPLACEABLE_HTML_KEY, html);

        if ((marqueeDirection != null && !marqueeDirection.equals(MarqueeDirection.NONE))
                && (marqueeDirection.equals(MarqueeDirection.LEFT)
                || marqueeDirection.equals(MarqueeDirection.RIGHT))) {
            String alignmentValue = "";
            if (textContentAlignment != null) {
                switch (textContentAlignment) {
                    case TOP: {
                        alignmentValue = "top";
                        break;
                    }
                    case CENTER: {
                        alignmentValue = "middle";
                        break;
                    }
                    case BOTTOM: {
                        alignmentValue = "bottom";
                        break;
                    }
                }
            } else {
                alignmentValue = "middle";
            }
            s = s.replace(REPLACEBLE_TEXT_TYPE, String.format(REPLACEBLE_TEXT_TYPE, alignmentValue));
        } else {
            s = s.replace(REPLACEBLE_TEXT_TYPE, "");
        }
        return s;
    }

    @JsonProperty("marquee")
    public String getMarquee() {
        if (MediaType.TEXT.equals(type)) {
            if (marqueeBehavior != null) {
                // marqueeBehavior should be present
                return String.format(MARQUEE_WITH_BEHAVIOR, "up", 2, marqueeBehavior)
                        + "height=\"100%\">";
            } else if ((marqueeDirection != null && !marqueeDirection.equals(MarqueeDirection.NONE))
                    && marqueeScrollAmount != null) {
                // both marqueeDirection and marqueeBehavior should be present
                return String.format(MARQUEE_WITHOUT_BEHAVIOR,
                        marqueeDirection.getMarqueeDirectionForHTML(), marqueeScrollAmount)
                        + "height=\"100%\">";
            } else {
                // all three are not present
                return null;
            }
        } else {
            return null;
        }
    }

    @JsonProperty("htmlForMobile")
    public String getHtmlForMobile() {
        if (MediaType.HTML.equals(type)) {
            return html;
        } else if (MediaType.TEXT.equals(type)) {
            String marquee = getMarqueeForAndroid();
            String secondDiv = generateSecondDiv(marqueeAlignment, marqueeDirection);
            String generatedHtml = generateFirstDiv(backgroundColor, isBgColor)
                    + secondDiv
                    + (marquee == null ? "" : marquee)
                    + html
                    + (marquee == null ? "" : END_MARQUEE)
                    + END_DIV
                    + (secondDiv.isEmpty() ? "" : END_DIV);
//            logger.error("generatedHtml = {}", generatedHtml);
            return generatedHtml;
        } else {
            return null;
        }
    }

    private static String generateFirstDiv(String backgroundColor, Boolean isBGColor) {
        String colorSetting = "";
        if (backgroundColor != null && isBGColor != null && isBGColor) {
            colorSetting = String.format("background:%s;", backgroundColor);
        }
        return "<div class='d-flex' style='height:{{regionHeight}};"
                + colorSetting
                + "'>";
    }

    private static String generateSecondDiv(
            TextAlignment marqueeAlignment,
            MarqueeDirection marqueeDirection
    ) {
        if (marqueeAlignment == null || marqueeDirection.equals(MarqueeDirection.NONE)
                || !(marqueeDirection.equals(MarqueeDirection.LEFT)
                || marqueeDirection.equals(MarqueeDirection.RIGHT))) {
            return "";
        }
        String secondDiv = "<div class=$replaceme style='width:100%;'>";
        secondDiv = secondDiv.replace("$replaceme", "'" + marqueeAlignment.getClassValue() + "'");
        return secondDiv;
    }

    private String removeSlashFromEndOfUrlIfRequired(String url) {
        if (url.endsWith("/"))
            return url.substring(0, url.length() - 1);
        else
            return url;
    }

    //   @JsonProperty("fFmpegProbeResult")
    @JsonIgnore
    public FFmpegProbeResult getFFmpegProbeResult() {
        return fFmpegProbeResult;
    }

    public void setFFmpegProbeResult(FFmpegProbeResult fFmpegProbeResult) {
        this.fFmpegProbeResult = fFmpegProbeResult;
    }
}
