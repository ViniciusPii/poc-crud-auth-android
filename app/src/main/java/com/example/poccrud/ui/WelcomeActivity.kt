package com.example.poccrud.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.poccrud.databinding.ActivityWelcomeBinding
import com.example.poccrud.utils.NavigationUtils.Companion.goToActivity

class WelcomeActivity : AppCompatActivity() {
    private val binding: ActivityWelcomeBinding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        googleButton.setOnClickListener { }
        loginEmailButton.setOnClickListener { goToActivity<LoginActivity>() }
    }
}