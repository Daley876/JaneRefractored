package com.example.jane.repository

import android.util.Log
import com.example.jane.models.StarWarsCharacterProfile
import com.example.jane.network.CharacterApiService
import com.example.jane.utils.NETWORK_ERROR_TAG
import com.example.jane.utils.PROFILE_NETWORK_ERROR_TAG
import com.example.jane.utils.ResponseStates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StarWarsRepositoryImpl @Inject constructor(
    private val apiService: CharacterApiService
) : CharacterDataRepository {

    override suspend fun getStarWarsCharactersFromApi(): Flow<ResponseStates> {
        return flow {
            emit(ResponseStates.OnResponseLoading) //shows loading before api data is retrieved
            try {
                val response = apiService.getAllCharacters()
                if (response.isSuccessful) {
                    response.body()?.let { listOfCharacters ->
                        emit(ResponseStates.OnResponseSuccess(listOfCharacters)) //shows data if available
                    } ?: emit(ResponseStates.OnResponseError("No Data Retrieved From Network"))
                } else {
                    emit(ResponseStates.OnResponseError("Error Encountered When Fetching Character List"))
                    throw Exception("Error Encountered When Fetching Characters")
                }

            } catch (e: Exception) {
                emit(ResponseStates.OnResponseError("Error Encountered When Fetching Character List"))
                Log.e(NETWORK_ERROR_TAG, e.toString())
            }
        }
    }

    override suspend fun getCharacterProfileFromApi(characterID: String): Flow<ResponseStates> {
        return flow {
            emit(ResponseStates.OnResponseLoading) //shows loading before api data is retrieved
            try {
                val response = apiService.getCharacterById(characterID)
                if (response?.isSuccessful == true) {
                    response.body()?.let { profileData ->
                        val reformattedData = capitalizeFirstChar(profileData)
                        emit(ResponseStates.OnResponseSuccess(reformattedData)) //shows data if available
                    } ?: emit(ResponseStates.OnResponseError("No Data Retrieved From Network"))
                } else {
                    emit(ResponseStates.OnResponseError("Error Encountered When Fetching Profile Data"))
                    throw Exception("Error Encountered When Fetching Profile Data")
                }

            } catch (e: Exception) {
                emit(ResponseStates.OnResponseError("Error Encountered When Fetching Profile Data"))
                Log.e(PROFILE_NETWORK_ERROR_TAG, e.toString())
            }
        }
    }

    private fun capitalizeFirstChar(person: StarWarsCharacterProfile): StarWarsCharacterProfile {
        val mName = person.name.replaceFirstChar { it.uppercase() }
        person.name = mName

        val mGender = person.gender.replaceFirstChar { it.uppercase() }
        person.gender = mGender

        val mSpecies = person.species?.replaceFirstChar { it.uppercase() }
        person.species = mSpecies

        val mEyes = person.eyeColor?.replaceFirstChar { it.uppercase() }
        person.eyeColor = mEyes

        val mSkin = person.skinColor?.replaceFirstChar { it.uppercase() }
        person.skinColor = mSkin

        val mHair = person.hairColor?.replaceFirstChar { it.uppercase() }
        person.hairColor = mHair

        val mBirthLoc = person.bornLocation?.replaceFirstChar { it.uppercase() }
        person.bornLocation = mBirthLoc

        val mDeathLoc = person.diedLocation?.replaceFirstChar { it.uppercase() }
        person.diedLocation = mDeathLoc

        val mHomeWorld = when (person.homeWorld) {
            is String -> (person.homeWorld as String).replaceFirstChar { it.uppercase() }
            is List<*> -> (person.homeWorld as List<*>).joinToString(", ")
            else -> "Unknown"
        }
        person.homeWorld = mHomeWorld

        return person
    }
}