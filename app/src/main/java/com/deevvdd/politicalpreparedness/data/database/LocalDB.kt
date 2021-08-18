package com.deevvdd.politicalpreparedness.data.database

import android.content.Context
import androidx.room.Room

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
object LocalDB {
    fun createAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "politicalpreparedness.db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}