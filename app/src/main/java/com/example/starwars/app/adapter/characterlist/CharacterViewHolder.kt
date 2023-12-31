package com.example.starwars.app.adapter.characterlist

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.databinding.CharacterItemBinding
import com.example.starwars.domain.model.People

class CharacterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = CharacterItemBinding.bind(view)

    fun bind(people: People, onItemSelected: (String?) -> Unit) {
        binding.tvCharacterName.text = people.name

        binding.root.setOnClickListener { onItemSelected(people.name) }

    }
}