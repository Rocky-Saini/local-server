package com.digital.signage.cache

/**
 * @author -Ravi Kumar created on 1/19/2023 11:27 AM
 * @project - Digital Sign-edge
 */
interface ICachable {
    fun getUniqueKeySuffixForCache(): String
}