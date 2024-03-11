package com.example.hostell_plus

// In your activity or fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.wallet.PaymentDataRequest
import com.google.android.gms.wallet.PaymentsClient
import com.google.android.gms.wallet.Wallet
import com.google.android.gms.wallet.WalletConstants

class googlepay : AppCompatActivity() {

    private lateinit var paymentsClient: PaymentsClient
    lateinit var imgpaybutton :ImageButton

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_googlepay)
        imgpaybutton = findViewById(R.id.imgpayButton)

        imgpaybutton.setOnClickListener(View.OnClickListener {
            Toast.makeText(this,"Opening googlepay",Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Payment::class.java)
            startActivity(intent)
        })

        // Create a PaymentsClient instance
        paymentsClient = Wallet.getPaymentsClient(
            this,
            Wallet.WalletOptions.Builder()
                .setEnvironment(WalletConstants.ENVIRONMENT_TEST) // Use ENVIRONMENT_PRODUCTION for production
                .build()
        )

        // Prepare a PaymentDataRequest
        val paymentDataRequest = PaymentDataRequest.fromJson("your_payment_data_request_json_string")

        // Show the Google Pay payment sheet
        val paymentDataTask = paymentsClient.loadPaymentData(paymentDataRequest)
        paymentDataTask.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val paymentData = task.result
                // Handle paymentData and process payment
            } else {
                // Handle payment failure
            }
        }
    }
}
