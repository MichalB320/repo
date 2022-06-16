package com.example.arrows.fragmenty.won

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.arrows.database.User
import com.example.arrows.database.UserDatabaseDao
import kotlinx.coroutines.launch

/**
 * ViewModel vyhratej hry
 */
class GameWonViewModel(private val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {

    /**
     * uloží score do databázy
     */
    fun updateniScore(hodnota: Int, meno: String) {
        viewModelScope.launch {
            val user: User = database.getUser(meno)
            user.score = hodnota
            database.update(user)
        }
    }
}