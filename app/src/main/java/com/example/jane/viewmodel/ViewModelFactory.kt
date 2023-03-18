package com.example.jane.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jane.repository.StarWarsRepositoryImpl

class ViewModelFactory (private val repository: StarWarsRepositoryImpl) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) : T {

        if (modelClass.isAssignableFrom(StarWarsViewModel::class.java)) {
            return StarWarsViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}