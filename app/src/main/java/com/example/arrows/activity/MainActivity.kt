package com.example.arrows.activity

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.arrows.R

/**
 * This main activity is just a container for our fragments,
 * where the real action is.
 */
class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private lateinit var events: SensorEvent

    /**
     * Vytvorí activitu
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_main)

        pripravSensor()
    }

    /**
     * Pripraví sensory - inicializuje senesorManager
     */
    private fun pripravSensor() {
    sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also {
            sensorManager.registerListener(
                this,
                it,
                SensorManager.SENSOR_DELAY_FASTEST,
                SensorManager.SENSOR_DELAY_FASTEST
            )
        }
    }

    /**
     * Každý pohyb senzora ukladá do atribútu events
     *
     * @param event
     */
    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            events = event
        }
    }

    /**
     * Zistuje zmenu presnosti senzora
     *
     * @param accuracy
     * @param sensor
     * @return
     */
    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        return
    }

    /**
     * Vráti atribut events
     *
     * @return events
     */
    fun getEvent(): SensorEvent {
        return events
    }
}




















