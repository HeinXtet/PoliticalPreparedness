package com.deevvdd.politicalpreparedness.data.api.state

import com.deevvdd.politicalpreparedness.data.api.DataErrorResponse
import com.deevvdd.politicalpreparedness.data.api.DataSuccessResponse

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
sealed class DataState<Data> {
    class Success<Data>(val dataResponse: DataSuccessResponse<Data>) : DataState<Data>()
    class Error<Data>(val dataResponse: DataErrorResponse<Data>) : DataState<Data>()
}
