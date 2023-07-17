package com.example.starwars.app.adapter.searchlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.databinding.CharacterItemBinding
import com.example.starwars.domain.model.ModelCharacter

class SearchCharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = CharacterItemBinding.bind(view)

    fun bind(modelCharacter: ModelCharacter, onItemSelected: (String?) -> Unit) {
        binding.tvCharacterName.text = modelCharacter.name
        binding.tvCharacterHeight.text = "Height: " + modelCharacter.height

        binding.root.setOnClickListener { onItemSelected(modelCharacter.name) }
    }
}
