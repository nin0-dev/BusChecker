package com.nin0dev.buschecker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast


class SMSReceiver : BroadcastReceiver() {

    val pdu_type = "pdus"
    override fun onReceive(context: Context, intent: Intent) {
        Log.e("SMS BusChecker", "received")
    }
}