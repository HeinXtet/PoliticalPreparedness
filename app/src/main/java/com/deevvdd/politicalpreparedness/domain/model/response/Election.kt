package com.deevvdd.politicalpreparedness.domain.model.response

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
@JsonClass(generateAdapter = true)
data class ElectionModel(
    val elections: List<Election>
)

@Entity
@Parcelize
@JsonClass(generateAdapter = true)
data class Election(
    @PrimaryKey
    val id: String,
    val name: String? = null,
    val electionDay: String? = null,
    @Embedded(prefix = "division_")
    @Json(name = "ocdDivisionId")
    val ocdDivisionId: Division? = null
) : Parcelable

@Entity
@JsonClass(generateAdapter = true)
data class SavedElection(
    @PrimaryKey
    val id: String,
    val name: String? = null,
    val electionDay: String? = null,
    @Embedded(prefix = "division_")
    val ocdDivisionId: Division? = null
)