package com.example.starwars.app.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.starwars.app.viewmodel.CharacterDetailViewModel
import com.example.starwars.databinding.FragmentCharacterDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private var _binding: FragmentCharacterDetailBinding? = null
    private val binding get() = _binding!!

    private val characterViewModel: CharacterDetailViewModel by viewModels()

    private var name: String? = ""

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let { bundle ->
            name = bundle.getString("name")
        }

        name.let {
            characterViewModel.getDetailCharacter(name) { detailList ->

                detailList.forEach { modelCharacter ->
                    val characterName = modelCharacter.name
                    val characterHeight = modelCharacter.height
                    val characterMass = modelCharacter.mass
                    val characterGender = modelCharacter.gender

                    binding.tvName.text = "Name: $characterName"
                    binding.tvGender.text = "Gender: $characterGender"
                    binding.tvHeight.text = "Height: $characterHeight cm"
                    binding.tvMass.text = "Mass: $characterMass kg"
                }
            }

        }
    }
}