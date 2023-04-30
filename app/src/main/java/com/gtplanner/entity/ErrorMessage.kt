package com.gtplanner.entity

data class ErrorMessage(val message: String){

    companion object {
        val DEFAULT = ErrorMessage(String.empty)
    }
}
