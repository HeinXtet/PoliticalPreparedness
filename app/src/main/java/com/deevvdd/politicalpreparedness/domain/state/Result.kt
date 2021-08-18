package com.deevvdd.politicalpreparedness.domain.state

/**
 * Created by heinhtet deevvdd@gmail.com on 17,August,2021
 */
sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val error: String) : Result<Nothing>()
}