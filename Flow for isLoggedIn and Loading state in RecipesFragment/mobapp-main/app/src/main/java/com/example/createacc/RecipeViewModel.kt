package com.example.createacc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class UiState(val recipes: List<Recipe>, val isLoading: Boolean)

class RecipeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(UiState(emptyList(), true))
    val uiState: StateFlow<UiState> = _uiState

    private val fullRecipeList = listOf(
        Recipe(1, R.drawable.food_placeholder1, "Fresh Ingredients", "A display of fresh ingredients."),
        Recipe(2, R.drawable.food_placeholder2, "Hearty Breakfast", "A colorful breakfast spread."),
        Recipe(3, R.drawable.food_placeholder3, "Healthy Salads", "Fresh and healthy salads.")
    )

    init {
        viewModelScope.launch {
            delay(2000)
            _uiState.value = UiState(fullRecipeList, false)
        }
    }

    fun searchRecipes(query: String) {
        viewModelScope.launch {
            _uiState.value = UiState(emptyList(), true)
            delay(2000)
            val filteredList = fullRecipeList.filter {
                it.title.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
            }
            _uiState.value = UiState(filteredList, false)
        }
    }
}
