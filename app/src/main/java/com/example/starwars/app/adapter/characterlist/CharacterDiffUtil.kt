package com.example.starwars.app.adapter.characterlist

import androidx.recyclerview.widget.DiffUtil
import com.example.starwars.domain.model.People

class CharacterDiffUtil(
    private val newList: List<People>,
    private val oldList: List<People>
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