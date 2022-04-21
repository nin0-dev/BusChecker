package com.nin0dev.buschecker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Build
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat


class SMSReceiver : BroadcastReceiver() {

    val pdu_type = "pdus"
    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras
        val msgs: Array<SmsMessage?>
        var strMessage = ""
        val format = bundle!!.getString("format")
        val pdus = bundle!![pdu_type] as Array<Any>?
        if (pdus != null) {
            val isVersionM = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            msgs = arrayOfNulls(pdus.size)
            for (i in msgs.indices) {
                msgs[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray, format)
                val address = msgs[i]?.originatingAddress
                val text = msgs[i]?.messageBody
                if(address != "52786")
                {
                    return
                }

            }
        }
    }
}