package com.digital.signage.dto;//package com.digital.signage.dto;
//
//import com.digital.signage.mapper.NativeQueryResultColumn;
//import com.digital.signage.mapper.NativeQueryResultEntity;
//
//@NativeQueryResultEntity
//public class DevicePanelMapDTO {
//
//  @NativeQueryResultColumn(index = 0)
//  Long deviceId;
//
//  /**
//   * this json of one of the following
//   *
//   * { 12, "ON" } = panelId, audio status
//   * OR
//   * { 12, "ON } = panelId, panel status
//   */
//  @NativeQueryResultColumn(index = 1)
//  String json;
//
//  public Long getDeviceId() {
//    return deviceId;
//  }
//
//  public void setDeviceId(Long deviceId) {
//    this.deviceId = deviceId;
//  }
//
//  /**
//   * this json of one of the following
//   *
//   * { 12, "ON" } = panelId, audio status
//   * OR
//   * { 12, "ON } = panelId, panel status
//   */
//  public String getJson() {
//    return json;
//  }
//
//  public void setJson(String json) {
//    this.json = json;
//  }
//}
