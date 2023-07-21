package com.example.starwars.app.adapter.favorites

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.databinding.FavoriteListItemBinding
import com.example.starwars.domain.model.People

class FavoritesCharactersListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = FavoriteListItemBinding.bind(view)

    fun bind(people: People) {
        binding.tvCharacterName.text = people.name

    }
}