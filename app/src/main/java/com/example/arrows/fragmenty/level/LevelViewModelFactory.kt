package com.example.arrows.fragmenty.level

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.arrows.R
import com.example.arrows.database.UserDatabaseDao

class LevelViewModelFactory(private val dataSource: UserDatabaseDao, private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LevelViewModel::class.java)) {
            return LevelViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException(application.getString(R.string.vyhod))
    }
}