package com.lugloc.services.model.response

sealed class UseCaseResponse<out T : Any> {
    open class Success<out T : Any>(val data: T) : UseCaseResponse<T>()
    open class Failure(val exception: Exception) : UseCaseResponse<Nothing>()
    open class FailureMessage<out T : Any>(val data: T) : UseCaseResponse<T>()
}