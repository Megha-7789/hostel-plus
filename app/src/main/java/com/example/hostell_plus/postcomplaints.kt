package com.example.hostell_plus

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class postcomplaints : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var complaintEditText: EditText
    private lateinit var submitComplaintButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_postcomplaints)

        dbHelper = DatabaseHelper(this)
        complaintEditText = findViewById(R.id.complaintEditText)
        submitComplaintButton = findViewById(R.id.submitComplaintButton)

        submitComplaintButton.setOnClickListener {
            val complaintText = complaintEditText.text.toString().trim()

            if (complaintText.isNotEmpty()) {
                // Get the student's ID from your authentication system
                val studentId = 1 // Replace with the actual student ID

                val insertedId = dbHelper.insertComplaint(complaintText, studentId.toLong())

                if (insertedId != -1L) {
                    // Successfully inserted complaint
                    complaintEditText.text.clear()
                } else {
                    // Error occurred while inserting
                }
            }
        }
    }
}
