package com.shandorino.binchecker.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shandorino.binchecker.data.model.CardEntity

@Dao
interface CardDao {

    @Query("SELECT * FROM card")
    suspend fun getCards(): List<CardEntity>

    @Query("SELECT * FROM card WHERE id IN (:cardId)")
    suspend fun getCard(cardId: Int): CardEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: CardEntity)

}