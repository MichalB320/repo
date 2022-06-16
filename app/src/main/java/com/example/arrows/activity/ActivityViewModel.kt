package com.example.arrows.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel pre Activity
 */
class ActivityViewModel : ViewModel() {
    private var _meno = MutableLiveData(" ")
    private var _score = MutableLiveData(0)
    val score: MutableLiveData<Int>
        get() = _score

    val meno: MutableLiveData<String>
        get() = _meno

    /**
     * nastaví score
     *
     * @param hodnota
     */
    fun setScore(hodnota: Int) {
        _score.value = hodnota
    }

    /**
     * nastaví meno
     *
     * @param paMeno
     */
    fun setMeno(paMeno: String) {
        _meno.value = paMeno
    }
}