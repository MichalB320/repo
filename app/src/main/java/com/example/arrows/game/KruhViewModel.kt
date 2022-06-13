package com.example.arrows.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val POLOMER_KRUHU = 145f

class KruhViewModel : ViewModel() {
    private val _rotKruhu = MutableLiveData(0f)
    val rotKruhu: LiveData<Float>
        get() = _rotKruhu

    fun getPolomerKruhu() = POLOMER_KRUHU

    fun rotuj() {
        _rotKruhu.value = (_rotKruhu.value)?.plus(3.9999999999999999999f)
    }
}