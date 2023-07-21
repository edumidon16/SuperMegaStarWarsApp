package com.example.starwars.app.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.starwars.R
import com.example.starwars.app.viewmodel.CharacterDetailViewModel
import com.example.starwars.app.viewmodel.CharacterListViewModel
import com.example.starwars.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private val characterViewModel: CharacterDetailViewModel by viewModels()
    private val peopleViewModel: CharacterListViewModel by viewModels()


    private var name: String? = ""
    var peopleName = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        saveInstanceState: Bundle?
    ): View {
        _binding = FragmentCharacterDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.let { bundle ->
            peopleName = bundle.getString("name").toString()
        }

        peopleName.let { name ->
            characterViewModel.getDetailCharacter(name) { detailList ->

                detailList.first().let { modelCharacter ->
                    val characterName = modelCharacter.name
                    val characterHeight = modelCharacter.height
                    val characterMass = modelCharacter.mass
                    val characterGender = modelCharacter.gender

                    binding?.tvName?.text = "Name: $characterName"
                    binding.tvGender.text = "Gender: $characterGender"
                    binding.tvHeight.text = "Height: $characterHeight cm"
                    binding.tvMass.text = "Mass: $characterMass kg"
                }
            }

        }
        binding.btnEliminateCharacter.setOnClickListener {
            navigateToHome(peopleName)
        }

        binding.btnAddCharacter.setOnClickListener {
            val addFavoriteName = peopleName
            Toast.makeText(requireContext(), "Add to favorites", Toast.LENGTH_LONG).show()
            val personToAdd =
                peopleViewModel.currentCharacterList.find { it.name == addFavoriteName }


            if (!addFavoriteName.isNullOrEmpty()) {
                if (personToAdd != null) {
                    characterViewModel.listOfFavorites =
                        characterViewModel.listOfFavorites.plus(personToAdd)
                }
            }
            navigateToHomeToFav()
        }
    }

    private fun navigateToHomeToFav() {
        findNavController().navigate(
            R.id.action_characterDetailFragment_to_homeFragment
        )
    }

    private fun navigateToHome(name: String?) {
        findNavController().navigate(
            R.id.action_characterDetailFragment_to_homeFragment,
            bundleOf("name" to name)
        )
    }
}