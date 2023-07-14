package com.example.starwars.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.domain.model.People

class CharacterAdapter(
    var characterList: List<People> = emptyList(),
    private val onItemSelected: (String?) -> Unit
) : RecyclerView.Adapter<CharacterViewHolder>() {


    fun updateList(characterList: List<People>) {
        this.characterList = characterList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layaoutInflater = LayoutInflater.from(parent.context)
        return CharacterViewHolder(layaoutInflater.inflate(R.layout.character_item, parent, false))
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(viewholder: CharacterViewHolder, position: Int) {
        viewholder.bind(characterList[position], onItemSelected)
    }
}