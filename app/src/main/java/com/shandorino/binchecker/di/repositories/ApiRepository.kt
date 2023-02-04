package com.shandorino.binchecker.di.repositories

import com.shandorino.binchecker.data.model.Card
import kotlinx.coroutines.flow.Flow

interface ApiRepository {

    fun getCard(bin: String): Flow<Card>

}