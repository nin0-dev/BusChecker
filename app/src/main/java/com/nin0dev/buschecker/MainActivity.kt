package com.nin0dev.buschecker

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.view.Menu
import android.view.MenuItem
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
import com.google.android.material.textfield.TextInputLayout
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
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.addBusStop -> {
                startActivity(Intent(this, AddBusStopActivity::class.java))
                finish()
                return true
            }
            else ->
                return super.onOptionsItemSelected(item)
        }
    }
    @SuppressLint("SetTextI18n")
    fun cards()
    {
        //region Variable declaration
        val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
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
    fun buttons() {
        val quickCheckButton = findViewById<Button>(R.id.quickcheck_button)
        quickCheckButton.setOnClickListener {
            val textView = findViewById<EditText>(R.id.quicksearch_field)
            if (textView.text.isNullOrBlank()) {
                findViewById<TextInputLayout>(R.id.quicksearch_textlayout).error = "Required"
                return@setOnClickListener
            }
            if (textView.text.length != 5) {
                findViewById<TextInputLayout>(R.id.quicksearch_textlayout).error = "Invalid"
                return@setOnClickListener
            }
            findViewById<TextInputLayout>(R.id.quicksearch_textlayout).error = ""
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("52786", null, textView.text.toString(), null, null)
            textView.setText("")
            Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        //region Delete buttons
        //region Variable declaration
        val delete1 = findViewById<Button>(R.id.delete_1)
        val delete2 = findViewById<Button>(R.id.delete_2)
        val delete3 = findViewById<Button>(R.id.delete_3)
        val delete4 = findViewById<Button>(R.id.delete_4)
        val delete5 = findViewById<Button>(R.id.delete_5)
        val delete6 = findViewById<Button>(R.id.delete_6)
        val delete7 = findViewById<Button>(R.id.delete_7)
        val delete8 = findViewById<Button>(R.id.delete_8)
        val delete9 = findViewById<Button>(R.id.delete_9)
        val delete10 = findViewById<Button>(R.id.delete_10)
        //endregion
        delete1.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Question")
                .setMessage("Are you sure you want to delete this stop?")
                .setPositiveButton("Yes") { dialog, which ->
                    val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
                    val e = sp.edit()
                    e.putString("title1", "null")
                    e.putString("text1", "null")
                    e.putString("stopCode1", "null")
                    e.putString("routeNumber1", "null")
                    e.apply()
                    recreate()
                }
                .setNegativeButton("No") { dialog, which ->

                }
                .show()
        }
        delete2.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Question")
                .setMessage("Are you sure you want to delete this stop?")
                .setPositiveButton("Yes") { dialog, which ->
                    val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
                    val e = sp.edit()
                    e.putString("title2", "null")
                    e.putString("text2", "null")
                    e.putString("stopCode2", "null")
                    e.putString("routeNumber2", "null")
                    e.apply()
                    recreate()
                }
                .setNegativeButton("No") { dialog, which ->

                }
                .show()
        }
        delete3.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Question")
                .setMessage("Are you sure you want to delete this stop?")
                .setPositiveButton("Yes") { dialog, which ->
                    val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
                    val e = sp.edit()
                    e.putString("title3", "null")
                    e.putString("text3", "null")
                    e.putString("stopCode3", "null")
                    e.putString("routeNumber3", "null")
                    e.apply()
                    recreate()
                }
                .setNegativeButton("No") { dialog, which ->

                }
                .show()
        }
        delete4.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Question")
                .setMessage("Are you sure you want to delete this stop?")
                .setPositiveButton("Yes") { dialog, which ->
                    val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
                    val e = sp.edit()
                    e.putString("title4", "null")
                    e.putString("text4", "null")
                    e.putString("stopCode4", "null")
                    e.putString("routeNumber4", "null")
                    e.apply()
                    recreate()
                }
                .setNegativeButton("No") { dialog, which ->

                }
                .show()
        }
        delete5.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Question")
                .setMessage("Are you sure you want to delete this stop?")
                .setPositiveButton("Yes") { dialog, which ->
                    val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
                    val e = sp.edit()
                    e.putString("title5", "null")
                    e.putString("text5", "null")
                    e.putString("stopCode5", "null")
                    e.putString("routeNumber5", "null")
                    e.apply()
                    recreate()
                }
                .setNegativeButton("No") { dialog, which ->

                }
                .show()
        }
        delete6.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Question")
                .setMessage("Are you sure you want to delete this stop?")
                .setPositiveButton("Yes") { dialog, which ->
                    val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
                    val e = sp.edit()
                    e.putString("title6", "null")
                    e.putString("text6", "null")
                    e.putString("stopCode6", "null")
                    e.putString("routeNumber6", "null")
                    e.apply()
                    recreate()
                }
                .setNegativeButton("No") { dialog, which ->

                }
                .show()
        }
        delete7.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Question")
                .setMessage("Are you sure you want to delete this stop?")
                .setPositiveButton("Yes") { dialog, which ->
                    val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
                    val e = sp.edit()
                    e.putString("title7", "null")
                    e.putString("text7", "null")
                    e.putString("stopCode7", "null")
                    e.putString("routeNumber7", "null")
                    e.apply()
                    recreate()
                }
                .setNegativeButton("No") { dialog, which ->

                }
                .show()
        }
        delete8.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Question")
                .setMessage("Are you sure you want to delete this stop?")
                .setPositiveButton("Yes") { dialog, which ->
                    val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
                    val e = sp.edit()
                    e.putString("title8", "null")
                    e.putString("text8", "null")
                    e.putString("stopCode8", "null")
                    e.putString("routeNumber8", "null")
                    e.apply()
                    recreate()
                }
                .setNegativeButton("No") { dialog, which ->

                }
                .show()
        }
        delete9.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Question")
                .setMessage("Are you sure you want to delete this stop?")
                .setPositiveButton("Yes") { dialog, which ->
                    val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
                    val e = sp.edit()
                    e.putString("title9", "null")
                    e.putString("text9", "null")
                    e.putString("stopCode9", "null")
                    e.putString("routeNumber9", "null")
                    e.apply()
                    recreate()
                }
                .setNegativeButton("No") { dialog, which ->

                }
                .show()
        }
        delete10.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Question")
                .setMessage("Are you sure you want to delete this stop?")
                .setPositiveButton("Yes") { dialog, which ->
                    val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
                    val e = sp.edit()
                    e.putString("title10", "null")
                    e.putString("text10", "null")
                    e.putString("stopCode10", "null")
                    e.putString("routeNumber10", "null")
                    e.apply()
                    recreate()
                }
                .setNegativeButton("No") { dialog, which ->

                }
                .show()
        }
        //endregion
        //region Check buttons
        //region Variable declaration
        val check1 = findViewById<Button>(R.id.check1_button)
        val check2 = findViewById<Button>(R.id.check2_button)
        val check3 = findViewById<Button>(R.id.check3_button)
        val check4 = findViewById<Button>(R.id.check4_button)
        val check5 = findViewById<Button>(R.id.check5_button)
        val check6 = findViewById<Button>(R.id.check6_button)
        val check7 = findViewById<Button>(R.id.check7_button)
        val check8 = findViewById<Button>(R.id.check8_button)
        val check9 = findViewById<Button>(R.id.check9_button)
        val check10 = findViewById<Button>(R.id.check10_button)
        val sp = getSharedPreferences("busStops", Context.MODE_PRIVATE)
        //endregion
        check1.setOnClickListener {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("52786", null, sp.getString("stopCode1", "null") + " " + sp.getString("routeNumber1", "null"), null, null)
            Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
        }
        check2.setOnClickListener {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("52786", null, sp.getString("stopCode2", "null") + " " + sp.getString("routeNumber2", "null"), null, null)
            Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
        }
        check3.setOnClickListener {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("52786", null, sp.getString("stopCode3", "null") + " " + sp.getString("routeNumber3", "null"), null, null)
            Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
        }
        check4.setOnClickListener {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("52786", null, sp.getString("stopCode4", "null") + " " + sp.getString("routeNumber4", "null"), null, null)
            Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
        }
        check5.setOnClickListener {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("52786", null, sp.getString("stopCode5", "null") + " " + sp.getString("routeNumber5", "null"), null, null)
            Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
        }
        check6.setOnClickListener {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("52786", null, sp.getString("stopCode6", "null") + " " + sp.getString("routeNumber6", "null"), null, null)
            Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
        }
        check7.setOnClickListener {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("52786", null, sp.getString("stopCode7", "null") + " " + sp.getString("routeNumber7", "null"), null, null)
            Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
        }
        check8.setOnClickListener {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("52786", null, sp.getString("stopCode8", "null") + " " + sp.getString("routeNumber8", "null"), null, null)
            Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
        }
        check9.setOnClickListener {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("52786", null, sp.getString("stopCode9", "null") + " " + sp.getString("routeNumber9", "null"), null, null)
            Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
        }
        check10.setOnClickListener {
            val smsManager: SmsManager = SmsManager.getDefault()
            smsManager.sendTextMessage("52786", null, sp.getString("stopCode10", "null") + " " + sp.getString("routeNumber10", "null"), null, null)
            Toast.makeText(this, "Please wait...", Toast.LENGTH_SHORT).show()
        }
        //endregion
        }
}