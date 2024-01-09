package com.digital.signage.dto;

import com.digital.signage.util.ContentType;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContentIdNameContentTypeAndMediaSize {
   private Long ContentId;
    private String ContentName;
    private ContentType ContentType;
    private Long  MediaSize;
}