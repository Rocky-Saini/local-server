package com.digital.signage.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.LinkedHashMap;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MediaVersionDTO {
  private Integer version;
  private String contentName;
  private String fileExt;

  public MediaVersionDTO(Integer version, String contentName, String fileExt) {
    this.version = version;
    this.contentName = contentName;
    this.fileExt = fileExt;
  }

  public MediaVersionDTO() {
  }



    public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getContentName() {
    return contentName;
  }

  public void setContentName(String contentName) {
    this.contentName = contentName;
  }

  public String getFileExt() {
    return fileExt;
  }

  public void setFileExt(String fileExt) {
    this.fileExt = fileExt;
  }
  public static MediaVersionDTO linkedHashMapToMediaVersionDTO(LinkedHashMap<String, Object> linkedHashSet) {
    LinkedHashMap<String, Object> result = (LinkedHashMap<String, Object>) linkedHashSet.get("mediaVersionDTO");
    return new MediaVersionDTO((Integer) result.get("version"),
            (String) result.get("contentName"),
            (String) result.get("fileExt"));
  }
}
