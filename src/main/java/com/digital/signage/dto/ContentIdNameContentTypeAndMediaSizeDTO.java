package com.digital.signage.dto;


import com.digital.signage.util.ContentType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ContentIdNameContentTypeAndMediaSizeDTO {
    private Long mediaDetailId;
    private String name;
   private ContentType mediaType;
    private Long mediaSize;
}
