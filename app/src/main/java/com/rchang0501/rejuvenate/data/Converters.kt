package com.rchang0501.rejuvenate.data

import androidx.room.TypeConverter
import java.util.*

// converts Date type to Long type that can be interpreted by Room
class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): Calendar? {
        if (value != null) {
            val c = Calendar.getInstance()
            c.timeInMillis = value
            return c
        }
        return null
    }

    @TypeConverter
    fun dateToTimestamp(date: Calendar?): Long? {
        if (date != null) {
            return date.timeInMillis
        }
        return null
    }
}