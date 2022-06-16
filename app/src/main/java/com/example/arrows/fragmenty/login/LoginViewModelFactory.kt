package com.example.arrows.fragmenty.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.arrows.R
import com.example.arrows.database.UserDatabaseDao

class LoginViewModelFactory(private val dataSource: UserDatabaseDao, private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException(application.getString(R.string.vyhod))
    }
}