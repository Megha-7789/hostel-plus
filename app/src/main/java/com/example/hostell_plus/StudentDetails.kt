// StudentDetailsActivity.kt
package com.example.hostell_plus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentDetails : AppCompatActivity() {

    lateinit var searchbutton :FloatingActionButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_details)
        searchbutton = findViewById(R.id.floatingActionButton)

        val studentDetails = intent.getStringExtra("student_details")

        val studentDetailsTextView = findViewById<TextView>(R.id.studentDetailsTextView)
        studentDetailsTextView.text = studentDetails
        searchbutton.setOnClickListener( View.OnClickListener {
            val intent = Intent(this,SearchStudent::class.java)
            startActivity(intent)
        })
    }
}
