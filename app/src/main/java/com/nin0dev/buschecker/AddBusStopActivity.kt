package com.nin0dev.buschecker

import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.color.DynamicColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout

class AddBusStopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyIfAvailable(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_add_bus_stop)
        supportActionBar?.setTitle("Add bus stop")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)

        buttons()
    }
    override fun onCreateOptionsMenu(menu: Menu) : Boolean {
        menuInflater.inflate(R.menu.appbar_addbus, menu)
        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
        return true
    }
    override fun onBackPressed() {
        finish()
        startActivity(Intent(this, MainActivity::class.java))
        super.onBackPressed()
    }
    fun buttons()
    {
        val verify = findViewById<Button>(R.id.verifyButton)
        verify.setOnClickListener {
            val stopCodeL = findViewById<TextInputLayout>(R.id.stopcode_textlayout)
            val routeNumberL = findViewById<TextInputLayout>(R.id.lineNumber_textlayout)
            val stopCode = findViewById<EditText>(R.id.stopcode_field)
            val routeNumber = findViewById<EditText>(R.id.lineNumber_field)
            var validFields = 2
            if(stopCode.text.length != 5)
            {
                // Not valid stop code
                stopCodeL.error = "Invalid"
                stopCode.setText("")
                validFields--
            }
            if(stopCode.text.isNullOrEmpty())
            {
                // Empty stop code
                stopCodeL.error = "Required"
                validFields--
            }
            if(routeNumber.text.isNullOrEmpty())
            {
                // Empty route number
                routeNumberL.error = "Required"
                validFields--
            }
            if(validFields != 2)
            {
                return@setOnClickListener
            }
            stopCodeL.error = ""
            routeNumberL.error = ""
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("52786", null, stopCode.text.toString() + " " + routeNumber.text.toString(), null, null)
            Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
        }
    }
}