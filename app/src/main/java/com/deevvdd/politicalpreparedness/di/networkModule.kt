package com.deevvdd.politicalpreparedness.di

import com.deevvdd.politicalpreparedness.BuildConfig
import com.deevvdd.politicalpreparedness.data.api.ApiService
import com.deevvdd.politicalpreparedness.data.api.RetrofitProvider
import com.deevvdd.politicalpreparedness.data.api.jsonAdapter.ElectionAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.*

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
val networkModule = module {
    single {
        Moshi.Builder()
            .add(ElectionAdapter())
            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    single {
        RetrofitProvider(get(), BuildConfig.API_URL).provide()
    }
    single {
        get<Retrofit>().create(ApiService::class.java)
    }
}