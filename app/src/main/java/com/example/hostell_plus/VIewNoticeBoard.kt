package com.example.hostell_plus

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ViewNoticeBoard : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var titleTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_notice_board)

        dbHelper = DatabaseHelper(this)
        titleTextView = findViewById(R.id.titleTextView)

        val noticeList = retrieveNotice()

        // Display notices in the TextView
        val noticesText = noticeList.joinToString("\n\n")
        titleTextView.text = noticesText
    }

    private fun retrieveNotice(): List<Notice> {
        return dbHelper.getAllNotices()
    }
}
