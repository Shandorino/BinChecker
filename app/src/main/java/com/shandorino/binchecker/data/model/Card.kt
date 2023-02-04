package com.shandorino.binchecker.data.model


data class Card(
    val id: Int?,
    val bank: Bank?,
    val brand: String?,
    val country: Country?,
    val number: Number?,
    val prepaid: Boolean?,
    val scheme: String?,
    val type: String?,
    val bin: String
){
    fun toCardEntity() = CardEntity(
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
