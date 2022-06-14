package com.example.arrows

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityViewModel : ViewModel() {

    private var _score = MutableLiveData(0)
    val score: MutableLiveData<Int>
        get() = _score

    fun setScore(hodnota: Int) {
        _score.value = hodnota
    }
}