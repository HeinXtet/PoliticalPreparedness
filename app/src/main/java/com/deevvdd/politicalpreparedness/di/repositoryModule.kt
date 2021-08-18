package com.deevvdd.politicalpreparedness.di

import com.deevvdd.politicalpreparedness.data.repository.ElectionRepositoryImpl
import com.deevvdd.politicalpreparedness.data.repository.LocalDataSourceImpl
import com.deevvdd.politicalpreparedness.data.repository.RemoteDataSourceImpl
import com.deevvdd.politicalpreparedness.data.repository.RepresentativeRepositoryImpl
import com.deevvdd.politicalpreparedness.domain.repository.ElectionRepository
import com.deevvdd.politicalpreparedness.domain.repository.LocalDataSource
import com.deevvdd.politicalpreparedness.domain.repository.RemoteDataSource
import com.deevvdd.politicalpreparedness.domain.repository.RepresentativeRepository
import org.koin.dsl.module

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
val repositoryModule = module {
    single<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    single<LocalDataSource> { LocalDataSourceImpl(get(), get()) }
    single<ElectionRepository> { ElectionRepositoryImpl(get(), get()) }
    single<RepresentativeRepository> { RepresentativeRepositoryImpl(get()) }
}