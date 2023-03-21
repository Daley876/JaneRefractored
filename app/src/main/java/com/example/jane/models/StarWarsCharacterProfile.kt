package com.example.jane.models

import com.google.gson.annotations.SerializedName

data class StarWarsCharacterProfile(
    @SerializedName("id")
    val characterId: Int,
    var name: String,
    val height: Double,
    var gender: String,
    val mass: Int,
    @SerializedName("bornLocation")
    var bornLocation: String?,
    @SerializedName("diedLocation")
    var diedLocation: String?,
    var species: String?,
    val image: String?,
    @SerializedName("hair_color")
    var hairColor: String?,
    @SerializedName("skin_color")
    var skinColor: String?,
    @SerializedName("eye_color")
    var eyeColor: String?,
    @SerializedName("homeworld")
    var homeWorld: Any?,
)
