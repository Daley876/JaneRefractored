package com.example.jane.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jane.R
import com.example.jane.databinding.CharactersListItemLayoutBinding
import com.example.jane.models.StarWarsCharacter

class CharacterAdapter(
    characterList: MutableList<StarWarsCharacter>, currentCharacter: (StarWarsCharacter) -> Unit
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
        val sortedCharacterList = sortListByIsFavorite(listOfCharacters)
        val currentCharacter = sortedCharacterList[position]
        holder.bind(currentCharacter, viewCurrentCharacter)
    }

    private fun sortListByIsFavorite(characterList: MutableList<StarWarsCharacter>)
    : MutableList<StarWarsCharacter> {
      return characterList.sortedWith(compareByDescending { it.isFavorite }) as MutableList
    }

    override fun getItemCount(): Int {
        return listOfCharacters.size
    }


    fun updateCharacterList(characters: MutableList<StarWarsCharacter>) {
        listOfCharacters.clear()
        listOfCharacters.addAll(characters)
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(binding: CharactersListItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val name = binding.characterName
        private val species = binding.characterSpecies
        private val profileBtn = binding.profileBtn
        private val profileImg = binding.imageView
        private val faveBtn = binding.favoriteMarker

        fun bind(
            character: StarWarsCharacter,
            currentChar: (StarWarsCharacter) -> Unit
        ) {
            name.text = character.name
            species.text = character.species
            profileBtn.setOnClickListener {
                currentChar(character)
            }
            faveBtn.setOnClickListener {
                val currentVal = character.isFavorite
                character.isFavorite = !currentVal
                notifyItemRangeChanged(0, itemCount)
            }

            Glide.with(profileImg.context)
                .load(character.image)
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(profileImg)

            if (character.isFavorite) faveBtn.setBackgroundResource(R.drawable.favorite_checked)
            else faveBtn.setBackgroundResource(R.drawable.favorite_unchecked)
        }
    }
}