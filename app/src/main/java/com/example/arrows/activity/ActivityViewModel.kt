package com.example.arrows.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityViewModel : ViewModel() {
    private var _meno = MutableLiveData(" ")
    private var _score = MutableLiveData(0)
    val score: MutableLiveData<Int>
        get() = _score

    val meno: MutableLiveData<String>
        get() = _meno

    fun setScore(hodnota: Int) {
        _score.value = hodnota
    }

    fun setMeno(paMeno: String) {
        _meno.value = paMeno
    }
}