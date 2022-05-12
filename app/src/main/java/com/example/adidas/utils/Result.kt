package com.example.adidas.utils

import java.lang.Exception

sealed class Result<out A>{
    abstract fun getOr(f: () -> @UnsafeVariance A): A
    data class Success<out T>(private val value: T): Result<T>() {
        override fun getOr(f: () -> @UnsafeVariance T): T = value
        fun get() = value
    }

    data class Error(private val error: Exception): Result<Nothing>() {
        override fun getOr(f: () -> Nothing): Nothing = f()
        fun get() = error
    }
}
