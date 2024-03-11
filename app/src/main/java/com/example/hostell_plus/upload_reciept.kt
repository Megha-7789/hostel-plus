package com.example.hostell_plus

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class upload_reciept : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_reciept)


        val receiptImageView: ImageView = findViewById(R.id.uploaded_receipt_image)

        val receiptImageByteArray = intent?.getByteArrayExtra("receipt_image")
        val bill: Bill? = intent?.getParcelableExtra("bill")

        receiptImageByteArray?.let {
            val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            receiptImageView.setImageBitmap(bitmap)
        }

        bill?.let {
            // Display the bill information to the user (e.g., using TextViews)
            val billInfoTextView: TextView = findViewById(R.id.bill_info_text_view)
            val dbHelper = DatabaseHelper(this)

            val studentName = "John Doe"
            val amount = 500.0
            val paymentDate = "2023-08-15"

            val insertedId = dbHelper.insertPayment(studentName, amount, paymentDate)
            if (insertedId != -1L) {
                Toast.makeText(this,"Successfull",Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()

                // Failed to insert
            }
        }
    }


}
