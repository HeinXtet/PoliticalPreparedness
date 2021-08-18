package com.deevvdd.politicalpreparedness.domain.repository

import androidx.lifecycle.LiveData
import com.deevvdd.politicalpreparedness.domain.model.response.Election
import com.deevvdd.politicalpreparedness.domain.model.response.SavedElection
import com.deevvdd.politicalpreparedness.domain.model.response.VoterInfoModel
import com.deevvdd.politicalpreparedness.domain.state.Result

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
interface ElectionRepository {
    fun getElections(): LiveData<List<Election>>
    fun getSavedElections(): LiveData<List<SavedElection>>


    suspend fun fetchElections(): Result<List<Election>>
    suspend fun fetchVoterInfo(electionId: String, address: String): Result<VoterInfoModel>
    suspend fun insertElections(elections: List<Election>)
    suspend fun updateElection(election: Election)
    suspend fun saveElection(election: SavedElection)
    suspend fun deleteElection(election: SavedElection)

}