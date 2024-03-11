package com.example.hostell_plus

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.ByteArrayOutputStream

class uploadfeereciept : AppCompatActivity() {

    private lateinit var receiptImageView: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_uploadfeereciept)


        val btnGenerateReceipt: Button = findViewById(R.id.btn_generate_receipt)
        val btnUploadReceipt: Button = findViewById(R.id.btn_upload_receipt)
        val btnPickImage: Button = findViewById(R.id.btn_pick_image) // Correct ID

        btnPickImage.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
        }

        btnGenerateReceipt.setOnClickListener {
            generateReceipt()
        }

        btnUploadReceipt.setOnClickListener {
            uploadReceipt()
        }
    }

    private fun generateReceipt() {
        // Implement your receipt generation logic here
        // For example, you can create a bitmap of a receipt template with text and images
        // You can use libraries like Canvas, PDF generation libraries, etc.

        // For this example, let's use a placeholder receipt image
        receiptImageView.setImageResource(R.drawable.baseline_receipt_24)
        receiptImageView.visibility = View.VISIBLE
    }

    private fun uploadReceipt() {
        val drawable = receiptImageView.drawable
        if (drawable is BitmapDrawable) {
            val bitmap = drawable.bitmap
            val outputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            val byteArray = outputStream.toByteArray()

            val intent = Intent(this, upload_reciept::class.java)
            intent.putExtra("receipt_image", byteArray)
            startActivity(intent)
        } else {
            // Handle error, no image selected
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Companion.GALLERY_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            val selectedImageUri: Uri? = data.data

            // Set the selected image to the ImageView
            receiptImageView.setImageURI(selectedImageUri)
            receiptImageView.visibility = View.VISIBLE
        }
    }


    // Rest of your code...

    companion object {
        private const val GALLERY_REQUEST_CODE = 1001
    }
}
