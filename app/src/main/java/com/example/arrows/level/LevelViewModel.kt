package com.example.arrows.level

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.example.arrows.database.User
import com.example.arrows.database.UserDatabaseDao
import com.example.arrows.formatUsers
import kotlinx.coroutines.launch

class LevelViewModel(private val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var jeZap = false
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

    fun zapis(meno: String, context: FragmentActivity?) {
        viewModelScope.launch {
            if (maxId() != null && meno != meno(meno)) {
                val newUser = User(maxId() + 1, meno, 0)
                insert(newUser)
                Toast.makeText(context, "meno: $meno sa zapisalo do databazy", Toast.LENGTH_SHORT).show() // TODO cez STRINGY
            } else if (maxId() == null) {
                val newUser = User(1 , meno, 0)
                insert(newUser)
                Toast.makeText(context, "meno: $meno sa zapisalo do databazy", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "$meno uz je zapisany", Toast.LENGTH_SHORT).show() // TODO cezSTRIGNY
            }
        }
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
        }
    }

    private suspend fun maxId() = database.maxId()

    private suspend fun insert(user: User) = database.insert(user)

    private suspend fun clear() = database.clear()

    private suspend fun score(i: Int) = database.score(i)

    private suspend fun meno(i: String) = database.meno(i)
}