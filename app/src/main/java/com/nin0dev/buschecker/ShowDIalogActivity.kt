package com.nin0dev.buschecker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.color.DynamicColors
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ShowDIalogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        DynamicColors.applyIfAvailable(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_dialog)
        MaterialAlertDialogBuilder(this)
            .setTitle(intent.getStringExtra("title"))
            .setMessage(intent.getStringExtra("text"))
            .setPositiveButton("OK") { dialog, which ->
                finish()
            }
            .setOnCancelListener {
                finish()
            }
            .show()

    }
}