package com.deevvdd.politicalpreparedness.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.deevvdd.politicalpreparedness.domain.model.response.Election

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
@Dao
interface ElectionDao {

    @Insert(onConflict = REPLACE)
    fun insertElections(elections: List<Election>)

    @Query("SELECT * FROM election")
    fun getAll(): LiveData<List<Election>>

    @Update
    fun updateElection(election: Election)

}