package com.deevvdd.politicalpreparedness.domain.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
@Parcelize
@JsonClass(generateAdapter = true)
data class Division(
    val id: String,
    val country: String,
    val state: String
) : Parcelable