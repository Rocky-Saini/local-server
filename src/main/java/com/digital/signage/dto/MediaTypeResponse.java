package com.digital.signage.dto;



import com.digital.signage.util.MediaType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MediaTypeResponse {
    @JsonProperty("mediaTypes")
    private List<MediaType.MediaTypeDetail> mediaTypeDetails;
}
