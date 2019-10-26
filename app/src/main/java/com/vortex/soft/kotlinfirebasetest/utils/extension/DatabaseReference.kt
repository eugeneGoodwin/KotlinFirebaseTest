package com.vortex.soft.kotlinfirebasetest.utils.extension

import com.google.firebase.database.*
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException


private suspend fun <T : Any> readReference(
    reference: DatabaseReference,
    type: Class<T> ): T = suspendCancellableCoroutine { continuation ->
    val listener = object : ValueEventListener {

        override fun onCancelled(error: DatabaseError) {
            continuation.resumeWithException(error.toException())
        }

        override fun onDataChange(snapshot: DataSnapshot) {
            try {
                val data: T? = snapshot.getValue(type)
                continuation.resume(data!!)
            } catch (exception: Exception) {
                continuation.resumeWithException(exception)
            }
        }
    }
    continuation.invokeOnCancellation { reference.removeEventListener(listener) }
    reference.addListenerForSingleValueEvent(listener)
}

suspend fun <T : Any> DatabaseReference.readValue(type: Class<T>): T = readReference(this, type)

suspend inline fun <reified T : Any> DatabaseReference.readValue(): T = readValue(T::class.java)

private suspend fun <T : Any> readReferences(
    reference: DatabaseReference,
    type: Class<T> ): List<T> = suspendCancellableCoroutine { continuation ->
    val listener = object : ValueEventListener {

        override fun onCancelled(error: DatabaseError) {
            continuation.resumeWithException(error.toException())
        }

        override fun onDataChange(snapshot: DataSnapshot) {
            try {
                val data: List<T> = snapshot.children.toHashSet().map { it.getValue(type)!! }
                continuation.resume(data)
            } catch (exception: Exception) {
                continuation.resumeWithException(exception)
            }
        }
    }
    continuation.invokeOnCancellation{ reference.removeEventListener(listener) }
    reference.addListenerForSingleValueEvent(listener)
}

suspend fun <T : Any> DatabaseReference.readList(type: Class<T>): List<T> = readReferences(this, type)

suspend inline fun <reified T : Any> DatabaseReference.readList(): List<T> = readList(T::class.java)

suspend fun <T : Any> DatabaseReference.saveValue(value: T): Unit =
    setValue(value).await().let { Unit }

suspend fun <T : Any> DatabaseReference.pushValue(value: T): DatabaseReference {
    val ref = push()
    ref.saveValue(value)
    return ref
}

private suspend fun < T : Any> readMapReferences(
    reference: DatabaseReference,
    type: Class<T> ): Map<String, T> = suspendCancellableCoroutine { continuation ->
    val listener = object : ValueEventListener {

        override fun onCancelled(error: DatabaseError) {
            continuation.resumeWithException(error.toException())
        }

        override fun onDataChange(snapshot: DataSnapshot) {
            try {
                val data: Map<String, T> = snapshot.children.map { it.key!! to it.getValue(type)!! }.toMap()
                continuation.resume(data)
            } catch (exception: Exception) {
                continuation.resumeWithException(exception)
            }
        }
    }
    continuation.invokeOnCancellation{ reference.removeEventListener(listener) }
    reference.addListenerForSingleValueEvent(listener)
}

suspend fun <T : Any> DatabaseReference.readMap(type: Class<T>): Map<String, T> = readMapReferences(this, type)

suspend inline fun <reified T : Any> DatabaseReference.readMap(): Map<String, T> = readMap(T::class.java)

private suspend fun <T : Any> awaitQuerySingleValue(query: Query, type: Class<T>): T =
    suspendCancellableCoroutine { continuation ->
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) = try {
                continuation.resume(snapshot.getValue(type)!!)
            } catch (exception: Exception) {
                continuation.resumeWithException(exception)
            }

            override fun onCancelled(error: DatabaseError) =
                continuation.resumeWithException(error.toException())
        }
        query.addListenerForSingleValueEvent(listener)
        continuation.invokeOnCancellation { query.removeEventListener(listener) }
    }

suspend fun <T : Any> Query.readValue(type: Class<T>): T = awaitQuerySingleValue(this, type)

suspend inline fun <reified T : Any> Query.readValue(): T = readValue(T::class.java)

private suspend fun <T : Any> awaitQueryListValue(query: Query, type: Class<T>): List<T> =
    suspendCancellableCoroutine { continuation ->
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) = try {
                val data: List<T> = snapshot.children.toHashSet().map { it.getValue(type)!! }
                continuation.resume(data)
            } catch (exception: Exception) {
                continuation.resumeWithException(exception)
            }

            override fun onCancelled(error: DatabaseError) =
                continuation.resumeWithException(error.toException())
        }
        query.addListenerForSingleValueEvent(listener)
        continuation.invokeOnCancellation { query.removeEventListener(listener) }
    }

suspend fun <T : Any> Query.readList(type: Class<T>): List<T> = awaitQueryListValue(this, type)

suspend inline fun <reified T : Any> Query.readList(): List<T> = readList(T::class.java)