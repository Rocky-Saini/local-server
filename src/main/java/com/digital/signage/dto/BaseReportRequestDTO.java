package com.digital.signage.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author -Ravi Kumar created on 1/23/2023 12:15 AM
 * @project - Digital Sign-edge
 */
public class BaseReportRequestDTO implements Serializable {
    private static final long serialVersionUID = -4318242600607870858L;
    private Date fromDate;
    private String fromDateOperator;
    private Date toDate;
    private String toDateOperator;
    private List<Long> deviceList;
    private String deviceListOperator;
    private Long locationId;
    private String locationIdOperator;
    private Integer currentPage;
    private Integer numPerPage;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(Integer numPerPage) {
        this.numPerPage = numPerPage;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public String getFromDateOperator() {
        return fromDateOperator;
    }

    public void setFromDateOperator(String fromDateOperator) {
        this.fromDateOperator = fromDateOperator;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getToDateOperator() {
        return toDateOperator;
    }

    public void setToDateOperator(String toDateOperator) {
        this.toDateOperator = toDateOperator;
    }

    public String getDeviceListOperator() {
        return deviceListOperator;
    }

    public void setDeviceListOperator(String deviceListOperator) {
        this.deviceListOperator = deviceListOperator;
    }

    public List<Long> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Long> deviceList) {
        this.deviceList = deviceList;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationIdOperator() {
        return locationIdOperator;
    }

    public void setLocationIdOperator(String locationIdOperator) {
        this.locationIdOperator = locationIdOperator;
    }

    /**
     * are the object equal
     */
    protected boolean objectEquals(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) return true;
        if (obj1 != null && obj2 == null) return false;
        if (obj1 == null) return false;
        return obj1.equals(obj2);
    }

    /**
     * check basic object equality and then check further
     */
    protected Boolean objectBasicEquals(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) return true;
        if (obj1 != null && obj2 == null) return false;
        if (obj1 == null && obj2 != null) return false;
        return null;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;
        BaseReportRequestDTO that = (BaseReportRequestDTO) other;
        if (!objectEquals(that.fromDateOperator, this.fromDateOperator)) return false;
        if (!objectEquals(that.fromDate, this.fromDate)) return false;
        if (!objectEquals(that.toDateOperator, this.toDateOperator)) return false;
        if (!objectEquals(that.toDate, this.toDate)) return false;
        if (!objectEquals(that.locationIdOperator, this.locationIdOperator)) return false;
        if (!objectEquals(that.locationId, this.locationId)) return false;
        if (!objectEquals(that.deviceListOperator, this.deviceListOperator)) return false;
        Boolean eq = objectBasicEquals(that.deviceList, this.deviceList);
        if (eq == null) {
            if (that.deviceList.size() != this.deviceList.size()) {
                return false;
            } else {
                // since lists are sorted and they don't have duplicates we can compare each item
                for (int i = 0; i < this.deviceList.size(); i++) {
                    if (!that.deviceList.get(i).equals(this.deviceList.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            return eq;
        }
    }

    @Override
    public String toString() {
        return "BaseReportRequestDTO{" +
                "fromDate=" + fromDate +
                ", fromDateOperator='" + fromDateOperator + '\'' +
                ", toDate=" + toDate +
                ", toDateOperator='" + toDateOperator + '\'' +
                ", deviceList=" + deviceList +
                ", deviceListOperator='" + deviceListOperator + '\'' +
                ", locationId=" + locationId +
                ", locationIdOperator='" + locationIdOperator + '\'' +
                ", currentPage=" + currentPage +
                ", numPerPage=" + numPerPage +
                '}';
    }
}
