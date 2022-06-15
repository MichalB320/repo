package com.example.arrows.fragmenty.won

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.arrows.R
import com.example.arrows.database.UserDatabaseDao

class GameWonViewModelFactory(private val dataSource: UserDatabaseDao, private val application: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameWonViewModel::class.java)) {
            return GameWonViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException(application.getString(R.string.vyhod))
    }
}