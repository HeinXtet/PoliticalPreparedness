package com.deevvdd.politicalpreparedness.domain.repository

import androidx.lifecycle.LiveData
import com.deevvdd.politicalpreparedness.domain.model.response.Election
import com.deevvdd.politicalpreparedness.domain.model.response.SavedElection

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
interface LocalDataSource {
    fun getElections(): LiveData<List<Election>>
    fun getSavedElections(): LiveData<List<SavedElection>>
    suspend fun insertElections(elections: List<Election>)
    suspend fun updateElection(election: Election)
    suspend fun saveElection(election: SavedElection)
    suspend fun deleteElection(election: SavedElection)
}