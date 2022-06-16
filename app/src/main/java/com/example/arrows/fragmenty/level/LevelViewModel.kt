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

/**
 * ViewModel pre levelFragment
 */
class LevelViewModel(private val database: UserDatabaseDao, application: Application) : AndroidViewModel(application) {
    private var _submitVisible = MutableLiveData(true)
    private var _clearVisible = MutableLiveData(false)
    private var _playVisible = MutableLiveData(false)
    private var _readyVisible = MutableLiveData(false)
    private val _text = MutableLiveData(" ")
    val text: LiveData<String>
        get() = _text

    val submitVisible: MutableLiveData<Boolean>
        get() = _submitVisible

    val clearVisible: MutableLiveData<Boolean>
        get() = _clearVisible

    val playVisible: MutableLiveData<Boolean>
        get() = _playVisible

    val readyVisible: MutableLiveData<Boolean>
        get() = _readyVisible

    val users = database.getAllUsers()

    private var _oznacil = false
    val oznacil: Boolean
        get() = _oznacil

    init {
        viewModelScope.launch {
            database.getAllUsers()
            if (pocetUsers() >= 1) {
                _clearVisible.value = true
            }
            if (pocetUsers() < 1) {
                _clearVisible.value = false
            }
        }
    }

    /**
     * metóda zpíše do databázy nového užívateľa
     *
     * @param meno, context, textTrue, textFalse
     */
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

    /**
     * kliknutím na tlačidla CLEAR sa databáza vymaže
     */
    fun onClear() {
        viewModelScope.launch {
            clear()
            _clearVisible.value = false
        }
    }

    /**
     * na kliknutie tlacidla v recycleView sa zobrazia tlacidla
     */
    fun onClick() {
        setReadyButtonVisibility(true)
        _oznacil = true
    }

//    fun updateScore(hodnota: Int) {
//        viewModelScope.launch {
//            val user: User = database.get(1)
//            user.score = hodnota
//            database.update(user)
//        }
//    }

    /**
     * PlayButton sa zviditelní
     */
    fun setPlayButtonVisibility() {
        _playVisible.value = true
    }

    /**
     * ReadyButton sa zviditelní podla parametra
     *
     * @param value
     */
    fun setReadyButtonVisibility(value: Boolean) {
        _readyVisible.value = value
    }

    private suspend fun maxId(): Int = database.maxId()

    private suspend fun insert(user: User) = database.insert(user)

    private suspend fun clear() = database.clear()

    private suspend fun meno(i: String) = database.meno(i)

    private suspend fun pocetUsers() = database.countUser()
}