package com.shandorino.binchecker.di.repositories.impl

import com.shandorino.binchecker.data.api.ApiService
import com.shandorino.binchecker.data.model.Card
import com.shandorino.binchecker.di.repositories.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ApiRepositoryImpl(
    private val apiService: ApiService
): ApiRepository {

    override fun getCard(bin: String): Flow<Card> = flow{
        val card = apiService.getCard(bin.toInt()).toCard(bin)
        emit(card)
    }
}