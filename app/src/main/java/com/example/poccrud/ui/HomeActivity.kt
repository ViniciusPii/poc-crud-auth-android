package com.example.poccrud.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.poccrud.databinding.ActivityHomeBinding
import com.example.poccrud.ui.viewmodels.HomeViewModel
import com.example.poccrud.ui.viewmodels.HomeViewModel.State
import com.example.poccrud.utils.NavigationUtils.Companion.goToActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }
    private val viewModel: HomeViewModel by viewModel()

    private val stateObserver = Observer<State> { state ->
        when (state) {
            is State.Success -> signOutGoogle()
            is State.Error -> Unit
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.signOutResult.observe(this, stateObserver)
        setupListeners()
    }

    private fun setupListeners() {
        binding.homeSignOutButton.setOnClickListener {
            viewModel.signOut()
        }
    }

    private fun signOutGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        googleSignInClient.signOut()

        goToActivity<WelcomeActivity>()
        finish()
    }
}