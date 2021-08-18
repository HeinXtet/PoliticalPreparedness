package com.deevvdd.politicalpreparedness.data.repository

import com.deevvdd.politicalpreparedness.data.api.DataErrorResponse
import com.deevvdd.politicalpreparedness.data.api.DataSuccessResponse
import com.deevvdd.politicalpreparedness.data.api.exception.AuthException
import com.deevvdd.politicalpreparedness.data.api.exception.ErrorException.getErrorMessage
import com.deevvdd.politicalpreparedness.data.api.state.DataState
import org.json.JSONObject
import retrofit2.Response

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */

open class BaseRepository {
    suspend fun <T : Any> getData(callback: suspend () -> Response<T>): DataState<T> {
        return try {
            val response = callback()
            if (response.code() == StatusCode.SUCCESS) {
                DataState.Success(dataResponse = DataSuccessResponse(data = response.body()))
            } else {
                DataState.Error(
                    dataResponse = DataErrorResponse(
                        errorMessage = getErrorMessage(response)
                    )
                )
            }
        } catch (authException: AuthException) {
            DataState.Error(
                dataResponse = DataErrorResponse(
                    statusCode = authException.code,
                    errorMessage = authException.message
                )
            )
        } catch (e: Exception) {
            DataState.Error(
                dataResponse = DataErrorResponse(
                    statusCode = StatusCode.EXTERNAL_ERROR,
                    errorMessage = e.message
                )
            )
        }
    }

    object StatusCode {
        const val SUCCESS = 200
        const val EXTERNAL_ERROR = 500
    }
}