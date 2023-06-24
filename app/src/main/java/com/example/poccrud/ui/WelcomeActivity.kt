package com.example.poccrud.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.poccrud.databinding.ActivityWelcomeBinding

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
        loginEmailButton.setOnClickListener { goToLoginPage() }
    }

    private fun goToLoginPage() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}