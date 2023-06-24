package com.example.poccrud.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.poccrud.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.homeSignOutButton.setOnClickListener { }
    }
}