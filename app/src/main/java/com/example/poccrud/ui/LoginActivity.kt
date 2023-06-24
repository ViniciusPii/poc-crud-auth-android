package com.example.poccrud.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.poccrud.databinding.ActivityLoginBinding
import com.example.poccrud.utils.NavigationUtils.Companion.goToActivity

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
        backButton.setOnClickListener { finish() }
        loginButton.setOnClickListener { }
        createAccountButton.setOnClickListener {
            goToActivity<CreateAccountActivity>("args" to "Teste de args")
        }
    }
}
