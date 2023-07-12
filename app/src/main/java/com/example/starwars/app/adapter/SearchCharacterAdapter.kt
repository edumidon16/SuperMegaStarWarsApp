package com.example.starwars.app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.domain.model.ModelCharacter

class SearchCharacterAdapter(
    var characterDetail: List<ModelCharacter> = emptyList(),
    private val onItemSelected: (String?) -> Unit
) : RecyclerView.Adapter<SearchCharacterViewHolder>() {


    @SuppressLint("NotifyDataSetChanged")
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

    override fun getItemCount() = characterDetail.size

    override fun onBindViewHolder(holder: SearchCharacterViewHolder, position: Int) {
        holder.bind(characterDetail[position], onItemSelected)
    }

}