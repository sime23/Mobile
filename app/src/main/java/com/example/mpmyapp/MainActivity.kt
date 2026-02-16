package com.example.mpmyapp

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. References to all UI input fields and buttons
        val signUp = findViewById<Button>(R.id.signUp) // The main registration button
        val signIn = findViewById<Button>(R.id.signIn) // Link to sign-in page
        val name = findViewById<EditText>(R.id.editTextText) // Full Name field
        val email = findViewById<EditText>(R.id.editTextTextEmailAddress) // Email field
        val phone = findViewById<EditText>(R.id.editTextPhone) // Phone number field
        val password = findViewById<EditText>(R.id.editTextTextPassword) // Password field
        val password2 = findViewById<EditText>(R.id.editTextTextPassword4) // Verify Password field
        val genderGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)
        var gender = "none"

        // 2. Initial setup: Disable the Signup button until form is valid
        signUp.isEnabled = false
        signUp.alpha = 0.5f // Set transparency to show it's disabled



// This listener triggers every time a selection changes

        /**
         * Core Validation Function: 
         * This runs every time a character is typed in any field.
         */

        fun validateForm() {
            val nameVal = name.text.toString()
            val emailVal = email.text.toString()
            val phoneVal = phone.text.toString()
            val passVal = password.text.toString()
            val pass2Val = password2.text.toString()

            var isFormValid = true // Assume valid until a check fails

            // Name check: Cannot be empty
            if (nameVal.isBlank()) {
                isFormValid = false
            }

            // Email check: Must match standard email pattern (user@email.com)
            if (!Patterns.EMAIL_ADDRESS.matcher(emailVal).matches()) {
                // Show error icon only if user has started typing
                email.error = if (emailVal.isNotEmpty()) "Invalid email format" else null
                isFormValid = false
            } else {
                email.error = null // Clear error when valid
            }

            // Phone check: Simple check for a minimum length (e.g., 9 digits)
            if (phoneVal.length < 9) {
                phone.error = if (phoneVal.isNotEmpty()) "Phone too short" else null
                isFormValid = false
            } else {
                phone.error = null
            }

            // Password check: Enforce at least 8 characters
            if (passVal.length < 8) {
                password.error = if (passVal.isNotEmpty()) "Min 8 characters" else null
                isFormValid = false
            } else {
                password.error = null
            }

            // Confirm Password: Check if both passwords match exactly
            if (pass2Val != passVal) {
                password2.error = if (pass2Val.isNotEmpty()) "Passwords don't match" else null
                isFormValid = false
            } else {
                password2.error = null
            }

            // 3. Update the button state based on validation results
            signUp.isEnabled = isFormValid
            signUp.alpha = if (isFormValid) 1.0f else 0.5f // Full opacity when enabled
        }

        // 4. Attach live listeners to every field so validation happens while typing
        val allFields = listOf(name, email, phone, password, password2)
        allFields.forEach { it.doAfterTextChanged { validateForm() } }

        //Navigation: Move to the game page upon successful signup
        signUp.setOnClickListener {
            val selectedId = genderGroup.checkedRadioButtonId

            if (selectedId != -1) {
                // Find the actual RadioButton by that ID to get its text
                val selectedRadioButton = findViewById<RadioButton>(selectedId)
                val genderValue = selectedRadioButton.text.toString()

                //Create the Intent to move to the next page
                val intent = Intent(this, HomeActivity::class.java)

                intent.putExtra("USER_NAME", name.text.toString())
                intent.putExtra("USER_GENDER", genderValue)

                startActivity(intent)
            } else {
                // Handle case where no gender was selected
                Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show()
            }

        }

        // 6. Navigation: Go to Sign In page
        signIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
    }
}
