package com.deevvdd.politicalpreparedness.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.deevvdd.politicalpreparedness.domain.model.response.Election
import com.deevvdd.politicalpreparedness.domain.model.response.SavedElection

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
@Dao
interface SavedElectionDao {

    @Insert(onConflict = REPLACE)
    fun saveElection(elections: SavedElection)

    @Query("SELECT * FROM savedelection")
    fun getAll(): LiveData<List<SavedElection>>

    @Delete
    fun deleteElection(election: SavedElection)

}