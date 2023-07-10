package com.example.starwars.app.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwars.app.adapter.CharacterAdapter
import com.example.starwars.app.adapter.SearchCharacterAdapter
import com.example.starwars.app.common.states.ResourceState
import com.example.starwars.app.viewmodel.CharacterDetailViewModel
import com.example.starwars.app.viewmodel.PeopleListViewModel
import com.example.starwars.databinding.FragmentHomeBinding
import com.example.starwars.domain.model.ModelCharacter
import com.example.starwars.domain.model.People
import com.example.starwars.domain.model.PeopleResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
            .apply { }
    }

    private var binding: FragmentHomeBinding? = null

    //region List
    private val peopleViewModel: PeopleListViewModel by viewModels()
    private var currentCharacterList: List<People> = emptyList()
    private lateinit var characterAdapter: CharacterAdapter
    //endregion

    //Searcher
    private val characterViewModel: CharacterDetailViewModel by viewModels()
    private var currentCharacterDetailList: List<ModelCharacter> = emptyList()
    private lateinit var searchAdapter: SearchCharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        saveInstanceState: Bundle?
    ): View {
        this.binding = FragmentHomeBinding.inflate(inflater, container, false)
        return this.binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        this.binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //region List
//        characterAdapter = CharacterAdapter()
//        binding?.rvStarWarsCharacters?.setHasFixedSize(true)
//        binding?.rvStarWarsCharacters?.layoutManager = LinearLayoutManager(requireContext())
//        binding?.rvStarWarsCharacters?.adapter = characterAdapter
        //endregion

        searchAdapter = SearchCharacterAdapter()
        binding?.rvStarWarsCharacters?.setHasFixedSize(true)
        binding?.rvStarWarsCharacters?.layoutManager = LinearLayoutManager(requireContext())
        binding?.rvStarWarsCharacters?.adapter = searchAdapter

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                characterViewModel.searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch(Dispatchers.IO) {
                    characterViewModel.characterSearcher.collectLatest { listState ->
                        when (listState) {
                            is ResourceState.Error -> {
                                withContext(Dispatchers.Main) {
                                    binding?.progressBar?.isVisible = true
                                }
                            }

                            is ResourceState.Loading -> {
                                withContext(Dispatchers.Main) {
                                    binding?.progressBar?.isVisible = true
                                }
                            }

                            is ResourceState.Success -> {
                                withContext(Dispatchers.Main) {
                                    val characterDetail = listState.data as List<ModelCharacter>
                                    currentCharacterDetailList = characterDetail
                                    searchAdapter.updateList(currentCharacterDetailList)
                                    binding?.progressBar?.isVisible = false
                                }
                            }

                            else -> {}
                        }
                    }
                }
            }
        }


        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch(Dispatchers.IO) {
                    peopleViewModel.characterList.collectLatest { listState ->
                        when (listState) {
                            is ResourceState.Error -> {
                                withContext(Dispatchers.Main) {
                                    binding?.progressBar?.isVisible = true
                                }
                            }

                            is ResourceState.Loading -> {
                                withContext(Dispatchers.Main) {
                                    binding?.progressBar?.isVisible = true
                                }

                            }

                            is ResourceState.Success -> {

                                withContext(Dispatchers.Main) {
                                    val a = listState.data as List<PeopleResponse>
                                    currentCharacterList = a.first().results.orEmpty()
                                    characterAdapter.updateList(currentCharacterList)
                                    binding?.progressBar?.isVisible = false
                                }
                            }

                            else -> {}
                        }
                    }
                }
            }
        }
    }

}











