package com.deevvdd.politicalpreparedness.domain.repository

import com.deevvdd.politicalpreparedness.data.api.state.DataState
import com.deevvdd.politicalpreparedness.domain.model.response.ElectionModel
import com.deevvdd.politicalpreparedness.domain.model.response.RepresentativeModel
import com.deevvdd.politicalpreparedness.domain.model.response.VoterInfoModel
import retrofit2.Response

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
interface RemoteDataSource {
    suspend fun fetchElections(): Response<ElectionModel>
    suspend fun fetchVoterInfo(electionId : String,address : String): Response<VoterInfoModel>
    suspend fun fetchRepresentative(address : String): Response<RepresentativeModel>
}