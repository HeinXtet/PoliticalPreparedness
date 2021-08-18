package com.deevvdd.politicalpreparedness.data.repository

import com.deevvdd.politicalpreparedness.data.api.ApiService
import com.deevvdd.politicalpreparedness.data.api.state.DataState
import com.deevvdd.politicalpreparedness.domain.model.response.ElectionModel
import com.deevvdd.politicalpreparedness.domain.model.response.RepresentativeModel
import com.deevvdd.politicalpreparedness.domain.model.response.VoterInfoModel
import com.deevvdd.politicalpreparedness.domain.repository.RemoteDataSource
import retrofit2.Response

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
class RemoteDataSourceImpl(private val apiService: ApiService) : RemoteDataSource {
    override suspend fun fetchElections(): Response<ElectionModel> {
        return apiService.getElections()
    }

    override suspend fun fetchVoterInfo(
        electionId: String,
        address: String
    ): Response<VoterInfoModel> {
        return apiService.getVoterInfo(electionId, address)
    }

    override suspend fun fetchRepresentative(address: String):
            Response<RepresentativeModel> {
        return apiService.getRepresentativesAsync(address)
    }
}