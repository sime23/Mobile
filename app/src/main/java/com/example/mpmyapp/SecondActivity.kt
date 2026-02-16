package com.example.mpmyapp

import android.graphics.Color
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlin.random.Random

class SecondActivity : AppCompatActivity() {

    private var randomNumber = 0
    private var attempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val guessInput = findViewById<EditText>(R.id.guessInput)
        val btnGuess = findViewById<Button>(R.id.btnGuess)
        val btnReset = findViewById<Button>(R.id.btnReset)
        val feedbackText = findViewById<TextView>(R.id.feedbackText)
        val attemptsText = findViewById<TextView>(R.id.attemptsText)
        val welcomeText = findViewById<TextView>(R.id.textViewWelcome)

        val name = intent.getStringExtra("USER_NAME")
        welcomeText.text = "Welcome, $name!"

        resetGame()

        btnGuess.setOnClickListener {
            val guessString = guessInput.text.toString()

            if (guessString.isEmpty()) {
                // Changing color to red when there's an error
                feedbackText.setTextColor(ContextCompat.getColor(this, R.color.error_red))
                feedbackText.text = "Please enter a number!"
                return@setOnClickListener
            }

            val guess = guessString.toInt()
            attempts++
            attemptsText.text = "Attempts: $attempts"

            // Change color back to primary for normal feedback
            feedbackText.setTextColor(ContextCompat.getColor(this, R.color.dark_gold))

            when {
                guess < randomNumber -> feedbackText.text = "Too low! Try again."
                guess > randomNumber -> feedbackText.text = "Too high! Try again."
                else -> {
                    feedbackText.setTextColor(Color.GREEN) // Success color
                    feedbackText.text = "You got it in $attempts attempts!"
                    btnGuess.isEnabled = false
                }
            }
            guessInput.text.clear()
        }

        btnReset.setOnClickListener {
            resetGame()
        }
    }

    private fun resetGame() {
        randomNumber = Random.nextInt(1, 101)
        attempts = 0
        val feedbackText = findViewById<TextView>(R.id.feedbackText)
        feedbackText.setTextColor(ContextCompat.getColor(this, R.color.dark_gold))
        feedbackText.text = "Start guessing!"
        findViewById<TextView>(R.id.attemptsText).text = "Attempts: 0"
        findViewById<EditText>(R.id.guessInput).text.clear()
        findViewById<Button>(R.id.btnGuess).isEnabled = true
    }
}
