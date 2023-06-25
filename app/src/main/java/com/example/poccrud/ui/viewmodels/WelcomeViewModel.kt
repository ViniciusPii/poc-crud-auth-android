package com.example.poccrud.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poccrud.repositories.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _signInResult = MutableLiveData<State>()
    val signInResult: LiveData<State> = _signInResult

    fun sigInWithGoogle(idToken: String) {
        viewModelScope.launch {
            try {
                val result = authRepository.signInWithGoogle(idToken)
                _signInResult.value = State.Success(result)
            } catch (e: Exception) {
                _signInResult.value = State.Error(e.message.toString())
            }
        }
    }

    sealed interface State {
        object Loading : State
        data class Success(val data: Task<AuthResult>) : State
        data class Error(val message: String) : State
    }
}