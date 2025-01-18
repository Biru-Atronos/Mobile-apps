package com.example.createacc.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.createacc.R
import com.example.createacc.models.Recipe

class RecipeAdapter(
    private var recipes: List<Recipe>,
    private val onItemClicked: (Recipe) -> Unit,
    private val onButtonClicked: (Recipe, String) -> Unit
) : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recipeImage: ImageView = view.findViewById(R.id.recipeImage)
        val recipeTitle: TextView = view.findViewById(R.id.recipeTitle)
        val recipeDescription: TextView = view.findViewById(R.id.recipeDescription)
        val likeButton: Button = view.findViewById(R.id.btnLike)
        val shareButton: Button = view.findViewById(R.id.btnShare)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipes[position]
        holder.recipeImage.setImageResource(recipe.imageResId)
        holder.recipeTitle.text = recipe.title
        holder.recipeDescription.text = recipe.description

        holder.itemView.setOnClickListener {
            onItemClicked(recipe)
        }

        holder.likeButton.setOnClickListener {
            onButtonClicked(recipe, "Like")
        }

        holder.shareButton.setOnClickListener {
            onButtonClicked(recipe, "Share")
        }
    }

    override fun getItemCount(): Int = recipes.size

    fun updateRecipes(newRecipes: List<Recipe>) {
        recipes = newRecipes
        notifyDataSetChanged()
    }
}
