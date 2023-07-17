package com.example.starwars.app.adapter.searchlist

import androidx.recyclerview.widget.DiffUtil
import com.example.starwars.domain.model.ModelCharacter

class SeachCharacterDiffUtil(
    private val oldList: List<ModelCharacter>,
    private val newList: List<ModelCharacter>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }


}