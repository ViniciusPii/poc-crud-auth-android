package com.example.poccrud.repositories.impl

import com.example.poccrud.repositories.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.GoogleAuthProvider

class AuthRepositoryImp(
    private val firebaseAuth: FirebaseAuth,
) : AuthRepository {


    override suspend fun signInWithGoogle(idToken: String): Task<AuthResult> {
        try {
            val credential = GoogleAuthProvider.getCredential(idToken, null)
            return firebaseAuth.signInWithCredential(credential)
        } catch (e: FirebaseException) {
            throw Exception("Erro ao autenticar com o Google: ${e.message}")
        } catch (e: FirebaseAuthException) {
            throw Exception("Erro ao autenticar com o Firebase: ${e.message}")
        } catch (e: Exception) {
            throw Exception("Erro desconhecido: ${e.message}")
        }
    }

    override suspend fun signOut() = firebaseAuth.signOut()
}