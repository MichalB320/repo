package com.example.arrows.fragmenty.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val POLOMER_KRUHU = 145f

/**
 * ViewModel pre Kruh
 */
class KruhViewModel : ViewModel() {
    private val _rotKruhu = MutableLiveData(0f)
    val rotKruhu: LiveData<Float>
        get() = _rotKruhu

    /**
     * vráti polomer kruhu
     *
     * @return Float
     */
    fun getPolomerKruhu() = POLOMER_KRUHU

    /**
     * Kruh rotuje
     */
    fun rotuj() { _rotKruhu.value = (_rotKruhu.value)?.plus(3.9999999999999999999f) }
}