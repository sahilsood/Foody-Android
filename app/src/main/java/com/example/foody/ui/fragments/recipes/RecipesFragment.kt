package com.example.foody.ui.fragments.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foody.R
import com.example.foody.adapter.RecipesAdapter
import com.example.foody.databinding.FragmentRecipesBinding
import com.example.foody.util.NetworkResult
import com.example.foody.util.observeOnce
import com.example.foody.viewmodel.MainViewModel
import com.example.foody.viewmodel.RecipesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecipesFragment : Fragment() {
    private val recipesAdapter by lazy { RecipesAdapter() }
    private var _binding: FragmentRecipesBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        recipesViewModel = ViewModelProvider(requireActivity())[RecipesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRecipesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainViewModel = mainViewModel
        setUpRecyclerView()
        readDatabase()
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_recipesFragment_to_recipesBottomSheetFragment)
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerView.adapter = recipesAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observeOnce(viewLifecycleOwner) { database ->
                if (database.isNotEmpty()) {
                    Log.d("RecipesFragment", "readDatabase called!")
                    database.firstOrNull()?.foodRecipe?.let { recipesAdapter.setData(foodRecipe = it) }
                    hideShimmerEffect()
                } else {
                    requestApiData()
                }
            }
        }
    }

    private fun requestApiData() {
        Log.d("RecipesFragment", "requestApiData called!")
        mainViewModel.getRecipes(queries = recipesViewModel.applyQueries(resources = resources))
        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { recipesAdapter.setData(foodRecipe = it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    requestDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }

        }
    }

    private fun requestDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner) { database ->
                if (database.isNotEmpty()) {
                    database.firstOrNull()?.foodRecipe?.let { recipesAdapter.setData(foodRecipe = it) }
                }
            }
        }
    }

    private fun showShimmerEffect() {
        binding.recyclerView.showShimmer()
    }

    private fun hideShimmerEffect() {
        binding.recyclerView.hideShimmer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}