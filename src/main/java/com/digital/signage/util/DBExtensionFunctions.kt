@file:JvmName("DBExtensionFunctions")

package digital.signage.database

import java.sql.ResultSet

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