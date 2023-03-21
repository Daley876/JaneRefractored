package com.example.jane.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.jane.R
import com.example.jane.databinding.ProfileListItemLayoutBinding
import com.example.jane.models.StarWarsCharacterProfile
import com.example.jane.utils.ResponseStates

class CharacterProfileFragment : ViewModelFragment() {
    private lateinit var binding: ProfileListItemLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileListItemLayoutBinding.inflate(inflater, container, false)
        initObserver()
        return binding.root
    }

    private fun initObserver() {
        viewModel.profileOfCharacterLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResponseStates.OnResponseSuccess<*> -> {
                    val starWarsCharacter: StarWarsCharacterProfile? =
                        state.response as StarWarsCharacterProfile
                    if (starWarsCharacter == null) {
                        binding.apply {
                            profileLoading.visibility = View.GONE
                            emptyProfileError.visibility = View.VISIBLE
                            characterProfileData.visibility = View.GONE
                            emptyProfileError.setText(R.string.blank_profile_data)
                        }
                    } else {
                        binding.apply {
                            profileLoading.visibility = View.GONE
                            emptyProfileError.visibility = View.GONE
                            characterProfileData.visibility = View.VISIBLE

                            Glide.with(profileImage.context)
                                .load(starWarsCharacter.image)
                                .error(R.drawable.ic_baseline_error_outline_24)
                                .into(profileImage)

                            deathLocation.apply {
                                val deathText =
                                    starWarsCharacter.diedLocation ?: "Unknown"
                                text = resources.getString(
                                    R.string.character_death_location,
                                    deathText
                                )
                            }

                            bornLocation.apply {
                                val bornText =
                                    starWarsCharacter.bornLocation ?: "Unknown"
                                text =
                                    resources.getString(R.string.character_birth_location, bornText)
                            }

                            hairColor.apply {
                                val colorText = starWarsCharacter.hairColor ?: "Unknown"
                                text = resources.getString(R.string.character_hair_color, colorText)
                            }

                            skinColor.apply {
                                val colorText = starWarsCharacter.skinColor ?: "Unknown"
                                text = resources.getString(R.string.character_skin_color, colorText)
                            }

                            eyeColor.apply {
                                val colorText = starWarsCharacter.eyeColor ?: "Unknown"
                                text = resources.getString(R.string.character_eye_color, colorText)
                            }

                            homeWorld.apply {
                                val homeWorldText = starWarsCharacter.homeWorld ?: "Unknown"
                                text = resources.getString(
                                    R.string.character_home_world,
                                    homeWorldText
                                )
                            }

                            username.text = starWarsCharacter.name

                            charHeight.text = resources.getString(
                                R.string.character_height,
                                starWarsCharacter.height
                            )

                            charMass.text =
                                resources.getString(R.string.character_mass, starWarsCharacter.mass)

                            charGender.text = resources.getString(
                                R.string.character_gender,
                                starWarsCharacter.gender
                            )

                            species.text = resources.getString(
                                R.string.character_species,
                                starWarsCharacter.species
                            )

                            characterProfileData.visibility = View.VISIBLE
                        }
                    }
                }
                is ResponseStates.OnResponseError -> {
                    binding.apply {
                        profileLoading.visibility = View.GONE
                        characterProfileData.visibility = View.GONE
                        emptyProfileError.setText(R.string.blank_profile_data)
                        emptyProfileError.visibility = View.VISIBLE
                    }
                }
                is ResponseStates.OnResponseLoading -> {
                    binding.emptyProfileError.visibility = View.GONE
                    binding.characterProfileData.visibility = View.GONE
                    binding.profileLoading.visibility = View.VISIBLE
                    viewModel.fetchProfileDataForCharacter(viewModel.selectedCharacter?.characterId.toString())
                }
            }
        }
    }
}