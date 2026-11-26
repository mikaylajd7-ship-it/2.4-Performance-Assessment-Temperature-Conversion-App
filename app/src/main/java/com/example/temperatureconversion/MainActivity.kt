package com.example.temperatureconversion

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

/*
 * Student: Mikayla Dickerson
 * Assignment: PA - Temperature Conversion App
 * Date: November 26, 2026
 */
class MainActivity : AppCompatActivity() {
    private lateinit var txtTemperature: EditText
    private lateinit var radFahrenheit: RadioButton
    private lateinit var radCelsius: RadioButton
    private lateinit var txtResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtTemperature = findViewById(R.id.txtTemperature)
        radFahrenheit = findViewById(R.id.radFahrenheit)
        radCelsius = findViewById(R.id.radCelsius)
        txtResult = findViewById(R.id.txtResult)
        val btnConvert = findViewById<Button>(R.id.btnConvert)

        btnConvert.setOnClickListener { convertTemperature() }
    }

    private fun convertTemperature() {
        val input = txtTemperature.text.toString().trim()
        if (input.isEmpty()) {
            showMessage("Please enter a temperature.")
            txtResult.text = ""
            return
        }

        val value = input.toDoubleOrNull()
        if (value == null) {
            showMessage("Please enter a valid number.")
            txtResult.text = ""
            return
        }

        if (radFahrenheit.isChecked) {
            if (value < -100.0 || value > 250.0) {
                showMessage("Fahrenheit must be between -100°F and 250°F.")
                txtResult.text = "Out of range: -100°F to 250°F."
                return
            }
            val celsius = (value - 32.0) * 5.0 / 9.0
            txtResult.text = String.format(Locale.US, "%.1f °C", celsius)
        } else if (radCelsius.isChecked) {
            if (value < -75.0 || value > 125.0) {
                showMessage("Celsius must be between -75°C and 125°C.")
                txtResult.text = "Out of range: -75°C to 125°C."
                return
            }
            val fahrenheit = (value * 9.0 / 5.0) + 32.0
            txtResult.text = String.format(Locale.US, "%.1f °F", fahrenheit)
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
