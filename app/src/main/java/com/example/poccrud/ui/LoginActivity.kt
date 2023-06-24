package com.example.poccrud.ui

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

    private fun setListeners() = with(binding) {
        loginButton.setOnClickListener {
            loginButton.isVisible = false
            loadingButton.isVisible = true

            MainScope().launch {
                delay(600)
                loginButton.isVisible = true
                loadingButton.isVisible = false
            }
        }

        backButton.setOnClickListener { finish() }
    }
}