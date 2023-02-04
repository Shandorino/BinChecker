package com.shandorino.binchecker.data.database.converters

import androidx.room.TypeConverter
import com.shandorino.binchecker.data.model.Country

class CountryConverter {

    @TypeConverter
    fun toSimple(country: Country): String {
        return "${country.alpha2},${country.currency},${country.emoji},${country.latitude},${country.longitude},${country.name},${country.numeric}"
    }

    @TypeConverter
    fun toComplex(data: String): Country {
        val countryString = data.split(",")
        return Country(alpha2 = countryString[0], currency = countryString[1], emoji = countryString[2], latitude = countryString[3].toInt(), longitude = countryString[4].toInt(), name = countryString[5], numeric = countryString[6])
    }
}