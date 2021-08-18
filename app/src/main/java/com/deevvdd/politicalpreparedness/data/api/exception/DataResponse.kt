package com.deevvdd.politicalpreparedness.data.api

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
sealed class DataResponse<Data>

data class DataSuccessResponse<Data>(
    val data: Data? = null,
    val message: String? = null
) : DataResponse<Data>()

data class DataErrorResponse<Data>(
    val statusCode: Int = 500,
    val apiTag: String? = null,
    val errorMessage: String? = null
) : DataResponse<Data>()
