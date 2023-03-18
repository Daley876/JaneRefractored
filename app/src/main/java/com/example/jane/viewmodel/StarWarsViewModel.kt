package com.example.jane.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jane.models.StarWarsCharacter
import com.example.jane.repository.StarWarsRepositoryImpl
import com.example.jane.utils.ResponseStates
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class StarWarsViewModel @Inject constructor(
    private val repo : StarWarsRepositoryImpl
): ViewModel() {

    init {
        fetchAllCharacters()
    }

    private val listOfStarWarsCharacters : MutableLiveData<ResponseStates> = MutableLiveData()
    val listOfCharactersLiveData : LiveData<ResponseStates> get() = listOfStarWarsCharacters

    private val profileDataForCharacter : MutableLiveData<ResponseStates> = MutableLiveData()
    val profileOfCharacterLiveData  : LiveData<ResponseStates> get() = profileDataForCharacter

    var selectedCharacter: StarWarsCharacter? = null


    fun setSelectedStarWarsCharacter (character: StarWarsCharacter?) {
        selectedCharacter = character
        profileDataForCharacter.value = ResponseStates.OnResponseLoading
    }

    private fun fetchAllCharacters() {
        CoroutineScope(Dispatchers.IO).launch{
            repo.getStarWarsCharactersFromApi().collect { response ->
                listOfStarWarsCharacters.postValue(response)
            }
        }
    }

    fun fetchProfileDataForCharacter(characterID : String) {
        CoroutineScope(Dispatchers.IO).launch{
            repo.getCharacterProfileFromApi(characterID).collect { response ->
                profileDataForCharacter.postValue(response)
            }
        }
    }
}