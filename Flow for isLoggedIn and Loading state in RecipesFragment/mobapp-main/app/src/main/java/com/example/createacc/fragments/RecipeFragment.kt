package com.example.createacc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.createacc.CredentialsManager
import com.example.createacc.R
import com.example.createacc.RecipeViewModel
import com.example.createacc.RecipeAdapter
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RecipeFragment(private val credentialsManager: CredentialsManager) : Fragment() {

    private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var progressBar: ProgressBar
    private lateinit var logoutButton: Button
    private val recipeViewModel: RecipeViewModel by viewModels()
    private var debounceJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        searchView = view.findViewById(R.id.searchView)
        progressBar = view.findViewById(R.id.progressBar)
        logoutButton = view.findViewById(R.id.logoutButton)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recipeAdapter = RecipeAdapter(
            emptyList(),
            onItemClicked = { recipe ->
                Toast.makeText(requireContext(), "Clicked on ${recipe.title}", Toast.LENGTH_SHORT).show()
            },
            onButtonClicked = { recipe, action ->
                Toast.makeText(requireContext(), "$action clicked for ${recipe.title}", Toast.LENGTH_SHORT).show()
            }
        )

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipeAdapter
        }

        lifecycleScope.launch {
            recipeViewModel.uiState.collect { uiState ->
                progressBar.visibility = if (uiState.isLoading) View.VISIBLE else View.GONE
                recyclerView.visibility = if (uiState.isLoading) View.GONE else View.VISIBLE
                recipeAdapter.updateRecipes(uiState.recipes)
            }
        }

        lifecycleScope.launch {
            credentialsManager.isLoggedIn.collect { loggedIn ->
                if (!loggedIn) {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, LoginFragment(credentialsManager))
                        .commit()
                }
            }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { recipeViewModel.searchRecipes(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                debounceJob?.cancel()
                debounceJob = lifecycleScope.launch {
                    delay(300)
                    newText?.let { recipeViewModel.searchRecipes(it) }
                }
                return true
            }
        })

        logoutButton.setOnClickListener {
            credentialsManager.logout()
        }
    }
}
