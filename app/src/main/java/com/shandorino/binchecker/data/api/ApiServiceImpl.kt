package com.shandorino.binchecker.data.api

import com.google.gson.Gson
import com.shandorino.binchecker.data.common.RemoteContract
import com.shandorino.binchecker.data.model.CardApi
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class ApiServiceImpl(
    override val httpClient: HttpClient
): ApiService {


    override suspend fun getCard(bin: Int): CardApi {
        val gson = Gson()
        val response: HttpResponse = httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                method = HttpMethod.Get
                host = RemoteContract.binListBaseUrl
                encodedPath = bin.toString()
            }
        }
        return gson.fromJson(response.bodyAsText(), CardApi::class.java)
    }

}