package com.gtplanner.usecase.utils

import java.lang.Exception

suspend inline fun tryTo(crossinline action: suspend () -> Unit) : Exception? = try {
    action()
    null
} catch (e: Exception){
    e
}