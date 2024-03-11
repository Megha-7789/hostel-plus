package com.example.hostell_plus

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class connect_parent : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect_parent)

        databaseHelper = DatabaseHelper(this)

        val absentStudentData = getAbsentStudentData()
        sendNotificationsToParents(absentStudentData)
        // Now you can use the absentStudentData list as needed
    }

    private fun sendNotificationsToParents(absentStudents: List<StudentData>) {
        for (student in absentStudents) {
            val parentPhone = student.parentphone
            // Call a function to send a notification to the parent's phone number
            // Implement the notification sending mechanism here
            sendNotificationToParent(parentPhone)
        }
    }

    private fun sendNotificationToParent(parentPhone: String) {
        val notificationBuilder = NotificationCompat.Builder(this, "channelId")
            .setContentTitle("Absent Notification")
            .setContentText("Your child is absent today.")
            .setSmallIcon(R.drawable.baseline_notifications_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        val notificationManager = NotificationManagerCompat.from(this)
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

    private fun getAbsentStudentData(): List<StudentData> {
        val db = databaseHelper.readableDatabase
        val attendanceCursor = db.query(
            DatabaseHelper.TABLE_ATTENDANCE,
            arrayOf(DatabaseHelper.COLUMN_STUDENT_NAME),
            null,
            null,
            null,
            null,
            null
        )

        val allStudentCursor = db.query(
            DatabaseHelper.TABLE_NAME,
            null, // Retrieve all columns for student details
            null,
            null,
            null,
            null,
            null,
            null
        )

        val presentStudents = mutableListOf<String>()
        attendanceCursor.use {
            while (it.moveToNext()) {
                val studentName = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_STUDENT_NAME))
                presentStudents.add(studentName)
            }
        }

        val absentStudentList = mutableListOf<StudentData>()
        allStudentCursor.use {
            while (it.moveToNext()) {
                val studentName = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME))
                if (!presentStudents.contains(studentName)) {
                    // Student is absent
                    // Retrieve other student details from the cursor
                    val email = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL))
                    val phone = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PHONE))
                    val address = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ADDRESS))
                    val registrationNumber = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_REG_NUMBER))
                    val fatherName = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_FATHER_NAME))
                    val motherName = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_MOTHER_NAME))
                    val roomNumber = it.getInt(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ROOM_NUMBER))
                    val year = it.getInt(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_YEAR))
                    val parentPhone = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_PARENT_NUM))
                    val dob = it.getString(it.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DOB))
                    val password="12345"

                    val studentData = StudentData(
                        studentName,
                        email,
                        phone,
                        address,
                        registrationNumber,
                        fatherName,
                        motherName,
                        roomNumber,
                        year,
                        parentPhone,
                        dob,
                        password
                    )
                    absentStudentList.add(studentData)
                }
            }
        }

        return absentStudentList
    }
    companion object {
        private const val notificationId = 1
    }
}
