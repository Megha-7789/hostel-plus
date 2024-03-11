package com.example.hostell_plus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class studentpasswordchange : AppCompatActivity() {

    lateinit var donebutton: Button
    lateinit var dbhelper:DatabaseHelper
    lateinit var etPassword: EditText
    lateinit var etConfirmPassword: EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_studentpasswordchange)

        donebutton = findViewById(R.id.donebutton)
        dbhelper = DatabaseHelper(this)
        etPassword = findViewById(R.id.newpassword)
        etConfirmPassword = findViewById(R.id.newconfirmpassword)


        donebutton.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "Changed Password.", Toast.LENGTH_SHORT).show()
            val password = etPassword.text.toString()
            val confirmPassword = etConfirmPassword.text.toString()
            if (password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please enter both password and confirm password", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            if (password != confirmPassword) {
                Toast.makeText(this, "Password and confirm password do not match", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            val intent = Intent(this@studentpasswordchange, student_screen::class.java)
            startActivity(intent)
        })
    }

}