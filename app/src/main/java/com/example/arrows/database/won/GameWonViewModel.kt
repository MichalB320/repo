package com.example.arrows.database.won

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.arrows.database.User
import com.example.arrows.database.UserDatabaseDao
import kotlinx.coroutines.launch

class GameWonViewModel(private val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {

    fun updateniscore(hodnota: Int) {
        viewModelScope.launch {
            val user: User = database.get(1)
            user.score = hodnota
            database.update(user)
        }
    }
}