package com.nin0dev.buschecker

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.color.DynamicColors
import com.google.android.material.textfield.TextInputLayout

class MultiChoiceDialog : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyIfAvailable(this)
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_multi_choice_dialog)
        findViewById<TextView>(R.id.availableRoutesText).text = getString(R.string.availableRoutes) + intent.getStringExtra("stop") + ":" + intent.getStringExtra("routes")
        val continueB = findViewById<Button>(R.id.continueButton22)
        val textField = findViewById<EditText>(R.id.routeNumber_multichoice_field)
        val textFieldLayout = findViewById<TextInputLayout>(R.id.routeNumber_multichoice_layout)
        continueB.setOnClickListener {
            if(intent.getStringExtra("routes")?.contains(textField.text, true) == false)
            {
                textFieldLayout.error = getString(R.string.routeNotExist)
                textField.setText("")
            }
            else if(textField.text.isEmpty())
            {
                textFieldLayout.error = getString(R.string.routeNotExist)
                textField.setText("")
            }
            else
            {
                textFieldLayout.error = ""
                val smsManager: SmsManager = SmsManager.getDefault()
                smsManager.sendTextMessage("52786", null, intent.getStringExtra("stop") +  " " + textField.text, null, null)
                Toast.makeText(this, getString(R.string.wait), Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}