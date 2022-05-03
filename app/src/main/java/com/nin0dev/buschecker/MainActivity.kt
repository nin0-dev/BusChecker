package com.nin0dev.buschecker

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.view.Menu
import android.view.View.GONE
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.google.android.material.color.DynamicColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationBarView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyIfAvailable(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
        val e = sp.edit()
        if(ContextCompat.checkSelfPermission(this, "android.permission.RECEIVE_SMS") == PackageManager.PERMISSION_DENIED)
        {
            val i = Intent(this, SetupActivity::class.java)
            startActivity(i)
        }
        buttons()
        cards()
    }
    override fun onCreateOptionsMenu(menu: Menu) : Boolean {
        menuInflater.inflate(R.menu.appbar_main, menu)
        return true
    }
    fun cards()
    {
        //region Variable declaration
        val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
        sp.edit().putString("title1", "hello")
        sp.edit().commit()
        var availableElements = 10
        //region 10 title elements
        val title1e = findViewById<TextView>(R.id.title_1)
        val title2e = findViewById<TextView>(R.id.title_2)
        val title3e = findViewById<TextView>(R.id.title_3)
        val title4e = findViewById<TextView>(R.id.title_4)
        val title5e = findViewById<TextView>(R.id.title_5)
        val title6e = findViewById<TextView>(R.id.title_6)
        val title7e = findViewById<TextView>(R.id.title_7)
        val title8e = findViewById<TextView>(R.id.title_8)
        val title9e = findViewById<TextView>(R.id.title_9)
        val title10e = findViewById<TextView>(R.id.title_10)
        //endregion
        //region 10 titles (from SharedPreferences)
        val title1 = sp.getString("title1", "null")
        val title2 = sp.getString("title2", "null")
        val title3 = sp.getString("title3", "null")
        val title4 = sp.getString("title4", "null")
        val title5 = sp.getString("title5", "null")
        val title6 = sp.getString("title6", "null")
        val title7 = sp.getString("title7", "null")
        val title8 = sp.getString("title8", "null")
        val title9 = sp.getString("title9", "null")
        val title10 = sp.getString("title10", "null")
        //endregion
        //region 10 text elements
        val text1e = findViewById<TextView>(R.id.text_1)
        val text2e = findViewById<TextView>(R.id.text_2)
        val text3e = findViewById<TextView>(R.id.text_3)
        val text4e = findViewById<TextView>(R.id.text_4)
        val text5e = findViewById<TextView>(R.id.text_5)
        val text6e = findViewById<TextView>(R.id.text_6)
        val text7e = findViewById<TextView>(R.id.text_7)
        val text8e = findViewById<TextView>(R.id.text_8)
        val text9e = findViewById<TextView>(R.id.text_9)
        val text10e = findViewById<TextView>(R.id.text_10)
        //endregion
        //region 10 stop codes (from SharedPreferences)
        val stopCode1 = sp.getString("stopCode1", "null")
        val stopCode2 = sp.getString("stopCode2", "null")
        val stopCode3 = sp.getString("stopCode3", "null")
        val stopCode4 = sp.getString("stopCode4", "null")
        val stopCode5 = sp.getString("stopCode5", "null")
        val stopCode6 = sp.getString("stopCode6", "null")
        val stopCode7 = sp.getString("stopCode7", "null")
        val stopCode8 = sp.getString("stopCode8", "null")
        val stopCode9 = sp.getString("stopCode9", "null")
        val stopCode10 = sp.getString("stopCode10", "null")
        //endregion
        //region 10 route numbers (from SharedPreferences)
        val routeNumber1 = sp.getString("routeNumber1", "null")
        val routeNumber2 = sp.getString("routeNumber2", "null")
        val routeNumber3 = sp.getString("routeNumber3", "null")
        val routeNumber4 = sp.getString("routeNumber4", "null")
        val routeNumber5 = sp.getString("routeNumber5", "null")
        val routeNumber6 = sp.getString("routeNumber6", "null")
        val routeNumber7 = sp.getString("routeNumber7", "null")
        val routeNumber8 = sp.getString("routeNumber8", "null")
        val routeNumber9 = sp.getString("routeNumber9", "null")
        val routeNumber10 = sp.getString("routeNumber10", "null")
        //endregion
        //region 10 card elements
        val card1e = findViewById<com.google.android.material.card.MaterialCardView>(R.id.saved1)
        val card2e = findViewById<com.google.android.material.card.MaterialCardView>(R.id.saved2)
        val card3e = findViewById<com.google.android.material.card.MaterialCardView>(R.id.saved3)
        val card4e = findViewById<com.google.android.material.card.MaterialCardView>(R.id.saved4)
        val card5e = findViewById<com.google.android.material.card.MaterialCardView>(R.id.saved5)
        val card6e = findViewById<com.google.android.material.card.MaterialCardView>(R.id.saved6)
        val card7e = findViewById<com.google.android.material.card.MaterialCardView>(R.id.saved7)
        val card8e = findViewById<com.google.android.material.card.MaterialCardView>(R.id.saved8)
        val card9e = findViewById<com.google.android.material.card.MaterialCardView>(R.id.saved9)
        val card10e = findViewById<com.google.android.material.card.MaterialCardView>(R.id.saved10)
        //endregion
        //endregion

        //region Hide empty cards
        if(title1 == "null")
        {
            card1e.visibility = GONE
            availableElements--
        }
        if(title2 == "null")
        {
            card2e.visibility = GONE
            availableElements--
        }
        if(title3 == "null")
        {
            card3e.visibility = GONE
            availableElements--
        }
        if(title4 == "null")
        {
            card4e.visibility = GONE
            availableElements--
        }
        if(title5 == "null")
        {
            card5e.visibility = GONE
            availableElements--
        }
        if(title6 == "null")
        {
            card6e.visibility = GONE
            availableElements--
        }
        if(title7 == "null")
        {
            card7e.visibility = GONE
            availableElements--
        }
        if(title8 == "null")
        {
            card8e.visibility = GONE
            availableElements--
        }
        if(title9 == "null")
        {
            card9e.visibility = GONE
            availableElements--
        }
        if(title10 == "null")
        {
            card10e.visibility = GONE
            availableElements--
        }
        //endregion
        //region Set titles
        title1e.text = title1
        title2e.text = title2
        title3e.text = title3
        title4e.text = title4
        title5e.text = title5
        title6e.text = title6
        title7e.text = title7
        title8e.text = title8
        title9e.text = title9
        title10e.text = title10
        //endregion
        //region Set texts
        text1e.text = "Stop $stopCode1 / Route $routeNumber1"
        text2e.text = "Stop $stopCode2 / Route $routeNumber2"
        text3e.text = "Stop $stopCode3 / Route $routeNumber3"
        text4e.text = "Stop $stopCode4 / Route $routeNumber4"
        text5e.text = "Stop $stopCode5 / Route $routeNumber5"
        text6e.text = "Stop $stopCode6 / Route $routeNumber6"
        text7e.text = "Stop $stopCode7 / Route $routeNumber7"
        text8e.text = "Stop $stopCode8 / Route $routeNumber8"
        text9e.text = "Stop $stopCode9 / Route $routeNumber9"
        text10e.text = "Stop $stopCode10 / Route $routeNumber10"
        //endregion
        //region Set separation text
        if(availableElements == 0)
        {
            findViewById<TextView>(R.id.separationText).text = "No saved bus stops, click the + to add one"
        }
        //endregion
    }
    fun buttons()
    {
        val quickCheckButton = findViewById<Button>(R.id.quickcheck_button)
        quickCheckButton.setOnClickListener {
            val textView = findViewById<EditText>(R.id.quicksearch_field)
            if(textView.text.isNullOrBlank())
            {
                MaterialAlertDialogBuilder(this)
                    .setTitle("Error")
                    .setMessage("Looks like you haven't put anything in the quick search text field.")
                    .setPositiveButton("OK") { dialog, which ->

                    }
                    .show()
                return@setOnClickListener
            }
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("52786", null, textView.text.toString(), null, null)
            textView.setText("")
            Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
            recreate()
        }
    }

}