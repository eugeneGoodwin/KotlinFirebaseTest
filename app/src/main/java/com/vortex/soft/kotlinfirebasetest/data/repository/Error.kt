package com.vortex.soft.kotlinfirebasetest.data.repository

import com.vortex.soft.kotlinfirebasetest.utils.functional.Either

sealed class Error {
    val failure: Either<Throwable, String>

    constructor(error_message: String) {
        failure = Either.Right(error_message)
    }
    constructor(exception: Throwable) {
        failure = Either.Left(exception)
    }

    fun getMessage(): String {
        var errorMessage: String = ""
        failure.fold({
            errorMessage = it.message ?: ""
        }, {
            errorMessage = it
        })
        return errorMessage
    }

    class UndefineError: Error {
        constructor(error_message: String) : super(error_message)
        constructor(exception: Throwable) : super(exception)
    }
    class NetworkConnectionError: Error {
        constructor(error_message: String) : super(error_message)
        constructor(exception: Throwable) : super(exception)
    }
    class ServerError: Error {
        constructor(error_message: String) : super(error_message)
        constructor(exception: Throwable) : super(exception)
    }
    class FirebaseError: Error {
        constructor(error_message: String) : super(error_message)
        constructor(exception: Throwable) : super(exception)
    }
}