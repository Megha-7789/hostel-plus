package com.example.hostell_plus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class matron_screen : AppCompatActivity() {

    lateinit var billbutton : Button
    lateinit var roombutton : Button
    lateinit var viewbutton : Button
    lateinit var complaintbutton : Button
    lateinit var duebutton : Button
    lateinit var right_icon: ImageButton


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matron_screen)

        billbutton = findViewById(R.id.billButton)
        roombutton = findViewById(R.id.roomButton)
        viewbutton = findViewById(R.id.viewdetailsButton)
        complaintbutton = findViewById(R.id.ViewcomplaintsButton)
        duebutton = findViewById(R.id.duebutton)
        right_icon = findViewById(R.id.right_icon)


        billbutton.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "You clicked on sign up button.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@matron_screen, upload_reciept::class.java)
            startActivity(intent)
        })
        roombutton.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "You clicked on sign up button.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@matron_screen, rooms::class.java)
            startActivity(intent)
        })
        viewbutton.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "You clicked on sign up button.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@matron_screen, view_details::class.java)
            startActivity(intent)
        })
        complaintbutton.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "You clicked on sign up button.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@matron_screen, viewcomplaints::class.java)
            startActivity(intent)
        })
        duebutton.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "You clicked on sign up button.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@matron_screen, NewNotification::class.java)
            startActivity(intent)
        })
        right_icon.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "THANK YOU", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@matron_screen, navigation_drawer::class.java)
            startActivity(intent)
        })
    }
}