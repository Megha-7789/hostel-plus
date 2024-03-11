package com.example.hostell_plus

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class viewcomplaints : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var complaintsTextView: TextView
    data class Complaint(val complaintText: String)
    lateinit var complainttext :TextView


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewcomplaints)

        dbHelper = DatabaseHelper(this)
        complaintsTextView = findViewById(R.id.complaintsTextView)
        complainttext =findViewById(R.id.complaintext)

        // Retrieve complaints from the database
        val complaintsList = retrieveComplaints()

        // Display complaints in the TextView
        val complaintsText = complaintsList.joinToString("\n\n") { it.complaintText }
        complainttext.text = complaintsText
    }

    private fun retrieveComplaints(): List<Complaint> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_COMPLAINTS,
            null,
            null,
            null,
            null,
            null,
            null
        )

        val complaintsList = mutableListOf<Complaint>()
        cursor.use {
            while (it.moveToNext()) {
                val complaintText = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_COMPLAINT_TEXT))
                complaintsList.add(Complaint(complaintText))
            }
        }

        return complaintsList
    }
}
