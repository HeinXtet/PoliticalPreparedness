package com.deevvdd.politicalpreparedness.data.api.exception

import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

/**
 * Created by heinhtet deevvdd@gmail.com on 18,August,2021
 */

object ErrorException {
    fun <T> getErrorMessage(errorResponse: Response<T>): String {
        return try {
            val errorJson = JSONObject(errorResponse.errorBody()?.string())
            errorJson.getJSONObject("error")
                .getString("message")
        } catch (e: Exception) {
            "Can't able to parse the error message."
        }
    }
}