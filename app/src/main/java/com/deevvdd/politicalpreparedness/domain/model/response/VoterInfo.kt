package com.deevvdd.politicalpreparedness.domain.model.response

import com.deevvdd.politicalpreparedness.domain.model.response.Address
import com.squareup.moshi.JsonClass

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */


@JsonClass(generateAdapter = true)
class VoterInfoModel(
    val election: Election,
    val state: List<State>? = null,
)

@JsonClass(generateAdapter = true)
data class State(
    val name: String,
    val electionAdministrationBody: AdministrationBody
)

@JsonClass(generateAdapter = true)
data class AdministrationBody(
    val name: String? = null,
    val electionInfoUrl: String? = null,
    val votingLocationFinderUrl: String? = null,
    val ballotInfoUrl: String? = null,
    val correspondenceAddress: Address? = null
)