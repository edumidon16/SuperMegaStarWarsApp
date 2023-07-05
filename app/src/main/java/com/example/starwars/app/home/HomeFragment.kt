package com.example.starwars.app.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.starwars.app.common.states.ResourceState
import com.example.starwars.app.viewmodel.PeopleListViewModel
import com.example.starwars.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PeopleListViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        saveInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.resourceState.collect { resourcesState ->
                    when (resourcesState) {
                        is ResourceState.Error -> {
                            binding.progressBar.isVisible = true
                        }

                        is ResourceState.loading -> {
                            binding.progressBar.isVisible = true
                        }

                        is ResourceState.Success -> {
                            binding.progressBar.isVisible = false

                        }
                    }
                }
            }

        }

    }
}











