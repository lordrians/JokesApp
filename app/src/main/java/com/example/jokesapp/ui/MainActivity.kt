package com.example.jokesapp.ui

import android.annotation.SuppressLint
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
import com.example.jokesapp.data.source.local.entity.JokesEntity
import com.example.jokesapp.data.source.remote.network.ApiResponse
import com.example.jokesapp.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: JokesAdapter
    private var dummyList: List<JokesEntity> = ArrayList()

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

            isProgressDone(false)
            viewModel.getLiveData()
            binding.swipeLayout.isRefreshing = false
        }

    }

    private fun observeData() {
        viewModel.dataLive.observe(this, { jokes ->

            when(jokes){
                is ApiResponse.Success -> {
                    isProgressDone(true)
                    Log.i("MainActivity", "observeData: ${jokes.data}")
                    val map = DataMapper.mapResponseToEntities(jokes.data)
                    adapter.setData(map)
                    adapter.notifyDataSetChanged()
                }
                is ApiResponse.Error -> {
                    isProgressDone(true)
                    showSnackbar(jokes.errorMessage)
                    adapter.setData(dummyList)
                    Log.i("MainActivity", "observeData error: ${jokes.errorMessage}")
                }
                is ApiResponse.Empty -> {
                    isProgressDone(true)
                    adapter.setData(dummyList)
                    Log.i("MainActivity", "observeData empty: ${jokes}")
                }
            }
        })
    }

    @SuppressLint("ShowToast")
    private fun showSnackbar(message: String){
        Snackbar.make(binding.root,message,Snackbar.LENGTH_INDEFINITE)
            .setAction("Retry"){
                isProgressDone(false)
                viewModel.getLiveData()
            }
            .show()

    }

    private fun isProgressDone(state: Boolean){
        binding.rvJokes.isVisible = state
        binding.shimmerLayout.isVisible = !state
    }
}