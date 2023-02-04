package com.shandorino.binchecker.di.repositories

import com.shandorino.binchecker.data.model.Card
import kotlinx.coroutines.flow.Flow

interface DataBaseRepository {

    fun getCards(): Flow<List<Card>>

    fun getCard(id: Int): Flow<Card>

    fun insertCard(card: Card): Flow<String>

}