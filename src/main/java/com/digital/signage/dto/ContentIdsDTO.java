package com.digital.signage.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class ContentIdsDTO {
    private Set<Long> campaignIds;
}
