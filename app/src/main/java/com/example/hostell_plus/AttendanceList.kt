package com.example.hostell_plus

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AttendanceListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var databaseHelper: DatabaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance_list)

        // Set up action bar with a back button
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.recyclerView)
        databaseHelper = DatabaseHelper(this)

        // Retrieve attendance data from the database
        val attendanceList = getAttendanceData()

        // Initialize and set up RecyclerView
        val adapter = AttendanceListAdapter(attendanceList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        // Handle the action bar's back button click
        onBackPressed()
        return true
    }
    private fun getAttendanceData(): List<AttendanceData> {
        val db = databaseHelper.readableDatabase
        val cursor = db.query(
            DatabaseHelper.TABLE_ATTENDANCE,
            null,
            null,
            null,
            null,
            null,
            "${DatabaseHelper.COLUMN_TIMESTAMP} DESC"
        )

        val attendanceList = mutableListOf<AttendanceData>()
        cursor.use {
            while (it.moveToNext()) {
                val studentName = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_STUDENT_NAME))
                val timestamp = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TIMESTAMP))
                val attendanceData = AttendanceData(studentName, timestamp)
                attendanceList.add(attendanceData)
            }
        }

        return attendanceList
    }
}
