package com.deevvdd.politicalpreparedness.data.repository

import com.deevvdd.politicalpreparedness.data.api.state.DataState
import com.deevvdd.politicalpreparedness.domain.model.response.RepresentativeModel
import com.deevvdd.politicalpreparedness.domain.repository.RemoteDataSource
import com.deevvdd.politicalpreparedness.domain.repository.RepresentativeRepository
import com.deevvdd.politicalpreparedness.domain.state.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * Created by heinhtet deevvdd@gmail.com on 18,August,2021
 */

class RepresentativeRepositoryImpl(private val remoteDataSource: RemoteDataSource) :
    RepresentativeRepository, BaseRepository() {
    override suspend fun fetchRepresentative(address: String): Result<RepresentativeModel> =
        withContext(Dispatchers.IO) {
            return@withContext when (val state =
                getData { remoteDataSource.fetchRepresentative(address) }) {
                is DataState.Success -> {
                    Timber.d("representative from remote ${state.dataResponse.data}")
                    Result.Success(state.dataResponse.data!!)
                }
                is DataState.Error -> {
                    Timber.d("representative failure ${state.dataResponse.errorMessage}")
                    Result.Error(state.dataResponse.errorMessage.orEmpty())
                }
            }
        }
}