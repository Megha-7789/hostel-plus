package com.example.hostell_plus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class view_details : AppCompatActivity() {

    private lateinit var year1: Button
    private lateinit var year2: Button
    private lateinit var year3: Button
    private lateinit var year4: Button
    private lateinit var dbHelper: DatabaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_details)

        dbHelper = DatabaseHelper(this) // Initialize dbHelper

        year1 = findViewById(R.id.year1)
        year2 = findViewById(R.id.year2)
        year3 = findViewById(R.id.year3)
        year4 = findViewById(R.id.year4)

        year1.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "Details of First year students.", Toast.LENGTH_SHORT).show()
            retrieveStudentDetailsFromDatabase(1)
        })

        year2.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "Details of Second year students.", Toast.LENGTH_SHORT).show()
            retrieveStudentDetailsFromDatabase(2)
        })

        year3.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "Details of Third year students.", Toast.LENGTH_SHORT).show()
            retrieveStudentDetailsFromDatabase(3)
        })

        year4.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "Details of Fourth year students.", Toast.LENGTH_SHORT).show()
            retrieveStudentDetailsFromDatabase(4)
        })
    }

    private fun retrieveStudentDetailsFromDatabase(year: Int) {
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
        val selection = "${DatabaseHelper.COLUMN_YEAR} = ?"
        val selectionArgs = arrayOf(year.toString())

        val cursor = db.query(
            DatabaseHelper.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val studentDetailsList = mutableListOf<String>()

        cursor.use {
            while (it.moveToNext()) {
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

                val studentDetails = "Name: $name,\n Email: $email,\n Phone: $phone,\n Year: $Year,\n Address: $addr,\n Reg No: $regnum,\n Father: $fathername,\n Mother: $mothername,\n Room No: $roomno"
                studentDetailsList.add(studentDetails)
            }
        }

        val studentDetailsText = studentDetailsList.joinToString("\n\n")

        // Open StudentDetailsActivity with student details
        val intent = Intent(this, StudentDetails::class.java)
        intent.putExtra("student_details", studentDetailsText)
        startActivity(intent)
    }
}
