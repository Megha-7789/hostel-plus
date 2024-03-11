package com.example.hostell_plus

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Login_screen : AppCompatActivity() {

    lateinit var radioGroupUserType: RadioGroup
    lateinit var signup: Button
    lateinit var login: Button
    lateinit var password :EditText
    lateinit var username:EditText
    lateinit var databaseHelper: DatabaseHelper

    private lateinit var binding : Login_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        databaseHelper = DatabaseHelper(this)

        radioGroupUserType = findViewById(R.id.radioGroupUserType)
        signup = findViewById(R.id.signup)
        login = findViewById(R.id.loginButton)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)

        login.setOnClickListener(View.OnClickListener {
            // For demonstration purposes, assume userType contains "warden," "matron," or "student"
            val userType = when (radioGroupUserType.checkedRadioButtonId) {
                R.id.radioButtonWarden -> "Warden"
                R.id.radioButtonMatron -> "Matron"
                R.id.radioButtonStudent -> "Student"
                else -> ""
            } // Replace this with the actual user type

            if (userType.isEmpty()) {
                Toast.makeText(this, "Please select a user type", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            val password = password.text.toString()
            val username= username.text.toString()

            if (password.isEmpty()|| username.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            // Check the user type and navigate accordingly
            when (userType) {
                "Warden" -> {
                    // Start the Warden HomeScreen activity
                    val intent = Intent(this@Login_screen, warden_screen::class.java)
                    startActivity(intent)
                }

                "Matron" -> {
                    // Start the Matron HomeScreen activity
                    val intent = Intent(this@Login_screen, matron_screen::class.java)
                    startActivity(intent)
                }

                "Student" -> {
                    // Start the Student HomeScreen activity
                    val intent = Intent(this@Login_screen, studentpasswordchange::class.java)
                    startActivity(intent)
                }

                else -> {
                    // Handle other cases or show an error message
                    Toast.makeText(this, "Invalid user type.", Toast.LENGTH_SHORT).show()
                }
            }
            logindatabase(username,password,userType)
        })

        signup.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "You clicked on sign up button.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@Login_screen, reg_screen::class.java)
            startActivity(intent)
        })


    }

    private fun logindatabase(username: String, password: String, userType: String) {

        val userExist = databaseHelper.isValidCredentials(username, password, userType)
        if (userExist) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            val intent=Intent(this,Login_screen::class.java)
            startActivity(intent)
        }
    }

}
