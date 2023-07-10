package com.example.starwars.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.domain.model.ModelCharacter

class SearchCharacterAdapter(
    var characterDetail: List<ModelCharacter> = emptyList()
) : RecyclerView.Adapter<SearchCharacterViewHolder>() {


    fun updateList(characterList: List<ModelCharacter>) {
        this.characterDetail = characterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchCharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SearchCharacterViewHolder(
            layoutInflater.inflate(
                R.layout.character_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return characterDetail.size
    }

    override fun onBindViewHolder(holder: SearchCharacterViewHolder, position: Int) {
        holder.bind(characterDetail[position])
    }


}