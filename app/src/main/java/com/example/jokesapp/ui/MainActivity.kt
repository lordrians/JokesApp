package com.example.jokesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jokesapp.R
import com.example.jokesapp.adapter.JokesAdapter
import com.example.jokesapp.data.Resource
import com.example.jokesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object{
        var oldPosition: Int? = null
    }

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = JokesAdapter()
        binding.rvJokes.layoutManager = LinearLayoutManager(this)
        binding.rvJokes.adapter = adapter
        binding.rvJokes.setHasFixedSize(true)

        viewModel.jokes
            .observe(this, { jokes ->
                when (jokes){
                    is Resource.Loading -> {
                        isProgressDone(false)
                    }
                    is Resource.Success -> {
                        isProgressDone(true)
                        jokes.data?.let { adapter.setData(it) }
                        adapter.notifyDataSetChanged()
                    }
                    is Resource.Error -> {
                        isProgressDone(true)
                        Log.i("MainActivity", "onCreate: ${jokes.message} ")

                    }
                }
            })

    }

    private fun isProgressDone(state: Boolean){
        binding.progressBar.isVisible = !state
        binding.rvJokes.isVisible = state
    }
}