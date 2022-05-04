package com.nin0dev.buschecker

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.telephony.SmsManager
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.color.DynamicColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text

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
    fun findEmptySlot(): Int {
        val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
        if(sp.getString("title1", "null") == "null")
        {
            return 1
        }
        if(sp.getString("title2", "null") == "null")
        {
            return 2
        }
        if(sp.getString("title3", "null") == "null")
        {
            return 3
        }
        if(sp.getString("title4", "null") == "null")
        {
            return 4
        }
        if(sp.getString("title5", "null") == "null")
        {
            return 5
        }
        if(sp.getString("title6", "null") == "null")
        {
            return 6
        }
        if(sp.getString("title7", "null") == "null")
        {
            return 7
        }
        if(sp.getString("title8", "null") == "null")
        {
            return 8
        }
        if(sp.getString("title9", "null") == "null")
        {
            return 9
        }
        if(sp.getString("title10", "null") == "null")
        {
            return 10
        }
        return 0
    }
    fun saveOnClick(v: View)
    {
        //region Variable declaration
        val stopCodeL = findViewById<TextInputLayout>(R.id.stopcode_textlayout)
        val routeNumberL = findViewById<TextInputLayout>(R.id.lineNumber_textlayout)
        val nameL = findViewById<TextInputLayout>(R.id.name_textlayout)
        val stopCode = findViewById<EditText>(R.id.stopcode_field)
        val routeNumber = findViewById<EditText>(R.id.lineNumber_field)
        val name = findViewById<EditText>(R.id.name_field)
        val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
        val e = sp.edit()
        var validFields = 3
        var writeIn = 0
        //endregion
        //region Check fields
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
        if(routeNumber.text.isNullOrEmpty())
        {
            // Empty route number
            routeNumberL.error = "Required"
            validFields--
        }
        if(name.text.isNullOrEmpty())
        {
            // Empty name
            nameL.error = "Required"
            validFields--
        }
        if(validFields != 3)
        {
            return
        }
        stopCodeL.error = ""
        routeNumberL.error = ""
        nameL.error = ""
        //endregion
        //region Write in memory
        e.putString("title" + findEmptySlot().toString(), name.text.toString())
        e.putString("stopCode" + findEmptySlot().toString(), stopCode.text.toString())
        e.putString("routeNumber" + findEmptySlot().toString(), routeNumber.text.toString())
        e.apply()
        //endregion
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }
    fun buttons()
    {
        val verify = findViewById<Button>(R.id.verifyButton)
        val save = findViewById<Button>(R.id.save_button)
        verify.setOnClickListener {
            //region Variable declaration
            val stopCodeL = findViewById<TextInputLayout>(R.id.stopcode_textlayout)
            val routeNumberL = findViewById<TextInputLayout>(R.id.lineNumber_textlayout)
            val stopCode = findViewById<EditText>(R.id.stopcode_field)
            val routeNumber = findViewById<EditText>(R.id.lineNumber_field)
            var validFields = 2
            //endregion
            //region Check fields
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
            //endregion
            //region Verification process
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("52786", null, stopCode.text.toString() + " " + routeNumber.text.toString(), null, null)
            Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
            //endregion
        }
    }
}