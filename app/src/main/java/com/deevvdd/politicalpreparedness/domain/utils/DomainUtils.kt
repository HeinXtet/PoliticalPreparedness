package com.deevvdd.politicalpreparedness.domain.utils

import com.deevvdd.politicalpreparedness.domain.model.response.Election
import com.deevvdd.politicalpreparedness.domain.model.response.SavedElection

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */


fun Election.toSavedElection() = SavedElection(
    id = this.id,
    name = this.name,
    electionDay = this.electionDay,
    ocdDivisionId = this.ocdDivisionId
)

fun SavedElection.toElection() = Election(
    id = this.id,
    name = this.name,
    electionDay = this.electionDay,
    ocdDivisionId = this.ocdDivisionId
)