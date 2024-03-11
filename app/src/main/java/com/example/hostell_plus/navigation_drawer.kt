package com.example.hostell_plus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class navigation_drawer : AppCompatActivity() {
    lateinit var logoutbutton:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_drawer)
        logoutbutton = findViewById(R.id.logout)

        logoutbutton?.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "You clicked on sign up button.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@navigation_drawer, Login_screen::class.java)
            startActivity(intent)
        })
    }
}