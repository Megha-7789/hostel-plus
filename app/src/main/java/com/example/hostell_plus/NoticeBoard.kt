package com.example.hostell_plus

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NoticeBoard : AppCompatActivity() {

    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var postNoticeButton: Button
    private lateinit var noticeEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice_board)

        databaseHelper = DatabaseHelper(this)
        postNoticeButton = findViewById(R.id.postnoticebutton)
        noticeEditText = findViewById(R.id.editTextTextMultiLine3)

        postNoticeButton.setOnClickListener {
            val noticeText = noticeEditText.text.toString().trim()

            if (noticeText.isNotEmpty()) {
                val content = noticeEditText.text.toString()

                val insertedId = databaseHelper.insertNotice(noticeText, content)

                if (insertedId != -1L) {
                    // Successfully inserted notice
                    Toast.makeText(this, "Notice inserted successfully", Toast.LENGTH_SHORT).show()
                    // Clear the EditText after inserting
                    noticeEditText.text.clear()
                } else {
                    Toast.makeText(this, "Failed to insert notice", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
