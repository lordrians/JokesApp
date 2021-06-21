package com.example.jokesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.jokesapp.R
import com.example.jokesapp.data.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.jokes
            .observe(this, { jokes ->
                when (jokes){
                    is Resource.Loading -> Log.i("MainActivity", "Data Loading")
                    is Resource.Success -> Log.i("MainActivity", "Data ${jokes.data}")
                    is Resource.Error -> Log.i("MainActivity", "Data ${jokes.message}")
                }
            })

    }
}