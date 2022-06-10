package com.example.arrows.level

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.arrows.database.UsersDatabaseDao

class LevelViewModelFactory(private val dataSource: UsersDatabaseDao) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LevelViewModel::class.java)) {
            return LevelViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}