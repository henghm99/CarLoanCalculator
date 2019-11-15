package com.example.carloancalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculate_button.setOnClickListener() {
            calCarLoan(it)
        }
    }

    private fun calCarLoan(view: View) {
        val carPrice = editText_car_price.text.toString()
        val downPayment = editText_down_payment.text.toString()
        val loanPeriod = editText_loan_period.text.toString()
        val interestRate = editText_interest_rate.text.toString()

        val carLoan: Double = carPrice.toDouble() - downPayment.toDouble()
        val interest: Double = carLoan * (interestRate.toDouble()/100) * loanPeriod.toInt()
        val monthRepayment: Double = (carLoan + interest) / loanPeriod.toInt() / 12

        val carLoanText = findViewById<TextView>(R.id.textViewCarLoan)
        val interestText = findViewById<TextView>(R.id.textViewInterest)
        val monthRepaymentText = findViewById<TextView>(R.id.textViewMonthRepayment)

        carLoanText.text = "RM %.2f".format(carLoan)
        interestText.text = "RM %.2f".format(interest)
        monthRepaymentText.text = "RM %.2f".format(monthRepayment)

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText_interest_rate, 0)
    }
}
