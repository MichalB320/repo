package com.example.arrows.fragmenty.over

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.arrows.database.User
import com.example.arrows.database.UserDatabaseDao
import kotlinx.coroutines.launch

/**
 * ViewModel pre fragment prehratej hry
 */
class GameOverViewModel(private val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {

    /**
     * ulozí nahrané score do databázy
     *
     * @param hodnota, meno
     */
    fun updateniscore(hodnota: Int, meno: String) {
        viewModelScope.launch {
            val user: User = database.getUser(meno)
            user.score = hodnota
            database.update(user)
        }
    }
}