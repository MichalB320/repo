package com.example.arrows.fragmenty.level

import android.app.Application
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.arrows.database.User
import com.example.arrows.database.UserDatabaseDao
import kotlinx.coroutines.launch

class LevelViewModel(private val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var _submitVisible = MutableLiveData(false)
    private var _clearVisible = MutableLiveData(false)
    private var _playVisible = MutableLiveData(false)
    private val _text = MutableLiveData(" ")
    val text: LiveData<String>
        get() = _text

    val submitVisible: MutableLiveData<Boolean>
        get() = _submitVisible

    val clearVisible: MutableLiveData<Boolean>
        get() = _clearVisible

    val playVisible: MutableLiveData<Boolean>
        get() = _playVisible

    val users = database.getAllUsers()
//    val userStrings = Transformations.map(users) { users ->
//        formatUsers(users, application.resources)
//    }

//    var potvrdButtonVisible = Transformations.map(users) {
//        true
//    }

    init {
        viewModelScope.launch {

            //insert(User(1, "misko", 0))
            database.getAllUsers()


            if (pocetUsers() >= 1) {
                _submitVisible.value = true
                _clearVisible.value = true
            }
            if (pocetUsers() < 1) {
                _submitVisible.value = false
                _clearVisible.value = false
            }
            _submitVisible.value = true // odstranit potom
            _playVisible.value = false //false
        }
    }

    fun zapis(meno: String, context: FragmentActivity?, textTrue: String, textFalse: String) {
        viewModelScope.launch {
            if (pocetUsers() >= 1 && meno != meno(meno)) {
                val newUser = User(maxId() + 1, meno, 0)
                insert(newUser)
                Toast.makeText(context, textTrue, Toast.LENGTH_LONG).show()
                _clearVisible.value = true
            } else if (pocetUsers() < 1) {
                val newUser = User(1 , meno, 0)
                insert(newUser)
                Toast.makeText(context, textTrue, Toast.LENGTH_LONG).show()
                _clearVisible.value = true
            } else {
                Toast.makeText(context, textFalse, Toast.LENGTH_LONG).show()
            }
        }
    }

    fun onClear() {
        viewModelScope.launch {
            clear()
            _clearVisible.value = false
        }
    }

    fun onClick() {
        setPlayButtonVisibility()
    }

    fun updateScore(hodnota: Int) {
        viewModelScope.launch {
            val user: User = database.get(1)
            user.score = hodnota
            database.update(user)
        }
    }

    private fun setPlayButtonVisibility() {
        _playVisible.value = _playVisible.value != true
    }

    private suspend fun maxId(): Int = database.maxId()

    private suspend fun insert(user: User) = database.insert(user)

    private suspend fun clear() = database.clear()

    private suspend fun meno(i: String) = database.meno(i)

    private suspend fun pocetUsers() = database.countUser()

    fun onUserClicked(userId: Int) {
        //setPlayButtonVisibility()

    }
}