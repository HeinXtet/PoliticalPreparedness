package com.deevvdd.politicalpreparedness.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.deevvdd.politicalpreparedness.data.api.state.DataState
import com.deevvdd.politicalpreparedness.domain.model.response.Election
import com.deevvdd.politicalpreparedness.domain.model.response.SavedElection
import com.deevvdd.politicalpreparedness.domain.model.response.VoterInfoModel
import com.deevvdd.politicalpreparedness.domain.repository.ElectionRepository
import com.deevvdd.politicalpreparedness.domain.repository.LocalDataSource
import com.deevvdd.politicalpreparedness.domain.repository.RemoteDataSource
import com.deevvdd.politicalpreparedness.domain.state.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
class ElectionRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ElectionRepository, BaseRepository() {
    override fun getElections(): LiveData<List<Election>> {
        return localDataSource.getElections()
    }

    override fun getSavedElections(): LiveData<List<SavedElection>> {
        return localDataSource.getSavedElections()
    }

    override suspend fun fetchElections(): Result<List<Election>> = withContext(Dispatchers.IO) {
        return@withContext when (val state = getData { remoteDataSource.fetchElections() }) {
            is DataState.Success -> {
                Timber.d("elections from remote ${state.dataResponse.data?.elections}")
                insertElections(state.dataResponse.data?.elections.orEmpty())
                Result.Success(state.dataResponse.data?.elections.orEmpty())
            }
            is DataState.Error -> {
                Timber.d("getElections failure ${state.dataResponse.errorMessage}")
                Result.Error(state.dataResponse.errorMessage.orEmpty())
            }
        }
    }


    override suspend fun fetchVoterInfo(
        electionId: String,
        address: String
    ): Result<VoterInfoModel> = withContext(Dispatchers.IO) {
        return@withContext when (val state =
            getData { remoteDataSource.fetchVoterInfo(electionId, address) }) {
            is DataState.Success -> {
                Timber.d("voterInfo from remote ${state.dataResponse.data}")
                Result.Success(state.dataResponse.data!!)
            }
            is DataState.Error -> {
                Timber.d("voterInfo failure ${state.dataResponse.errorMessage}")
                Result.Error(state.dataResponse.errorMessage.orEmpty())
            }
        }
    }

    override suspend fun insertElections(elections: List<Election>) {
        localDataSource.insertElections(elections)
    }

    override suspend fun updateElection(election: Election) = withContext(Dispatchers.IO) {
        localDataSource.updateElection(election)
    }

    override suspend fun saveElection(election: SavedElection) = withContext(Dispatchers.IO) {
        try {
            localDataSource.saveElection(election)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun deleteElection(election: SavedElection) = withContext(Dispatchers.IO) {
        try {
            localDataSource.deleteElection(election)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}