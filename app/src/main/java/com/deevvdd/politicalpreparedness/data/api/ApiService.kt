package com.deevvdd.politicalpreparedness.data.api

import com.deevvdd.politicalpreparedness.domain.model.response.ElectionModel
import com.deevvdd.politicalpreparedness.domain.model.response.RepresentativeModel
import com.deevvdd.politicalpreparedness.domain.model.response.VoterInfoModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
interface ApiService {
    @GET("elections")
    suspend fun getElections(): Response<ElectionModel>

    @GET("voterinfo")
    suspend fun getVoterInfo(
        @Query("electionId") id: String,
        @Query("address") address: String
    ): Response<VoterInfoModel>


    @GET("representatives")
    suspend fun getRepresentativesAsync(
        @Query("address") address: String
    ): Response<RepresentativeModel>
}