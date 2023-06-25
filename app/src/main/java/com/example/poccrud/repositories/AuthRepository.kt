package com.example.poccrud.repositories

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface AuthRepository {
    suspend fun signInWithGoogle(idToken: String): Task<AuthResult>
    suspend fun signOut()
}