package com.shandorino.binchecker.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.shandorino.binchecker.data.database.converters.BankConverter
import com.shandorino.binchecker.data.database.converters.CountryConverter
import com.shandorino.binchecker.data.database.converters.NumberConverter

@Entity(tableName = "CARD")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    @ColumnInfo(name = "bank")
    @TypeConverters(BankConverter::class)
    val bank: Bank?,
    @ColumnInfo(name = "brand")
    val brand: String?,
    @ColumnInfo(name = "country")
    @TypeConverters(CountryConverter::class)
    val country: Country?,
    @ColumnInfo(name = "number")
    @TypeConverters(NumberConverter::class)
    val number: Number?,
    @ColumnInfo(name = "prepaid")
    val prepaid: Boolean?,
    @ColumnInfo(name = "scheme")
    val scheme: String?,
    @ColumnInfo(name = "type")
    val type: String?,
    @ColumnInfo(name = "bin")
    val bin: String
) {
    fun toCard() = Card(
        id = id,
        bank = bank,
        brand = brand,
        country = country,
        number = number,
        prepaid = prepaid,
        scheme = scheme,
        type = type,
        bin = bin
    )
}