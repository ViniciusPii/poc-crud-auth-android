package com.example.poccrud.di

import android.app.Application
import com.example.poccrud.repositories.AuthRepository
import com.example.poccrud.repositories.impl.AuthRepositoryImp
import com.example.poccrud.ui.viewmodels.HomeViewModel
import com.example.poccrud.ui.viewmodels.WelcomeViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }

    private val appModule = module {
        single { FirebaseAuth.getInstance() }
        single<AuthRepository> { AuthRepositoryImp(firebaseAuth = get()) }

        viewModel { WelcomeViewModel(authRepository = get()) }
        viewModel { HomeViewModel(authRepository = get()) }
    }
}