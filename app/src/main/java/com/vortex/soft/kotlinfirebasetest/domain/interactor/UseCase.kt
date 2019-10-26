package com.vortex.soft.kotlinfirebasetest.domain.interactor

import com.vortex.soft.kotlinfirebasetest.data.repository.Error
import com.vortex.soft.kotlinfirebasetest.utils.functional.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Error, Type>

    operator fun invoke(scope: CoroutineScope, params: Params, onResult: (Either<Error, Type>) -> Unit = {}) = with(scope) {
        try {
            val job = async(Dispatchers.Default) { run(params) }
            launch(Dispatchers.Main) { onResult(job.await()) }
        } catch (exception: Exception) {
            onResult(Either.Left(Error.UndefineError(exception.message ?: "Undefine error")))
        }
    }

    class None
}