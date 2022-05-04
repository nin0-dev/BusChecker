package com.nin0dev.buschecker

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.color.DynamicColors
import com.google.android.material.textfield.TextInputLayout

class EditBusStopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyIfAvailable(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_bus_stop)

        supportActionBar?.setTitle(getString(R.string.editStop))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)

        buttons()
        val name = findViewById<EditText>(R.id.name_fieldE)
        val stopCode = findViewById<EditText>(R.id.stopcode_fieldE)
        val lineNumber = findViewById<EditText>(R.id.lineNumber_fieldE)
        val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
        name.setText(sp.getString("title" + intent.getIntExtra("slot", 0).toString(), "null"))
        stopCode.setText(sp.getString("stopCode" + intent.getIntExtra("slot", 0).toString(), "null"))
        lineNumber.setText(sp.getString("routeNumber" + intent.getIntExtra("slot", 0).toString(), "hello"))
    }
    override fun onCreateOptionsMenu(menu: Menu) : Boolean {
        menuInflater.inflate(R.menu.appbar_addbus, menu)
        return true
    }
    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        return true
    }
    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        super.onBackPressed()
    }

    fun saveOnClick(v: View)
    {
        //region Variable declaration
        val stopCodeL = findViewById<TextInputLayout>(R.id.stopcode_textlayoutE)
        val routeNumberL = findViewById<TextInputLayout>(R.id.lineNumber_textlayoutE)
        val nameL = findViewById<TextInputLayout>(R.id.name_textlayoutE)
        val stopCode = findViewById<EditText>(R.id.stopcode_fieldE)
        val routeNumber = findViewById<EditText>(R.id.lineNumber_fieldE)
        val name = findViewById<EditText>(R.id.name_fieldE)
        val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
        val e = sp.edit()
        var validFields = 3
        //endregion
        //region Check fields
        if(stopCode.text.length != 5)
        {
            // Not valid stop code
            stopCodeL.error = getString(R.string.invalid)
            stopCode.setText("")
            validFields--
        }
        if(stopCode.text.isNullOrEmpty())
        {
            // Empty stop code
            stopCodeL.error = getString(R.string.required)
            validFields--
        }
        if(routeNumber.text.isNullOrEmpty())
        {
            // Empty route number
            routeNumberL.error = getString(R.string.required)
            validFields--
        }
        if(routeNumber.text.isNullOrEmpty())
        {
            // Empty route number
            routeNumberL.error = getString(R.string.required)
            validFields--
        }
        if(name.text.isNullOrEmpty())
        {
            // Empty name
            nameL.error = getString(R.string.required)
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
        e.putString("title" + intent.getIntExtra("slot", 0).toString(), name.text.toString())
        e.putString("stopCode" + intent.getIntExtra("slot", 0).toString(), stopCode.text.toString())
        e.putString("routeNumber" + intent.getIntExtra("slot", 0).toString(), routeNumber.text.toString())
        e.apply()
        //endregion
        finish()
        startActivity(Intent(this, MainActivity::class.java))
    }
    fun buttons()
    {
        val verify = findViewById<Button>(R.id.verifyButtonE)
        verify.setOnClickListener {
            //region Variable declaration
            val stopCodeL = findViewById<TextInputLayout>(R.id.stopcode_textlayoutE)
            val routeNumberL = findViewById<TextInputLayout>(R.id.lineNumber_textlayoutE)
            val stopCode = findViewById<EditText>(R.id.stopcode_fieldE)
            val routeNumber = findViewById<EditText>(R.id.lineNumber_fieldE)
            var validFields = 2
            //endregion
            //region Check fields
            if(stopCode.text.length != 5)
            {
                // Not valid stop code
                stopCodeL.error = getString(R.string.invalid)
                stopCode.setText("")
                validFields--
            }
            if(stopCode.text.isNullOrEmpty())
            {
                // Empty stop code
                stopCodeL.error = getString(R.string.required)
                validFields--
            }
            if(routeNumber.text.isNullOrEmpty())
            {
                // Empty route number
                routeNumberL.error = getString(R.string.required)
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
            Toast.makeText(this, getString(R.string.wait), Toast.LENGTH_SHORT).show()
            //endregion
        }
    }
}