package com.example.jane.repository

import com.example.jane.utils.ResponseStates
import kotlinx.coroutines.flow.Flow

interface CharacterDataRepository {

    //repository methods that will be overridden as needed
    suspend fun getStarWarsCharactersFromApi() : Flow<ResponseStates>
    suspend fun getCharacterProfileFromApi(characterID : String) : Flow<ResponseStates>

}