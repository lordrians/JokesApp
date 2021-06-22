package com.example.jokesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jokesapp.DataMapper
import com.example.jokesapp.R
import com.example.jokesapp.adapter.JokesAdapter
import com.example.jokesapp.data.Resource
import com.example.jokesapp.data.source.remote.network.ApiResponse
import com.example.jokesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: JokesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = JokesAdapter()
        binding.rvJokes.layoutManager = LinearLayoutManager(this)
        binding.rvJokes.adapter = adapter
        binding.rvJokes.setHasFixedSize(true)

        viewModel.getLiveData()

        observeData()

        binding.swipeLayout.setOnRefreshListener {

            binding.progressBar.isVisible = true
            viewModel.getLiveData()
            binding.swipeLayout.isRefreshing = false
        }

    }

    private fun observeData() {
//        viewModel.jokes
//            .observe(this, { jokes ->
//                when (jokes){
//                    is Resource.Loading -> {
//                        isProgressDone(false)
//                    }
//                    is Resource.Success -> {
//                        isProgressDone(true)
//                        jokes.data?.let { adapter.setData(it) }
//                        adapter.notifyDataSetChanged()
//                    }
//                    is Resource.Error -> {
//                        isProgressDone(true)
//                        Log.i("MainActivity", "onCreate: ${jokes.message} ")
//                    }
//                }
//            })
        viewModel.dataLive.observe(this, { jokes ->

            when(jokes){
                is ApiResponse.Success -> {
                    binding.progressBar.isVisible = false
                    Log.i("MainActivity", "observeData: ${jokes.data}")
                    val map = DataMapper.mapResponseToEntities(jokes.data)
                    adapter.setData(map)
                    adapter.notifyDataSetChanged()
                }
                is ApiResponse.Error -> {
                    binding.progressBar.isVisible = false
                    Log.i("MainActivity", "observeData error: ${jokes.errorMessage}")
                }
                is ApiResponse.Empty -> {
                    binding.progressBar.isVisible = false
                    Log.i("MainActivity", "observeData empty: ${jokes}")
                }
            }
        })
    }

    private fun isProgressDone(state: Boolean){
        binding.progressBar.isVisible = !state
        binding.rvJokes.isVisible = state
    }
}