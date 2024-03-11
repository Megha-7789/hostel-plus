package com.example.hostell_plus

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale

class regstudent_screen : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var submitButton: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_regstudent_screen)
        Log.d("Debug", "regstudent_screen onCreate()")

        dbHelper = DatabaseHelper(this)
        submitButton = findViewById(R.id.SUBMIT)

        submitButton.setOnClickListener {
            saveStudentDetails()
        }
    }

    private fun saveStudentDetails() {
        val name = findViewById<EditText>(R.id.editTextText).text.toString()
        val email = findViewById<EditText>(R.id.emailaddEmailAddress).text.toString()
        val phone = findViewById<EditText>(R.id.phnoPhone).text.toString()
        val address = findViewById<EditText>(R.id.addressPostalAddress).text.toString()
        val regNumber = findViewById<EditText>(R.id.regNumber).text.toString()
        val fatherName = findViewById<EditText>(R.id.fatherText).text.toString()
        val motherName = findViewById<EditText>(R.id.MotherText).text.toString()
        val roomNumber = findViewById<EditText>(R.id.roomNumber).text.toString().toIntOrNull() ?: 0
        val year = findViewById<EditText>(R.id.yeartext).text.toString().toIntOrNull() ?: 0
        val parentphone = findViewById<EditText>(R.id.phnoPhone).text.toString()
        val dob = findViewById<EditText>(R.id.dobtext).text.toString()
        val password ="12345"


        if (!isValidPhoneNumber(phone)) {
            Toast.makeText(this, "Invalid phone number.", Toast.LENGTH_SHORT).show()
            return
        }
        if(!isValidParentPhoneNumber(parentphone)){
            Toast.makeText(this,"Invalid phone number.",Toast.LENGTH_SHORT).show()
            return
        }

        if (!isValidEmail(email)) {
            Toast.makeText(this, "Invalid email address.", Toast.LENGTH_SHORT).show()
            return
        }
        if(!isValidDateOfBirth(dob)){
            Toast.makeText(this,"Invalid format of date of birth",Toast.LENGTH_SHORT).show()
            return
        }

        val student = StudentData(
            name = name,
            email = email,
            phone = phone,
            address = address,
            registrationNumber = regNumber,
            fatherName = fatherName,
            motherName = motherName,
            roomNumber = roomNumber,
            year = year,
            parentphone = parentphone,
            dob =dob,
            password =password
        )

        val insertedId = try {
            dbHelper.insertStudent(student)
        } catch (e: Exception) {
            Log.e("DatabaseError", "Error inserting student data: ${e.message}")
            -1L
        }

        if (insertedId != -1L) {
            Toast.makeText(this, "Your data saved.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Error saving data.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidPhoneNumber(phone: String): Boolean {
        // Implement your phone number validation logic here
        return phone.isNotEmpty() && phone.length >= 10 // Adjust as needed
    }
    private fun isValidParentPhoneNumber(parentphone: String): Boolean {
        // Implement your phone number validation logic here
        return parentphone.isNotEmpty() && parentphone.length >= 10 // Adjust as needed
    }

    private fun isValidEmail(email: String): Boolean {
        // Implement your email validation logic here
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isValidDateOfBirth(dob: String): Boolean {
        // Implement your date of birth validation logic here
        // You can use SimpleDateFormat or other date validation libraries
        // Example: Check if the input is a valid date in a specific format
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.isLenient = false // Disable lenient parsing
        return try {
            val parsedDate = dateFormat.parse(dob)
            parsedDate != null
        } catch (e: ParseException) {
            false
        }
    }
}
