package com.example.hostell_plus

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startButton = findViewById(R.id.startbutton)

        startButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "You clicked on start", Toast.LENGTH_SHORT).show()
            // Start the HomeScreen activity
            val intent = Intent(this@MainActivity, Login_screen::class.java)
            startActivity(intent)
        })
    }
}
