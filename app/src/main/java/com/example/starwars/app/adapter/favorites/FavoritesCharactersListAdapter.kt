package com.example.starwars.app.adapter.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.domain.model.People

class FavoritesCharactersListAdapter(
    var favoritesCharacterList: List<People> = emptyList()
) : RecyclerView.Adapter<FavoritesCharactersListViewHolder>() {

    fun updateList(newList: List<People>) {
        val favoritesCharacterDiffUtil =
            FavoritesCharactersListDiffUtil(favoritesCharacterList, newList)
        val result = DiffUtil.calculateDiff(favoritesCharacterDiffUtil)
        favoritesCharacterList = newList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesCharactersListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavoritesCharactersListViewHolder(
            layoutInflater.inflate(
                R.layout.favorite_list_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return favoritesCharacterList.size
    }

    override fun onBindViewHolder(holder: FavoritesCharactersListViewHolder, position: Int) {
        holder.bind(favoritesCharacterList[position])
    }

}