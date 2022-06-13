package com.example.arrows.level

import android.app.Application
import androidx.lifecycle.*
import com.example.arrows.database.User
import com.example.arrows.database.UserDatabaseDao
import kotlinx.coroutines.launch

class LevelViewModel(val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData(" ")
    val text: LiveData<String>
        get() = _text

    init {
        viewModelScope.launch {
            database.getAllUsers()
        }
    }

    suspend fun insert(user: User) {
        database.insert(user)
    }

    suspend fun clear() {
        database.clear()
    }

    suspend fun score(i: Int) {
        database.score(i)
    }

    suspend fun meno(i: Int) {
        database.meno(i)
    }

    suspend fun maxId(): Int {
        return database.maxId()
    }
}