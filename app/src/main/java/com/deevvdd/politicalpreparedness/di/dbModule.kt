package com.deevvdd.politicalpreparedness.di

import androidx.room.Room
import com.deevvdd.politicalpreparedness.data.database.AppDatabase
import com.deevvdd.politicalpreparedness.data.database.LocalDB
import org.koin.dsl.module

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
val dbModule = module {
    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "PoliticalAppDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { (get() as AppDatabase).electionDao() }
    single { (get() as AppDatabase).savedElectionDao() }
}