package com.digital.signage.dto;//package com.digital.signage.dto;
//
//import com.digital.signage.models.ContentPlaybackActuals;
//import com.fasterxml.jackson.annotation.JsonProperty;
//
//import java.util.Objects;
//
//public class ContentPlaybackDto extends ContentPlaybackActuals {
//
//    @JsonProperty("tpappId")
//    private Long tpAppId;
//
//    public Long getTpAppId() {
//        return tpAppId;
//    }
//
//    public void setTpAppId(Long tpAppId) {
//        this.tpAppId = tpAppId;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (o instanceof ContentPlaybackDto) {
//            ContentPlaybackDto that = (ContentPlaybackDto) o;
//            if (!super.equals(that)) return false;
//            return Objects.equals(tpAppId, that.tpAppId);
//        }
//        return false;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(super.hashCode(), tpAppId);
//    }
//}
//
