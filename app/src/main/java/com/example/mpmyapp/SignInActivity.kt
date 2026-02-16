package com.example.mpmyapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        // 1. References to UI components
        val email = findViewById<EditText>(R.id.editTextTextEmailAddress) // Email input field
        val password = findViewById<EditText>(R.id.editTextTextPassword) // Password input field
        val signInBtn = findViewById<Button>(R.id.signIn1) // Login button
        val signUpBtn = findViewById<Button>(R.id.signUp1) // Button to go to signup page

        // 2. Initial State: Button is disabled until fields are valid
        signInBtn.isEnabled = false
        signInBtn.alpha = 0.5f // Make it look faded when disabled

        /**
         * Function to validate the form inputs.
         * It checks if the email is valid and if the password is at least 8 characters.
         */
        fun validateSignIn() {
            val emailVal = email.text.toString()
            val passVal = password.text.toString()

            var isValid = true

            // Email format validation (e.g., user@email.com)
            if (!Patterns.EMAIL_ADDRESS.matcher(emailVal).matches()) {
                email.error = if (emailVal.isNotEmpty()) "Invalid email" else null
                isValid = false
            } else {
                email.error = null // Clear error if format is correct
            }

            // Password length validation (at least 8 characters)
            if (passVal.length < 8) {
                password.error = if (passVal.isNotEmpty()) "At least 8 characters" else null
                isValid = false
            } else {
                password.error = null // Clear error if length is correct
            }

            // 3. Update Button State based on validation
            signInBtn.isEnabled = isValid
            signInBtn.alpha = if (isValid) 1.0f else 0.5f // Change transparency for visual feedback
        }

        // 4. Attach live listeners to trigger validation while typing
        email.doAfterTextChanged { validateSignIn() }
        password.doAfterTextChanged { validateSignIn() }

        // 5. Click listener for the Login button
        signInBtn.setOnClickListener {
            // Proceed to the game page (SecondActivity)
            val intent = Intent(this, SecondActivity::class.java)
            // We can pass a dummy name for now since it's a login
            intent.putExtra("USER_NAME", email.text.toString().substringBefore("@"))
            startActivity(intent)
        }

        // 6. Click listener to navigate back to the Sign Up page
        signUpBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
