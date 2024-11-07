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

    // RGB values
    private var red = 0
    private var green = 0
    private var blue = 0

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

        // Initialize listeners
        setupListeners()
    }

    private fun setupListeners() {
        // Switch for enabling/disabling Red control
        switchRed.setOnCheckedChangeListener { _, isChecked ->
            seekBarRed.isEnabled = isChecked
            updateColor()
        }

        // Switch for enabling/disabling Green control
        switchGreen.setOnCheckedChangeListener { _, isChecked ->
            seekBarGreen.isEnabled = isChecked
            updateColor()
        }

        // Switch for enabling/disabling Blue control
        switchBlue.setOnCheckedChangeListener { _, isChecked ->
            seekBarBlue.isEnabled = isChecked
            updateColor()
        }

        // SeekBar listener for Red
                seekBarRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        red = progress
                        val redScaled = red / 255.0f
                        editTextRed.setText(String.format(Locale.US, "%.2f", redScaled))
                        updateColor()
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
                })


        // SeekBar listener for Green
        seekBarGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                green = progress
                val greenScaled = green / 255.0f
                editTextGreen.setText(String.format(Locale.US, "%.2f", greenScaled))  // Specify Locale.US
                updateColor()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


        // SeekBar listener for Blue
        seekBarBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                blue = progress
                val blueScaled = blue / 255.0f
                editTextBlue.setText(String.format(Locale.US, "%.2f", blueScaled))  // Specify Locale.US
                updateColor()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })


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
