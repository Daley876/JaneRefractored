package com.example.jane.models

import com.google.gson.annotations.SerializedName

data class StarWarsCharacter(
    @SerializedName("id")
    val characterId : Int,
     var name: String,
     val height: Double,
     val gender: String,
     val mass : Int,
     var species: String?,
     val image: String?,
    @SerializedName("hair_color")
     var hairColor: String?,
    @SerializedName("skin_color")
     var skinColor: String?,
    @SerializedName("eye_color")
     var eyeColor: String?,
    @SerializedName("homeworld")
     var homeWorld: String?
)