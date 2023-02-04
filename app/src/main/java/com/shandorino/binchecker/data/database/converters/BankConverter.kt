package com.shandorino.binchecker.data.database.converters

import androidx.room.TypeConverter
import com.shandorino.binchecker.data.model.Bank

class BankConverter {

    @TypeConverter
    fun toSimple(bank: Bank): String {
        return "${bank.city},${bank.name},${bank.phone},${bank.url}"
    }

    @TypeConverter
    fun toComplex(data: String): Bank {
        val bankString = data.split(",")
        return Bank(city = bankString[0], name = bankString[1], phone = bankString[2], url = bankString[3])
    }
}