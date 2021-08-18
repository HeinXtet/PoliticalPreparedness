package com.deevvdd.politicalpreparedness.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.deevvdd.politicalpreparedness.domain.model.response.Election
import com.deevvdd.politicalpreparedness.domain.model.response.SavedElection

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
@Database(
    entities = [Election::class, SavedElection::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun electionDao(): ElectionDao
    abstract fun savedElectionDao(): SavedElectionDao
}
