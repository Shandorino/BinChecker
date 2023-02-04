package com.shandorino.binchecker.data.model


import com.google.gson.annotations.SerializedName

data class CardApi(
    @SerializedName("bank")
    val bank: Bank?,
    @SerializedName("brand")
    val brand: String?,
    @SerializedName("country")
    val country: Country?,
    @SerializedName("number")
    val number: Number?,
    @SerializedName("prepaid")
    val prepaid: Boolean?,
    @SerializedName("scheme")
    val scheme: String?,
    @SerializedName("type")
    val type: String?
) {
    fun toCard(bin: String) = Card(
        id = null,
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