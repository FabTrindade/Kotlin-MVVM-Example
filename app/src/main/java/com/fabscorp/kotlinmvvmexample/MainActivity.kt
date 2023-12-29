package com.fabscorp.kotlinmvvmexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fabscorp.kotlinmvvmexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Ensures that the view model is instantiated correctly following the MainActivity lifecycle
        // So, if MainActivity is destroyed, the viewModel will be as well.
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setObserver()
    }
    private fun setObserver() {
        //It stay observing to that, any change that occurs in viewModel.welcome,
        // the binding.textWelcome.text attribute is updated.
        viewModel.welcome().observe(this, Observer {
            binding.textWelcome.text = it
        })
    }
}