package com.example.hostell_plus


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class home_screen : AppCompatActivity() {

    lateinit var wardenbutton: Button
    lateinit var matronbutton: Button
    lateinit var studentbutton: Button
    lateinit var righticon: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        righticon = findViewById(R.id.right_icon)
        wardenbutton = findViewById(R.id.wardenButton)
        matronbutton = findViewById(R.id.matronButton)
        studentbutton = findViewById(R.id.studentButton)

        righticon.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "You clicked on right icon", Toast.LENGTH_SHORT).show()
            navigateToNavigationDrawer()
        })

        wardenbutton.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "You clicked on warden", Toast.LENGTH_SHORT).show()
            // Handle warden button click here if needed
            navigateToLoginScreen()
        })

        matronbutton.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "You clicked on matron", Toast.LENGTH_SHORT).show()
            navigateToLoginScreen()
        })

        studentbutton.setOnClickListener(View.OnClickListener {
            Toast.makeText(this, "You clicked on student", Toast.LENGTH_SHORT).show()
            navigateToLoginScreen()
        })
    }

    private fun navigateToLoginScreen() {
        val intent = Intent(this@home_screen, Login_screen::class.java)
        startActivity(intent)
    }

    private fun navigateToNavigationDrawer(){
        val intent = Intent(this@home_screen,navigation_drawer::class.java)
        startActivity(intent)
    }
}
