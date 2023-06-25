package com.example.poccrud.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.poccrud.repositories.AuthRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _signOutResult = MutableLiveData<State>()
    val signOutResult: LiveData<State> = _signOutResult

    fun signOut() {
        viewModelScope.launch {
            try {
                authRepository.signOut()
                _signOutResult.value = State.Success
            } catch (e: Exception) {
                _signOutResult.value = State.Error(e.message.toString())
            }
        }
    }

    sealed interface State {
        object Success : State
        data class Error(val message: String) : State
    }
}