package com.digital.signage.models;

import com.digital.signage.util.Status;

import javax.persistence.*;
import java.util.Date;

/**
 * @author -Ravi Kumar created on 1/23/2023 5:33 PM
 * @project - Digital Sign-edge
 */
@Entity(name = Template.TABLE_NAME)
public class Template implements EntityModel {
    public static final String TABLE_NAME = "template";
    public static final String TEMPLATE_NAME = "templateName";
    public static final String TEMPLATE_BACKGROUND_IMAGE_CONTENT_ID = "backgroundImageContentId";
    public static final String TEMPLATE_STATE = "state";
    public static final String TEMPLATE_ASPECT_RATIO_ID = "aspectRatioId";
    public static final String IDS = "Ids";
    public static final String CREATED_BY = "createdBy";
    public static final String STATUS = "status";
    public static final String TEMPLATE_ID = "template_id";
    public static final String TEMPLATE_ASPECT_RATIO = "aspectRatio";
    public static final String CUSTOMER_ID = "customerId";
    public static final String TEMPLATE_DESCRIPTION = "templateDesc";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = TEMPLATE_ID)
    private Long templateId;

    @Column(name = "template_name")
    private String templateName;

    @Column(name = "created_on")
    private Date createdOn;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "modified_on")
    private Date modifiedOn;

    @Column(name = "template_desc", columnDefinition = "TEXT")
    private String templateDesc;

    @Transient
    private Long aspectRatioId;

    @ManyToOne
    @JoinColumn(name = "aspect_ratio_id")
    private AspectRatio aspectRatio;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "state")
    private byte state;
    @Column(name = "background_color")
    private String backgroundColor;
    @Column(name = "background_image_content_id")
    private Long backgroundImageContentId;
    @Column(name = "transparency_in_percentage", columnDefinition = "Decimal(3,2)")
    private Double transparencyInPercentage;
    @Column(name = STATUS)
    private Status status;

    public AspectRatio getAspectRatio() {
        return aspectRatio;
    }

    public void setAspectRatio(AspectRatio aspectRatio) {
        this.aspectRatio = aspectRatio;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getTemplateDesc() {
        return templateDesc;
    }

    public void setTemplateDesc(String templateDesc) {
        this.templateDesc = templateDesc;
    }

    public Long getAspectRatioId() {
        return (aspectRatio != null) ? aspectRatio.getAspectRatioId() : aspectRatioId;
    }

    public void setAspectRatioId(Long aspectRatioId) {
        this.aspectRatioId = aspectRatioId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Long getBackgroundImageContentId() {
        return backgroundImageContentId;
    }

    public void setBackgroundImageContentId(Long backgroundImageContentId) {
        this.backgroundImageContentId = backgroundImageContentId;
    }

    public Double getTransparencyInPercentage() {
        return transparencyInPercentage;
    }

    public void setTransparencyInPercentage(Double transparencyInPercentage) {
        this.transparencyInPercentage = transparencyInPercentage;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

