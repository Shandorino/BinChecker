package com.shandorino.binchecker.di

import com.shandorino.binchecker.view_model.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    includes(listOf(ApiModule, DataBaseModule))

    viewModel {
        MainViewModel(get(), get(), androidContext())
    }

}