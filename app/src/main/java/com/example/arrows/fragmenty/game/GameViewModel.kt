package com.example.arrows.fragmenty.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel pre Hru
 */
class GameViewModel : ViewModel() {
    private var _pohybujSa = false
    private var _stlacil = false
    private var _index = 0
    private val _score = MutableLiveData(0)
    private val _currentArrowCount = MutableLiveData(0)
    private val _koliduje = MutableLiveData(false)
    val koliduje: LiveData<Boolean>
        get() = _koliduje

    val stlacil: Boolean
        get() = _stlacil

    val index: Int
        get() = _index

    val score: LiveData<Int>
        get() = _score

    val currentArrowCount: LiveData<Int>
        get() = _currentArrowCount

    val pohybujSa: Boolean
        get() = _pohybujSa

    /**
     * nastavenie pohybovania šipky pomocou senzora
     *
     * @param value
     */
    fun setSensor(value: Float) {
        if (value > 0f) {
            _pohybujSa = true
        }
    }

    /**
     * šipka sa prestane pohybovat
     */
    fun nepohybujSa() { _pohybujSa = false }

    /**
     * prezeranie kolidvacie hodnoty (true || false)
     */
    fun prehral() { _koliduje.value = _koliduje.value == false }

    /**
     * stlacene tlacislo PAL
     */
    fun onPal() { _stlacil = true }

    /**
     * nestlacene tlacidlo PAL
     */
    fun nestlacil() { _stlacil = false }

    /**
     * zvíši index šípok
     */
    fun zvysIndex() = _index++

    /**
     * navýšnie score 15
     */
    fun pripocitajScore() { _score.value = (_score.value)?.plus(15) }

    /**
     * zvyšuje počitanie šipok
     */
    fun pocitadlo() { _currentArrowCount.value = (_currentArrowCount.value)?.inc() }
}