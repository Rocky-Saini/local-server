package com.digital.signage.dto

import com.digital.signage.models.Logic
import com.digital.signage.util.Status

/**
 * @author -Ravi Kumar created on 2/1/2023 12:50 AM
 * @project - Digital Sign-edge
 */
interface JustLogic {
    fun getLogicType(): Logic.LogicType?
    fun getPlanogramId(): Long?
    fun getCustomerId(): Long?
    fun getStatus(): Status?
    fun getId(): Long?
    fun getDlmDeviceId(): Long?
    fun getDlmDeviceName(): String?
    fun getLlmLocationId(): Long?
    fun getLlmLocationName(): String?
    fun getDglmDeviceGroupId(): Long?
    fun getDglmDeviceGroupName(): String?
    fun getLwdgLocationId(): Long?
    fun getLwdgLocationName(): String?
    fun getLwdgDeviceGroupId(): Long?
    fun getLwdgDeviceGroupName(): String?
}