package com.example.jane.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jane.R
import com.example.jane.databinding.CharactersListFragmentLayoutBinding
import com.example.jane.models.StarWarsCharacter
import com.example.jane.utils.ResponseStates

class CharactersListFragment : ViewModelFragment() {
    private lateinit var binding : CharactersListFragmentLayoutBinding
    private lateinit var mAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CharactersListFragmentLayoutBinding.inflate(inflater, container, false)
        initAdapter()
        initRecyclerView()
        initObserver()
        return binding.root
    }

    private fun initObserver() {
        viewModel.listOfCharactersLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseStates.OnResponseSuccess<*> -> {
                    binding.apply {
                        progressBar.visibility = View.GONE
                        errorText.visibility = View.GONE
                    }
                    val newList = state.response as MutableList<StarWarsCharacter>
                    mAdapter.updateCharacterList(newList)
                }
                is ResponseStates.OnResponseError -> {
                    binding.apply {
                        progressBar.visibility = View.GONE
                        errorText.visibility = View.VISIBLE
                        errorText.text = getString(R.string.error_fetching_list)
                    }
                }
                is ResponseStates.OnResponseLoading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.errorText.visibility = View.GONE
                }
            }
        }
    }

    private fun setCurrentCharacter (character: StarWarsCharacter) {
        viewModel.setSelectedStarWarsCharacter(character)
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_view, CharacterProfileFragment())
            .addToBackStack("CHARACTER_LIST_FRAGMENT")
            .commit()
    }

    private fun initAdapter() {
        mAdapter = CharacterAdapter(mutableListOf(), currentCharacter = ::setCurrentCharacter)
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }
}