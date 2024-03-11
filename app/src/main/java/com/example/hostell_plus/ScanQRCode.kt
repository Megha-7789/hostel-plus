package com.example.hostell_plus

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class ScanQRCode : AppCompatActivity() {

    lateinit var scanButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qrcode)

        scanButton = findViewById(R.id.scanButton)

        scanButton.setOnClickListener {
            startQRCodeScanner()
        }
    }

    private fun startQRCodeScanner() {
        val integrator = IntentIntegrator(this)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setBeepEnabled(false)
        integrator.setPrompt("Scan QR Code")
        integrator.setCameraId(0) // Use the back camera
        integrator.initiateScan()
    }

    // Override onActivityResult to handle the scan result
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        if (result != null) {
            if (result.contents != null) {
                val attendanceData = result.contents
                // Save attendanceData to the database
                saveAttendanceToDatabase(attendanceData)
            } else {
                // Handle case when scan result is empty
            }
        }
    }

    private fun saveAttendanceToDatabase(attendanceData: String) {
        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_STUDENT_NAME, attendanceData)
            // You might add more columns and values here for other attendance data
        }
        val newRowId = db.insert(DatabaseHelper.TABLE_ATTENDANCE, null, values)
        // You can use newRowId to check if insertion was successful

        // Display a message indicating successful attendance saving
        Toast.makeText(this, "Attendance saved successfully", Toast.LENGTH_SHORT).show()
    }
}
