package com.digital.signage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MediaTagResponse {
    @JsonProperty("mediaTags")
    private List<MediaTag> mediaTags;

    @Getter
    @Setter
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class MediaTag {
        private String title;
        private Long customerId;
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createdOn;
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime modifiedOn;
        private String createdBy;
    }
}
