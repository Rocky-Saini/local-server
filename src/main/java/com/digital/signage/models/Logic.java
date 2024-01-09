package com.digital.signage.models;

import com.digital.signage.util.Status;
import com.fasterxml.jackson.annotation.*;
import com.google.common.collect.ImmutableMap;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author -Ravi Kumar created on 1/22/2023 11:51 PM
 * @project - Digital Sign-edge
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "key", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DeviceLogic.class, name = "DEVICES"),
        @JsonSubTypes.Type(value = LocationLogic.class, name = "LOCATIONS"),
        @JsonSubTypes.Type(value = DeviceGroupLogic.class, name = "DEVICE_GROUPS"),
        @JsonSubTypes.Type(value = LocationWithDeviceGroupLogic.class, name = "LOCATIONS_AND_DEVICE_GROUPS"),
})
@Entity(name = "logic")
@Table(name = "planogram_device_logic")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Logic {

    public enum LogicType {
        DEVICES(1, 0), LOCATIONS(2, 1), DEVICE_GROUPS(3, 2), LOCATIONS_AND_DEVICE_GROUPS(4, 3);
        private final int value;
        private final int dbValue;

        private LogicType(int value, int dbValue) {
            this.value = value;
            this.dbValue = dbValue;
        }

        private final static Map<Integer, LogicType> MAP_VALUE_TO_LOGIC_TYPE;

        static {
            Map<Integer, LogicType> mapOfDbValues = new HashMap<>();
            for (LogicType logicType : LogicType.values()) {
                mapOfDbValues.put(logicType.dbValue, logicType);
            }
            MAP_VALUE_TO_LOGIC_TYPE = ImmutableMap.copyOf(mapOfDbValues);
        }

        public static LogicType fromDbValue(Integer dbValue) {
            return MAP_VALUE_TO_LOGIC_TYPE.getOrDefault(dbValue, null);
        }

        public Integer dbValueFromLogicType() {
            return dbValue;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("device_logic_id")
    private Long id;

    @Transient
    @JsonIgnore
    private boolean overwrite;

    @Column(name = "status")
    private Status status;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createdOn;

    @Column(name = "modified_on")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date modifiedOn;

    @Column(name = "customer_id")
    private Long customerId;

    @JsonProperty("key")
    private LogicType logicType;

    @JsonIgnore
    public boolean getOverwrite() {
        return overwrite;
    }

    @JsonProperty("overwrite")
    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }

    public LogicType getLogicType() {
        return logicType;
    }

    public void setLogicType(LogicType logicType) {
        this.logicType = logicType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
