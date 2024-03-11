package com.example.hostell_plus

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.ResultPoint
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView

class rooms : AppCompatActivity() {

    lateinit var qrCodeImageView: ImageView
    private lateinit var databaseHelper: DatabaseHelper
    lateinit var studentName: String
    lateinit var generatebutton :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)

        qrCodeImageView = findViewById(R.id.qrCodeImageView)
        databaseHelper = DatabaseHelper(this)
        generatebutton = findViewById(R.id.generateButton)

        // Retrieve student name from intent extras
        studentName = intent.getStringExtra("studentName") ?: "Unknown Student"

        val textToEncode = "Your attendance data here" // Replace with the actual attendance data
        generateQRCode(textToEncode)

        // Set up QR code scanning functionality
        val scannerContainer = findViewById<FrameLayout>(R.id.scannerContainer)
        val scannerView = DecoratedBarcodeView(this)
        scannerContainer.addView(scannerView)

        scannerView.setStatusText("Scanning...") // Optional status text

        scannerView.decodeContinuous(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult) {
                val scannedData = result.text
                insertScannedData(scannedData, studentName)
            }

            override fun possibleResultPoints(resultPoints: List<ResultPoint>) {}
        })

        // Insert attendance data into the database
        insertAttendanceData(studentName)

        generatebutton.setOnClickListener {
            Toast.makeText(this, "Attendance list is here", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, AttendanceListActivity::class.java)
            startActivity(intent)
        }
    }
    private fun insertAttendanceData(studentName: String) {
        val db = databaseHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_STUDENT_NAME, studentName)
            // You might add more columns and values here for other attendance data
            // put(DatabaseHelper.COLUMN_TIMESTAMP, timestamp)
        }
        val newRowId = db.insert(DatabaseHelper.TABLE_ATTENDANCE, null, values)
        // You can use newRowId to check if insertion was successful
    }

    private fun insertScannedData(scannedData: String, studentName: String) {
        val db = databaseHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.COLUMN_SCANNED_DATA, scannedData)
            put(DatabaseHelper.COLUMN_STUDENT_NAME, studentName)
            // You might add more columns and values here for other scanned data
            // put(DatabaseHelper.COLUMN_TIMESTAMP, timestamp)
        }
        val newRowId = db.insert(DatabaseHelper.TABLE_ATTENDANCE, null, values)
        // You can use newRowId to check if insertion was successful
    }

    private fun generateQRCode(text: String) {
        try {
            val multiFormatWriter = MultiFormatWriter()
            val bitMatrix: BitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)
            qrCodeImageView.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }
}
