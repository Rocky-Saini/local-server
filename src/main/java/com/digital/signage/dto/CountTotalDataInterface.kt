package com.digital.signage.dto

@FunctionalInterface
interface CountTotalDataInterface {
    /**
     * This method return total no of count.
     */
    fun countTotalRecordInTable(): Int?
}