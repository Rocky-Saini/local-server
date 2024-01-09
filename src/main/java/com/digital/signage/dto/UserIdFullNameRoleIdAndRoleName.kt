package com.digital.signage.dto

/**
 * @author -Ravi Kumar created on 1/21/2023 10:03 PM
 * @project - Digital Sign-edge
 */
interface UserIdFullNameRoleIdAndRoleName {
    fun getUserId(): Long?
    fun getFullName(): String?
    fun getRoleId(): Long?
    fun getRoleName(): String?
}