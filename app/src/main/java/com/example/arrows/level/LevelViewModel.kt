package com.example.arrows.level

import android.app.Application
import androidx.lifecycle.*
import com.example.arrows.database.User
import com.example.arrows.database.UserDatabaseDao
import com.example.arrows.formatUsers
import kotlinx.coroutines.launch

class LevelViewModel(private val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private val _text = MutableLiveData(" ")
    val text: LiveData<String>
        get() = _text

    private val users = database.getAllUsers()
    val userStrings = Transformations.map(users) {users ->
        formatUsers(users, application.resources)
    }

    val potvrdButtonVisible = Transformations.map(users) {
        null != it
    }

    init {
        viewModelScope.launch {
            database.getAllUsers()
        }
    }

    fun zapis(meno: String) {
        viewModelScope.launch {
            val newUser = User(maxId() + 1, meno, 0)
            insert(newUser)
        }
    }

    private suspend fun maxId() = database.maxId()

    private suspend fun insert(user: User) = database.insert(user)

    private suspend fun clear() = database.clear()

    private suspend fun score(i: Int) = database.score(i)

    private suspend fun meno(i: Int) = database.meno(i)
}