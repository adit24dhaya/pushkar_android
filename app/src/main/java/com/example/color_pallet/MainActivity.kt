package com.example.color_pallet

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    // UI components
    private lateinit var colorView: View
    private lateinit var switchRed: SwitchCompat
    private lateinit var switchGreen: SwitchCompat
    private lateinit var switchBlue: SwitchCompat
    private lateinit var seekBarRed: SeekBar
    private lateinit var seekBarGreen: SeekBar
    private lateinit var seekBarBlue: SeekBar
    private lateinit var editTextRed: EditText
    private lateinit var editTextGreen: EditText
    private lateinit var editTextBlue: EditText
    private lateinit var resetButton: Button
    private lateinit var colorCodeTextView: TextView


    // RGB values
    private var red = 0
    private var green = 0
    private var blue = 0

    private var redBackup = 0
    private var greenBackup = 0
    private var blueBackup = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize components
        colorView = findViewById(R.id.colorView)
        switchRed = findViewById(R.id.switch1)
        switchGreen = findViewById(R.id.switch2)
        switchBlue = findViewById(R.id.switch3)
        seekBarRed = findViewById(R.id.seekBar4)
        seekBarGreen = findViewById(R.id.seekBar5)
        seekBarBlue = findViewById(R.id.seekBar6)
        editTextRed = findViewById(R.id.editTextRed)
        editTextGreen = findViewById(R.id.editTextGreen)
        editTextBlue = findViewById(R.id.editTextBlue)
        resetButton = findViewById(R.id.button)
        colorCodeTextView = findViewById(R.id.textView)

        // Initialize listeners
        setupListeners()
    }

    private fun setupListeners() {
        // Switch for enabling/disabling Red control
        switchRed.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Restore the previous red value
                red = redBackup
                seekBarRed.progress = red
                editTextRed.setText(String.format(Locale.US, "%.3f", red / 255.0f))
            } else {
                // Save the current red value and set it to 0
                redBackup = red
                red = 0
                seekBarRed.progress = red
                editTextRed.setText("0.000")
            }
            seekBarRed.isEnabled = isChecked
            editTextRed.isEnabled = isChecked
            updateColor()
        }

        // Switch for enabling/disabling Green control
        switchGreen.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Restore the previous green value
                green = greenBackup
                seekBarGreen.progress = green
                editTextGreen.setText(String.format(Locale.US, "%.3f", green / 255.0f))
            } else {
                // Save the current green value and set it to 0
                greenBackup = green
                green = 0
                seekBarGreen.progress = green
                editTextGreen.setText("0.000")
            }
            seekBarGreen.isEnabled = isChecked
            editTextGreen.isEnabled = isChecked
            updateColor()
        }

        // Switch for enabling/disabling Blue control
        switchBlue.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // Restore the previous blue value
                blue = blueBackup
                seekBarBlue.progress = blue
                editTextBlue.setText(String.format(Locale.US, "%.3f", blue / 255.0f))
            } else {
                // Save the current blue value and set it to 0
                blueBackup = blue
                blue = 0
                seekBarBlue.progress = blue
                editTextBlue.setText("0.000")
            }
            seekBarBlue.isEnabled = isChecked
            editTextBlue.isEnabled = isChecked
            updateColor()
        }


        // SeekBar listener for Red
        seekBarRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                red = progress
                val redScaled = red / 255.0f
                editTextRed.setText(String.format(Locale.US, "%.3f", redScaled))
                updateColor()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // EditText listener for Red
        editTextRed.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) { // When the user leaves the EditText
                val input = editTextRed.text.toString().toFloatOrNull()
                if (input != null && input in 0.0..1.0) {
                    red = (input * 255).toInt()
                    seekBarRed.progress = red
                    updateColor()
                } else {
                    editTextRed.setText(String.format(Locale.US, "%.3f", red / 255.0f))
                }
            }
        }

        // SeekBar listener for Green
        seekBarGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                green = progress
                val greenScaled = green / 255.0f
                editTextGreen.setText(String.format(Locale.US, "%.3f", greenScaled))
                updateColor()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // EditText listener for Green
        editTextGreen.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) { // When the user leaves the EditText
                val input = editTextGreen.text.toString().toFloatOrNull()
                if (input != null && input in 0.0..1.0) {
                    green = (input * 255).toInt()
                    seekBarGreen.progress = green
                    updateColor()
                } else {
                    editTextGreen.setText(String.format(Locale.US, "%.3f", green / 255.0f))
                }
            }
        }

        // SeekBar listener for Blue
        seekBarBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                blue = progress
                val blueScaled = blue / 255.0f
                editTextBlue.setText(String.format(Locale.US, "%.3f", blueScaled))
                updateColor()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // EditText listener for Blue
        editTextBlue.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) { // When the user leaves the EditText
                val input = editTextBlue.text.toString().toFloatOrNull()
                if (input != null && input in 0.0..1.0) {
                    blue = (input * 255).toInt()
                    seekBarBlue.progress = blue
                    updateColor()
                } else {
                    editTextBlue.setText(String.format(Locale.US, "%.3f", blue / 255.0f))
                }
            }
        }

        // Reset button listener to reset RGB values and UI
        resetButton.setOnClickListener {
            resetValues()
        }
    }

    // Method to update the color based on the current RGB values
    private fun updateColor() {
        val redScaled = red / 255.0f
        val greenScaled = green / 255.0f
        val blueScaled = blue / 255.0f

        val color = Color.rgb(
            (redScaled * 255).toInt(),
            (greenScaled * 255).toInt(),
            (blueScaled * 255).toInt()
        )

        // Set the color of the view
        colorView.setBackgroundColor(color)

        // Update the TextView with the hexadecimal color code
        val hexColor = String.format("#%02X%02X%02X", red, green, blue)
        colorCodeTextView.text = hexColor

        // Set the background color of the TextView to match the generated color
        colorCodeTextView.setBackgroundColor(color)
    }


    // Method to reset all values to 0
    private fun resetValues() {
        seekBarRed.progress = 0
        seekBarGreen.progress = 0
        seekBarBlue.progress = 0
        editTextRed.setText("0")
        editTextGreen.setText("0")
        editTextBlue.setText("0")
        colorView.setBackgroundColor(Color.rgb(0, 0, 0))
    }
}
