package com.example.jane.models

import com.google.gson.annotations.SerializedName

data class StarWarsCharacter(
    @SerializedName("id")
    val characterId : Int,
    var name: String,
    var species: String?,
    val image: String?,
    var isFavorite : Boolean
)