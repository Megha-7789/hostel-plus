// SearchStudentActivity.kt
package com.example.hostell_plus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SearchStudent : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    lateinit var searchButton:Button
    lateinit var searchRegistrationNumberEditText :EditText

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_student)
        searchButton =findViewById(R.id.searchButton)
        searchRegistrationNumberEditText =findViewById(R.id.searchRegistrationNumberEditText)

        dbHelper = DatabaseHelper(this) // Initialize dbHelper

        searchButton.setOnClickListener {
            val registrationNumber = searchRegistrationNumberEditText.text.toString()
            val studentDetails = retrieveStudentDetailsFromDatabase(registrationNumber)

            if (studentDetails.isNotEmpty()) {
                val intent = Intent(this, StudentDetails::class.java)
                intent.putExtra("student_details", studentDetails)
                startActivity(intent)
            }
        }
    }

    private fun retrieveStudentDetailsFromDatabase(registrationNumber: String): String {
        val db = dbHelper.readableDatabase
        val projection = arrayOf(
            DatabaseHelper.COLUMN_NAME,
            DatabaseHelper.COLUMN_EMAIL,
            DatabaseHelper.COLUMN_PHONE,
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_ADDRESS,
            DatabaseHelper.COLUMN_REG_NUMBER,
            DatabaseHelper.COLUMN_FATHER_NAME,
            DatabaseHelper.COLUMN_MOTHER_NAME,
            DatabaseHelper.COLUMN_ROOM_NUMBER,
            DatabaseHelper.COLUMN_YEAR
            // Add other columns you want to retrieve
        )
        val selection = "${DatabaseHelper.COLUMN_REG_NUMBER} = ?"
        val selectionArgs = arrayOf(registrationNumber)

        val cursor = db.query(
            DatabaseHelper.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var studentDetails = ""

        cursor.use {
            if (it.moveToFirst()) {
                val name = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME))
                val email = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL))
                val phone = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PHONE))
                val Year = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_YEAR))
                val addr = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ADDRESS))
                val regnum = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_REG_NUMBER))
                val fathername = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_FATHER_NAME))
                val mothername = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MOTHER_NAME))
                val roomno = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ROOM_NUMBER))
                // Retrieve other columns as needed

                studentDetails = "Name: $name,\n Email: $email,\n Phone: $phone,\n Year: $Year,\n Address: $addr,\n Reg No: $regnum,\n Father: $fathername,\n Mother: $mothername,\n Room No: $roomno"
            }
        }

        return studentDetails
    }
}
