package com.example.starwars.app.home

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwars.app.adapter.favorites.FavoritesCharactersListAdapter
import com.example.starwars.app.viewmodel.CharacterDetailViewModel
import com.example.starwars.databinding.DialogFavBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesDialog : DialogFragment() {

    private lateinit var binding: DialogFavBinding
    private lateinit var favoriteAdapter: FavoritesCharactersListAdapter
    private val characterViewModel: CharacterDetailViewModel by viewModels()


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogFavBinding.inflate(LayoutInflater.from(context))

        favoriteAdapter = FavoritesCharactersListAdapter()
        binding?.recyclerView?.setHasFixedSize(true)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerView?.adapter = favoriteAdapter

        //No funciona xD
        favoriteAdapter.updateList(characterViewModel.listOfFavorites)

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        binding.btnExitDialog.setOnClickListener {
            dismiss() // Para cerrar el diálogo después de hacer clic
        }

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

}

