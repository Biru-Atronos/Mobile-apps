package com.example.createacc.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.createacc.R
import com.example.createacc.adapters.RecipeAdapter
import com.example.createacc.models.Recipe

class FragmentA : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecipeAdapter


    private val recipes = listOf(
        Recipe(1, R.drawable.food_placeholder1, "Fresh Ingredients", "A display of fresh ingredients for cooking."),
        Recipe(2, R.drawable.food_placeholder2, "Hearty Breakfast", "A healthy and colorful breakfast spread."),
        Recipe(3, R.drawable.food_placeholder3, "Healthy Salads", "A variety of fresh and healthy salads.")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        adapter = RecipeAdapter(
            recipes,
            onItemClicked = { recipe ->
                Toast.makeText(requireContext(), "Clicked: ${recipe.title}", Toast.LENGTH_SHORT).show()
            },
            onButtonClicked = { recipe, action ->
                Toast.makeText(requireContext(), "$action clicked for ${recipe.title}", Toast.LENGTH_SHORT).show()
            }
        )

        recyclerView.adapter = adapter

        return view
    }
}
