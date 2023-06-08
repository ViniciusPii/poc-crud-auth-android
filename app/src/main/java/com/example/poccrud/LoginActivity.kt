package com.example.poccrud

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.poccrud.databinding.ActivityLoginBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setListeners()
    }

    private fun setListeners() {
        binding.loginButton.setOnClickListener {
            binding.loginButton.isVisible = false
            binding.loadingButton.isVisible = true

            MainScope().launch {
                delay(600)
                binding.loginButton.isVisible = true
                binding.loadingButton.isVisible = false
            }
        }
    }
}