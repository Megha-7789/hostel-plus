package com.example.hostell_plus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class student_screen : AppCompatActivity() {

    private lateinit var uploadbutton: Button
    private lateinit var attendencebutton: Button
    private lateinit var viewbutton: Button
    private lateinit var complaintbutton: Button
    private lateinit var paymentbutton: Button
    private lateinit var right_icon: ImageButton
    private lateinit var viewnoticeboard: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_screen)

        uploadbutton = findViewById(R.id.UploadButton)
        attendencebutton = findViewById(R.id.attendButton)
        viewbutton = findViewById(R.id.viewdetailsButton)
        complaintbutton = findViewById(R.id.postcomplaintsButton)
        paymentbutton = findViewById(R.id.payment)
        right_icon = findViewById(R.id.right_icon)
        viewnoticeboard = findViewById(R.id.viewnoticeboard)

        uploadbutton.setOnClickListener {
            Toast.makeText(this, "Upload Button Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, uploadfeereciept::class.java)
            startActivity(intent)
        }

        attendencebutton.setOnClickListener {
            Toast.makeText(this, "Attendance Button Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ScanQRCode::class.java)
            startActivity(intent)
        }

        viewbutton.setOnClickListener {
            Toast.makeText(this, "View Button Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, view_details::class.java)
            startActivity(intent)
        }

        complaintbutton.setOnClickListener {
            Toast.makeText(this, "Complaints Button Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, postcomplaints::class.java)
            startActivity(intent)
        }

        viewnoticeboard.setOnClickListener {
            Toast.makeText(this, "View Notice Board Button Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ViewNoticeBoard::class.java)
            startActivity(intent)
        }

        paymentbutton.setOnClickListener {
            Toast.makeText(this, "Payment Button Clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, googlepay::class.java)
            startActivity(intent)
        }

        right_icon.setOnClickListener {
            Toast.makeText(this, "THANK YOU", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, navigation_drawer::class.java)
            startActivity(intent)
        }
    }
}
