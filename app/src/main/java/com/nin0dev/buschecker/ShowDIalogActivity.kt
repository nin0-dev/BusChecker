package com.nin0dev.buschecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.color.DynamicColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ShowDIalogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyIfAvailable(this)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_dialog)
        findViewById<TextView>(R.id.dialogTitle).text = intent.getStringExtra("title")
        findViewById<TextView>(R.id.dialogText).text = intent.getStringExtra("text")
        findViewById<Button>(R.id.okButton2).setOnClickListener {
            finish()
        }
    }
}