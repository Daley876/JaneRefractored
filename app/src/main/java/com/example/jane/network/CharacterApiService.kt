package com.example.jane.network

import com.example.jane.models.StarWarsCharacter
import com.example.jane.models.StarWarsCharacterProfile
import com.example.jane.utils.CHARACTER_LIST_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApiService {
    @GET(CHARACTER_LIST_ENDPOINT)
    suspend fun getAllCharacters(): Response<MutableList<StarWarsCharacter>>

    @GET("id/{id}.json")
    suspend fun getCharacterById(@Path("id") id: String): Response<StarWarsCharacterProfile>?
}