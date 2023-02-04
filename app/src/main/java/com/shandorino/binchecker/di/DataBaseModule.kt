package com.shandorino.binchecker.di

import androidx.room.Room
import com.shandorino.binchecker.data.database.CardDao
import com.shandorino.binchecker.data.database.DataBase
import com.shandorino.binchecker.di.repositories.DataBaseRepository
import com.shandorino.binchecker.di.repositories.impl.DataBaseRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val DataBaseModule = module {

    single<DataBaseRepository> {
        DataBaseRepositoryImpl(get())
    }


    single<CardDao> {
        Room.databaseBuilder(
            context = androidContext(),
            DataBase::class.java,
            "cards"
        ).build().cardDao()
    }


}