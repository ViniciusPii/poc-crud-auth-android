package com.example.poccrud.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poccrud.repositories.AuthRepository
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _signInResult = MutableLiveData<State>()
    val signInResult: LiveData<State> = _signInResult

    fun sigInWithGoogle(idToken: String) {
        viewModelScope.launch {
            try {
                authRepository.signInWithGoogle(idToken)
                _signInResult.value = State.Success
            } catch (e: Exception) {
                _signInResult.value = State.Error(e.message.toString())
            }
        }
    }

    fun isLoggedIn() {
        viewModelScope.launch {
            val isLoggedIn = authRepository.isLoggedIn()
            if (isLoggedIn) _signInResult.value = State.Success
        }
    }

    sealed interface State {
        object Loading : State
        object Success : State
        data class Error(val message: String) : State
    }
}