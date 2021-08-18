package com.deevvdd.politicalpreparedness.domain.model.response

import com.squareup.moshi.Json

/**
 * Created by heinhtet deevvdd@gmail.com on 18,August,2021
 */


data class RepresentativeModel(
    val offices: List<Office>,
    val officials: List<Official>
)

data class Office(
    val name: String,
    @Json(name = "divisionId") val division: Division,
    @Json(name = "officialIndices") val officials: List<Int>
) {
    fun getRepresentatives(officials: List<Official>): List<Representative> {
        return this.officials.map { index ->
            Representative(officials[index], this)
        }
    }
}

data class Representative(
    val official: Official,
    val office: Office
)

data class Official(
    val name: String,
    val address: List<Address>? = null,
    val party: String? = null,
    val phones: List<String>? = null,
    val urls: List<String>? = null,
    val photoUrl: String? = null,
    val channels: List<Channel>? = null
)

data class Channel(
    val type: String,
    val id: String
)