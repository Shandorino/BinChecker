package com.shandorino.binchecker.data.database.converters

import androidx.room.TypeConverter
import com.shandorino.binchecker.data.model.Number

class NumberConverter {

    @TypeConverter
    fun toSimple(number: Number): String {
        return "${number.length},${number.luhn}"
    }

    @TypeConverter
    fun toComplex(data: String): Number {
        val numberString = data.split(",")

        return Number(length = if(numberString[0] == "null") 0 else numberString[0].toInt(), luhn = numberString[1].toBoolean())
    }

}