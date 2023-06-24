package com.example.poccrud.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.poccrud.databinding.ActivityCreateAccountBinding

class CreateAccountActivity : AppCompatActivity() {

    private val binding: ActivityCreateAccountBinding by lazy {
        ActivityCreateAccountBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val string = intent.getStringExtra(ARGS)

        binding.userName.text = string

        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        createBackButton.setOnClickListener { finish() }
    }

    companion object {
        const val ARGS = "args"
    }
}