package com.deevvdd.politicalpreparedness.data.api.jsonAdapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by heinhtet deevvdd@gmail.com on 18,August,2021
 */
class DateAdapter {
    @FromJson
    fun dateFromJson(electionDay: String): Date {
        val pattern = "yyyy-MM-dd"
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return simpleDateFormat.parse(electionDay)!!
    }
    @ToJson
    fun dateToJson(electionDay: Date) : String {
        val pattern = "yyyy-MM-dd"
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        return simpleDateFormat.format(electionDay)
    }
}
