package com.example.jane.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.jane.models.StarWarsCharacter
import com.example.jane.repository.StarWarsRepositoryImpl
import com.example.jane.utils.ResponseStates
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class StarWarsViewModelTestMain{
    lateinit var viewModel: StarWarsViewModel
    lateinit var mockRepository: StarWarsRepositoryImpl

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @Before
     fun setUp() {
        mockRepository = mockk()
        viewModel = StarWarsViewModel(mockRepository)
    }

    @Test
    fun `test if character is updated in ViewModel once new character is passed to setCharacter func`() {
        val testChar = StarWarsCharacter(1,"Tester","Human","",false)
        viewModel.setSelectedStarWarsCharacter(testChar)
        val updatedCharacter = viewModel.selectedCharacter
        assertEquals(testChar, updatedCharacter)
    }

    @Test
    fun `test get character data list network call function - Success`() {
        val response = mutableListOf<StarWarsCharacter>()
        val testChar = StarWarsCharacter(1,"Tester","Human","",false)
        response.add(testChar)
        val responseType = ResponseStates.OnResponseSuccess(response)

        runBlocking {
            coEvery { mockRepository.getStarWarsCharactersFromApi() } returns  flow { emit(responseType) }
        }
        viewModel.fetchAllCharacters()

        val actualData = viewModel.listOfStarWarsCharacters.value
        val expectedData = ResponseStates.OnResponseSuccess(mutableListOf<StarWarsCharacter>())
        when(actualData) {
            is ResponseStates.OnResponseSuccess<*> -> assertEquals(expectedData, actualData.response as MutableList<StarWarsCharacter>)
            else -> {}
        }

    }

    @Test
    fun `test get character data list network call function - Loading`() {
        val responseType = ResponseStates.OnResponseLoading

        runBlocking {
            coEvery { mockRepository.getStarWarsCharactersFromApi() } returns  flow { emit(responseType) }
        }
        viewModel.fetchAllCharacters()

        val actualData = viewModel.listOfStarWarsCharacters.value
        val expectedData = ResponseStates.OnResponseLoading

        when(actualData) {
            is ResponseStates.OnResponseLoading -> assertEquals(expectedData, actualData)
            else -> {}
        }
    }
}