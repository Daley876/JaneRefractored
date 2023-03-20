package com.example.jane.utils

sealed class ResponseStates {
    /*This class represents how the UI will handle the different
stages of an API request that will show data*/
    class OnResponseSuccess<T>(val response: T) : ResponseStates()
    object OnResponseLoading : ResponseStates()
    class OnResponseError(val error: String) : ResponseStates()
}
