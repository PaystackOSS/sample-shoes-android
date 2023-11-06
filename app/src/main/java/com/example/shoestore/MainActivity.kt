package com.example.shoestore

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.ComponentActivity
import com.example.shoestore.R.id.pay_button
import com.paystack.android_sdk.core.Paystack
import com.paystack.android_sdk.ui.paymentsheet.PaymentSheet
import com.paystack.android_sdk.ui.paymentsheet.PaymentSheetResult


class MainActivity : ComponentActivity() {
    private lateinit var paymentSheet: PaymentSheet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Paystack.builder().setPublicKey("pk_test_{your-public-key-here}").build()
        val mCheckout: Button = findViewById(pay_button)
        paymentSheet = PaymentSheet(this, ::paymentComplete)

        mCheckout.setOnClickListener { payWithPaystack()
        }
    }

    private fun payWithPaystack() {
        // Pass access_code from transaction initialize call
        paymentSheet.launch("{access-code-here}")
    }

    private fun paymentComplete(paymentSheetResult: PaymentSheetResult ) {
        val transaction: String = paymentSheetResult.toString()
        Log.d("Transaction complete", transaction)
    }
}

