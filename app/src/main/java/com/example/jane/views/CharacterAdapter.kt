package com.example.jane.views

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.jane.models.StarWarsCharacter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jane.R
import com.example.jane.databinding.CharactersListItemLayoutBinding

class CharacterAdapter (
    characterList : MutableList<StarWarsCharacter>, currentCharacter : (StarWarsCharacter) -> Unit
        ) : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val listOfCharacters = characterList
    private val viewCurrentCharacter = currentCharacter

    private lateinit var binding: CharactersListItemLayoutBinding



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
       val context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        binding = CharactersListItemLayoutBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(listOfCharacters[position], viewCurrentCharacter)
    }

    override fun getItemCount(): Int {
       return listOfCharacters.size
    }


    fun updateCharacterList(characters: MutableList<StarWarsCharacter>) {
        listOfCharacters.clear()
        listOfCharacters.addAll(characters)
        notifyItemRangeInserted(0,itemCount)
    }
    inner class CharacterViewHolder(binding : CharactersListItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        private val name = binding.characterName
        private val species = binding.characterSpecies
        private val profileBtn = binding.profileBtn
        private val profileImg = binding.imageView
        fun bind(character : StarWarsCharacter, currentChar : (StarWarsCharacter) -> Unit) {
            name.text = character.name
            species.text = character.species
            profileBtn.setOnClickListener {
                currentChar(character)
            }
            Glide.with(profileImg.context)
                .load(character.image)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(profileImg)
        }
    }
}