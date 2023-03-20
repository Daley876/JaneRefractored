package com.example.jane.repository

import android.util.Log
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
                        emit(ResponseStates.OnResponseSuccess(profileData)) //shows data if available
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
}