package com.digital.signage.models

import javax.persistence.AttributeConverter
import javax.persistence.Converter

/**
 * @author -Ravi Kumar created on 1/22/2023 11:41 PM
 * @project - Digital Sign-edge
 */
enum class DeviceAndPanelPendingType(val dbValue: Int) {
    DEVICE_AUDIO(DeviceAndPanelPendingType.DB_VALUE_DEVICE_AUDIO),  // 1
    PANEL_AUDIO(DeviceAndPanelPendingType.DB_VALUE_PANEL_AUDIO),    // 2
    PANEL_ON_OFF(DeviceAndPanelPendingType.DB_VALUE_PANEL_ON_OFF);  // 3

    companion object {
        const val DB_VALUE_DEVICE_AUDIO = 1
        const val DB_VALUE_PANEL_AUDIO = 2
        const val DB_VALUE_PANEL_ON_OFF = 3

        private val dbMap: HashMap<Int, DeviceAndPanelPendingType> = HashMap(3)

        init {
            for (en in values()) dbMap[en.dbValue] = en
        }

        fun enumFromDbValue(dbValue: Int?): DeviceAndPanelPendingType? = dbMap[dbValue]
    }
}

@Converter(autoApply = true)
open class DeviceAndPanelPendingTypeDbConverter :
    AttributeConverter<DeviceAndPanelPendingType, Int> {

    override fun convertToDatabaseColumn(
        deviceAndPanelPendingType: DeviceAndPanelPendingType?
    ): Int? = deviceAndPanelPendingType?.dbValue

    override fun convertToEntityAttribute(dbData: Int?): DeviceAndPanelPendingType? {
        return if (null == dbData) null else DeviceAndPanelPendingType.enumFromDbValue(dbData)
    }
}