package com.example.hostell_plus

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.wallet.PaymentData
import com.google.android.gms.wallet.PaymentDataRequest
import com.google.android.gms.wallet.PaymentsClient

class Payment : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var paymentsClient: PaymentsClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        databaseHelper = DatabaseHelper(this)
    }

    fun initiatePayment(view: View) {
        // Implement Google Pay integration here
        // This might involve calling APIs, processing payment, etc.

        // Once payment is successful, save payment details to the database
        val paymentRequest = createPaymentRequest()
        paymentsClient.loadPaymentData(paymentRequest)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Payment successful, process the payment data
                    val paymentData = task.result
                    processPaymentResponse(paymentData)
                } else {
                    // Payment failed or user canceled
                    val exception = task.exception
                    // Handle error
                }
            }
    }
    private fun createPaymentRequest(): PaymentDataRequest {
        val paymentDataRequestJson = """
        {
            "apiVersion": 2,
            "apiVersionMinor": 0,
            "allowedPaymentMethods": [
                {
                    "type": "CARD",
                    "parameters": {
                        "allowedAuthMethods": ["PAN_ONLY", "CRYPTOGRAM_3DS"],
                        "allowedCardNetworks": ["VISA", "MASTERCARD"],
                        "billingAddressRequired": true,
                        "billingAddressParameters": {
                            "format": "FULL",
                            "phoneNumberRequired": true
                        }
                    },
                    "tokenizationSpecification": {
                        "type": "PAYMENT_GATEWAY",
                        "parameters": {
                            "gateway": "Your_Payment_Gateway_Name",
                            "gatewayMerchantId": "Your_Gateway_Merchant_Id"
                        }
                    }
                }
            ],
            "transactionInfo": {
                "totalPrice": "100.00",
                "totalPriceStatus": "FINAL",
                "currencyCode": "USD"
            },
            "merchantInfo": {
                "merchantName": "Your Merchant Name",
                "merchantId": "Your Merchant ID"
            }
        }
    """.trimIndent()

        return PaymentDataRequest.fromJson(paymentDataRequestJson)
    }


    private fun processPaymentResponse(paymentData: PaymentData) {
        // Process the payment response data
        // Extract payment details and save to the database
        val studentName = "John Doe" // Replace with actual student name
        val amountPaid = 100.0 // Replace with actual paid amount
        val paymentDate = getCurrentDateTime()

        savePaymentDetails(studentName, amountPaid, paymentDate)
    }

    private fun getCurrentDateTime(): String {
        val currentDateTime = java.util.Calendar.getInstance().time
        return currentDateTime.toString()
    }

    private fun savePaymentDetails(studentName: String, amountPaid: Double, paymentDate: String) {
        val result = databaseHelper.insertPayment(studentName, amountPaid, paymentDate)
        if (result != -1L) {
            // Payment details saved successfully
        } else {
            // Error while saving payment details
        }
    }
}
