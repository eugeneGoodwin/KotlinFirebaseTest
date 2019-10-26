package com.vortex.soft.kotlinfirebasetest.utils.functional

sealed class Either<out L, out R> {
    data class Left<out L>(val a: L) : Either<L, Nothing>()
    data class Right<out R>(val b: R) : Either<Nothing, R>()

    val isRight get() = this is Right<R>
    val isLeft get() = this is Left<L>

    fun <L> left(a: L) = Either.Left(a)
    fun <R> right(b: R) = Either.Right(b)

    fun fold(fnL: (L) -> Unit, fnR: (R) -> Unit): Unit =
            when (this) {
                is Left -> fnL(a)
                is Right -> fnR(b)
            }

    fun getRight(): R? =
            when (this) {
                is Left -> null
                is Right -> b
            }

    fun getLeft(): L? =
            when (this) {
                is Left -> a
                is Right -> null
            }
}

fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

fun <T, L, R> Either<L, R>.flatMap(fn: (R) -> Either<L, T>): Either<L, T> =
        when (this) {
            is Either.Left -> Either.Left(a)
            is Either.Right -> fn(b)
        }

fun <T, L, R> Either<L, R>.map(fn: (R) -> (T)): Either<L, T> = this.flatMap(fn.c(::right))