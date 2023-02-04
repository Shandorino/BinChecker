package com.shandorino.binchecker.di.repositories.impl

import com.shandorino.binchecker.data.database.CardDao
import com.shandorino.binchecker.data.model.Card
import com.shandorino.binchecker.di.repositories.DataBaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DataBaseRepositoryImpl(
    private val cardDao: CardDao
): DataBaseRepository {
    override fun getCards(): Flow<List<Card>> = flow {
        println("gets")
        val cards = cardDao.getCards().map { it.toCard() }
        emit(cards)
    }

    override fun getCard(id: Int): Flow<Card> = flow {
        println("get")
        val card = cardDao.getCard(id)
        emit(card.toCard())
    }

    override fun insertCard(card: Card): Flow<String> = flow {
        cardDao.insertCard(card.toCardEntity())
        emit("")
    }
}