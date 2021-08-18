package com.deevvdd.politicalpreparedness.data.api.jsonAdapter

/**
 * Created by heinhtet deevvdd@gmail.com on 18,August,2021
 */

import com.deevvdd.politicalpreparedness.domain.model.response.Division
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson

class RepresentativeAdapter {
    @FromJson
    fun divisionFromJson (ocdDivisionId: String): Division {
        val countryDelimiter = "country:"
        val stateDelimiter = "state:"
        val country = ocdDivisionId.substringAfter(countryDelimiter,"")
            .substringBefore("/")
        val state = ocdDivisionId.substringAfter(stateDelimiter,"")
            .substringBefore("/")
        return Division(ocdDivisionId, country, state)
    }

    @ToJson
    fun divisionToJson (division: Division): String {
        return division.id
    }
}