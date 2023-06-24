package com.example.poccrud.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.poccrud.databinding.ActivityCreateAccountBinding
import com.example.poccrud.utils.NavigationUtils.Companion.goToActivity

class CreateAccountActivity : AppCompatActivity() {

    private val binding: ActivityCreateAccountBinding by lazy {
        ActivityCreateAccountBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        createBackButton.setOnClickListener { finish() }
        createAccountUserButton.setOnClickListener { goToActivity<HomeActivity>(isFinished = true) }
    }
}