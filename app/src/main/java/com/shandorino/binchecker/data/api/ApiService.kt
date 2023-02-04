package com.shandorino.binchecker.data.api

import com.shandorino.binchecker.data.model.CardApi
import io.ktor.client.*

interface ApiService {

    val httpClient: HttpClient

    suspend fun getCard(bin: Int): CardApi

}