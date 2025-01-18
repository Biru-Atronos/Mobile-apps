package com.example.createacc

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.createacc.models.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipeViewModel : ViewModel() {

    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes

    private val fullRecipeList = listOf(
        Recipe(
            id = 1,
            imageResId = R.drawable.food_placeholder1,
            title = "Fresh Ingredients",
            description = "A display of fresh ingredients for cooking."
        ),
        Recipe(
            id = 2,
            imageResId = R.drawable.food_placeholder2,
            title = "Hearty Breakfast",
            description = "A healthy and colorful breakfast spread."
        ),
        Recipe(
            id = 3,
            imageResId = R.drawable.food_placeholder3,
            title = "Healthy Salad",
            description = "A delicious and nutritious green salad."
        )
    )

    init {

        _recipes.value = fullRecipeList
    }

    fun searchRecipes(query: String?) {
        viewModelScope.launch {
            val filteredList = if (!query.isNullOrEmpty() && query.length >= 3) {
                fullRecipeList.filter {
                    it.title.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
                }
            } else {
                fullRecipeList
            }
            _recipes.value = filteredList
        }
    }
}
