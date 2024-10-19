package com.spacepulse.core_module.utility

//sealed class for representing various states
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: ErrorType) : Result<Nothing>()
    data object Loading : Result<Nothing>()
}