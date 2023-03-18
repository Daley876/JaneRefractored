package com.example.jane.models

sealed class HomeWorld {
    data class SingleHomeWorld(val homeworld: String) : HomeWorld()
    data class ListHomeWorld(val homeworlds: List<String>) : HomeWorld()
}
