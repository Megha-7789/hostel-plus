package com.example.hostell_plus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class reg_screen : AppCompatActivity() {

    lateinit var radioGroupUserType: RadioGroup
    lateinit var signup: Button
    lateinit var etPassword: EditText
    lateinit var etConfirmPassword: EditText
    lateinit var username: EditText
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_screen)

        databaseHelper = DatabaseHelper(this)
        radioGroupUserType = findViewById(R.id.radioGroupUserType)
        signup = findViewById(R.id.signup)
        etPassword = findViewById(R.id.password)
        etConfirmPassword = findViewById(R.id.confirmpassword)
        username = findViewById(R.id.username)

        signup.setOnClickListener {

            val userType = when (radioGroupUserType.checkedRadioButtonId) {
                R.id.radioButtonWarden -> "Warden"
                else -> ""
            }

            if (userType.isEmpty()) {
                Toast.makeText(this, "Please select a user type", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val enteredUsername = username.text.toString()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()

            if (password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please enter both password and confirm password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Password and confirm password do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val insertedId = databaseHelper.insertUser(enteredUsername, userType, password)
            if (insertedId != -1L) {
                Toast.makeText(this, "User inserted with ID: $insertedId", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Error inserting user.", Toast.LENGTH_SHORT).show()
            }

            // Now, navigate to the appropriate home screen based on userType
            val intent = when (userType) {
                "Warden" -> Intent(this@reg_screen, warden_screen::class.java)
                else -> {
                    Toast.makeText(this, "Invalid user type.", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            startActivity(intent)
        }
    }
}
