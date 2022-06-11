package com.example.arrows.level

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LevelViewModel : ViewModel() {

    private val _text = MutableLiveData(" ")
    val text: LiveData<String>
        get() = _text


}