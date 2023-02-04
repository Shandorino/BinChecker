package com.shandorino.binchecker.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shandorino.binchecker.data.model.CardEntity

@Database(entities = [CardEntity::class], version = 1, exportSchema = false)
abstract class DataBase: RoomDatabase() {

    abstract fun cardDao(): CardDao

}