package com.example.run4u.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.run4u.R
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import kotlin.math.sqrt

class fitcount : AppCompatActivity(), SensorEventListener {

    private var steps: Float = 0f
    private var totalSteps: Float = 0f
    private var previousTotalSteps: Float = 0f
    private var magnitudePreviousStep = 0.0
    private lateinit var sensorManager: SensorManager
    private var isPaused = false

    private val ACTIVITY_RECOGNITION_REQUEST_CODE = 100

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fitcount) // Use the layout where the back icon is placed

        // Find the back icon by its ID
        val backIcon: ImageView = findViewById(R.id.btn_back)

        // Set click listener to navigate back to the homepage
        backIcon.setOnClickListener {
            finish() // Finishes the current activity and returns to the previous one
        }

        // Check and request permissions if needed
        if (!isPermissionGranted()) {
            requestPermission()
        }

        // Initialize sensor manager
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        // Load saved step count
        loadData()

        // Set up button functionalities
        setupButtons()
    }

    private fun setupButtons() {
        val resetButton = findViewById<Button>(R.id.btn_reset)
        val pauseButton = findViewById<Button>(R.id.btn_pause)
        val resumeButton = findViewById<Button>(R.id.btn_resume)
        val stepTaken = findViewById<TextView>(R.id.step_current)

        resetButton.setOnClickListener {
            previousTotalSteps = totalSteps
            stepTaken.text = "0"
            saveData()

            // Log reset step count
            Log.d("StepTracker", "Steps reset. Previous total steps: $previousTotalSteps")
        }

        pauseButton.setOnClickListener {
            isPaused = true
            sensorManager.unregisterListener(this)
            Toast.makeText(this, "Step tracking paused", Toast.LENGTH_SHORT).show()
            Log.d("StepTracker", "Step tracking paused")
        }

        resumeButton.setOnClickListener {
            isPaused = false
            registerSensors()
            Toast.makeText(this, "Step tracking resumed", Toast.LENGTH_SHORT).show()
            Log.d("StepTracker", "Step tracking resumed")
        }
    }

    private fun saveData() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("step", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putFloat("currentstep", previousTotalSteps)
        editor.apply()
    }

    private fun loadData() {
        val sharedPreferences: SharedPreferences = getSharedPreferences("step", Context.MODE_PRIVATE)
        previousTotalSteps = sharedPreferences.getFloat("currentstep", 0f)
        Log.d("StepTracker", "Loaded previous total steps: $previousTotalSteps")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (isPaused) return

        val stepTaken = findViewById<TextView>(R.id.step_current)
        val circularBar = findViewById<CircularProgressBar>(R.id.progress_circular)

        if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER) {
            totalSteps = event.values[0]
            val currentSteps = totalSteps - previousTotalSteps
            stepTaken.text = currentSteps.toInt().toString()

            circularBar.setProgressWithAnimation(currentSteps)

            // Log the current step count
            Log.d("StepTracker", "Step count: $currentSteps")
        } else if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val xAccel = event.values[0]
            val yAccel = event.values[1]
            val zAccel = event.values[2]

            val magnitude = sqrt((xAccel * xAccel + yAccel * yAccel + zAccel * zAccel).toDouble())
            val magnitudeDelta = magnitude - magnitudePreviousStep
            magnitudePreviousStep = magnitude

            if (magnitudeDelta > 6) {
                totalSteps++
                stepTaken.text = totalSteps.toInt().toString()

                circularBar.setProgressWithAnimation(totalSteps)

                // Log the current step count
                Log.d("StepTracker", "Step count: ${totalSteps.toInt()}")
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}

    override fun onResume() {
        super.onResume()
        if (!isPaused) {
            registerSensors()
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
        Log.d("StepTracker", "Sensors unregistered")
    }

    private fun registerSensors() {
        val stepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
        val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        if (stepSensor != null) {
            sensorManager.registerListener(this, stepSensor, SensorManager.SENSOR_DELAY_UI)
            Log.d("StepTracker", "Step sensor registered")
        } else if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI)
            Log.d("StepTracker", "Accelerometer registered")
        } else {
            Toast.makeText(this, "Your device is not compatible", Toast.LENGTH_LONG).show()
            Log.d("StepTracker", "No compatible sensor available")
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.ACTIVITY_RECOGNITION
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACTIVITY_RECOGNITION),
                ACTIVITY_RECOGNITION_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == ACTIVITY_RECOGNITION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
                Log.d("StepTracker", "Permission granted")
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                Log.d("StepTracker", "Permission denied")
            }
        }
    }
}
