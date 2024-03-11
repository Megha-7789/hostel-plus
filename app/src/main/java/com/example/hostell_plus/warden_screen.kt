package com.example.hostell_plus

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class warden_screen : AppCompatActivity() {

    lateinit var regButton: Button
    lateinit var roomButton : Button
    lateinit var viewbutton : Button
    lateinit var complaintbutton : Button
    lateinit var editnoticeboardbutton :Button
    lateinit var connectparentbutton : Button
    lateinit var right_icon:ImageButton
    lateinit var addwardenButton:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warden_screen)
        Log.d("Debug", "warden_screen onCreate()")
        regButton = findViewById(R.id.regButton)
        roomButton = findViewById(R.id.roomButton)
        viewbutton = findViewById(R.id.viewdetailsButton)
        complaintbutton = findViewById(R.id.ViewcomplaintsButton)
        editnoticeboardbutton = findViewById(R.id.editnoticeButton)
        connectparentbutton = findViewById(R.id.connectparentButton)
        right_icon=findViewById(R.id.right_icon)
        addwardenButton = findViewById(R.id.addwardenButton)

        regButton.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "You clicked on sign up button.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@warden_screen, regstudent_screen::class.java)
            startActivity(intent)
        })
        roomButton.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "You clicked on attendance button.", Toast.LENGTH_SHORT).show()

            val intent = Intent(this@warden_screen, rooms::class.java)
            intent.putExtra("studentName", "John Doe") // Replace with actual student name
            startActivity(intent)
        })

        viewbutton.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "You clicked on sign up button.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@warden_screen, view_details::class.java)
            startActivity(intent)
        })
        complaintbutton.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "You clicked on sign up button.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@warden_screen, viewcomplaints::class.java)
            startActivity(intent)
        })
        editnoticeboardbutton.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "You clicked on sign up button.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@warden_screen, NoticeBoard::class.java)
            startActivity(intent)
        })
        connectparentbutton.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "You clicked on sign up button.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@warden_screen, connect_parent::class.java)
            startActivity(intent)
        })
        addwardenButton.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "You clicked on sign up button.", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@warden_screen, reg_screen::class.java)
            startActivity(intent)
        })
        right_icon.setOnClickListener(View.OnClickListener {

            Toast.makeText(this, "THANK YOU", Toast.LENGTH_SHORT).show()
            val intent = Intent(this@warden_screen, navigation_drawer::class.java)
            startActivity(intent)
        })
    }
}
