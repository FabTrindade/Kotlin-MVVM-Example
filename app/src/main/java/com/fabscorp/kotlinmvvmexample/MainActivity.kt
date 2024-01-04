package com.fabscorp.kotlinmvvmexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fabscorp.kotlinmvvmexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener(this)

        //Ensures that the view model is instantiated correctly following the MainActivity lifecycle
        // So, if MainActivity is destroyed, the viewModel will be as well.
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setObserver()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_login) {
            val email = binding.editEmail.text.toString()
            val passw = binding.editPassw.text.toString()

            viewModel.doLoging(email, passw)
        }
    }

    private fun setObserver() {
        //It stay observing to that, any change that occurs in viewModel.welcome,
        // the binding.textWelcome.text attribute is updated.
        viewModel.welcome().observe(this, Observer {
            binding.textWelcome.text = it
        })

        viewModel.login().observe(this, Observer {
            if (it) {
                Toast.makeText(applicationContext, "SUCCESS!!!", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(applicationContext, "FAULT!!!", Toast.LENGTH_LONG).show()
            }
        })
    }
}
