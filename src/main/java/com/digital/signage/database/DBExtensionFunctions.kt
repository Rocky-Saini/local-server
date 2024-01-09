@file:JvmName("DBExtensionFunctions")

package com.digital.signage.database

import java.sql.ResultSet

/**
 * @author -Ravi Kumar created on 1/25/2023 12:36 AM
 * @project - Digital Sign-edge
 */


fun ResultSet.nullableLong(columnName: String): Long? {
    val x = this.getLong(columnName)
    return if (this.wasNull()) {
        null
    } else {
        x
    }
}

fun ResultSet.nullableBoolean(columnName: String): Boolean? {
    val x = this.getBoolean(columnName)
    return if (this.wasNull()) {
        null
    } else {
        x
    }
}

fun ResultSet.nullableInt(columnName: String): Int? {
    val x = this.getInt(columnName)
    return if (this.wasNull()) {
        null
    } else {
        x
    }
}