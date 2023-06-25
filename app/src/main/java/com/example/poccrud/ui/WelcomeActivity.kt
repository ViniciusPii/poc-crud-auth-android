package com.example.poccrud.ui

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.poccrud.R
import com.example.poccrud.databinding.ActivityWelcomeBinding
import com.example.poccrud.ui.viewmodels.WelcomeViewModel
import com.example.poccrud.ui.viewmodels.WelcomeViewModel.State
import com.example.poccrud.utils.NavigationUtils.Companion.goToActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class WelcomeActivity : AppCompatActivity() {

    private val binding: ActivityWelcomeBinding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }
    private val viewModel: WelcomeViewModel by viewModel()

    private val stateObserver = Observer<State> { state ->
        when (state) {
            is State.Loading -> Unit
            is State.Success -> showSuccess()
            is State.Error -> showError(state.message)
        }
    }

    private val stateObserverLogged = Observer<State> { state ->
        when (state) {
            is State.Loading -> Unit
            is State.Success -> showSuccess()
            is State.Error -> Unit
        }
    }


    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.signInResult.observe(this, stateObserver)
        viewModel.signInResult.observe(this, stateObserverLogged)

        viewModel.isLoggedIn()
        setupListeners()
    }

    private fun setupListeners() = with(binding) {
        googleButton.setOnClickListener { signInWithGoogle() }
        loginEmailButton.setOnClickListener { goToActivity<LoginActivity>() }
    }

    private fun showSuccess() {
        goToActivity<HomeActivity>()
        finish()
    }

    private fun signInWithGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail().build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent

        launcher.launch(signInIntent)
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                val account: GoogleSignInAccount? = task.result
                val idToken = account?.idToken

                if (idToken != null) viewModel.sigInWithGoogle(idToken)
            }
        }
}