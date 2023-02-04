package com.shandorino.binchecker.di



import com.shandorino.binchecker.data.api.ApiService
import com.shandorino.binchecker.data.api.ApiServiceImpl
import com.shandorino.binchecker.di.repositories.ApiRepository
import com.shandorino.binchecker.di.repositories.impl.ApiRepositoryImpl
import io.ktor.client.*
import io.ktor.client.engine.android.*
import org.koin.dsl.module


val ApiModule = module {

    single<ApiRepository> {
        ApiRepositoryImpl(get())
    }

    single<ApiService>{
        ApiServiceImpl(get())
    }

    single {
        HttpClient(Android)
    }
}