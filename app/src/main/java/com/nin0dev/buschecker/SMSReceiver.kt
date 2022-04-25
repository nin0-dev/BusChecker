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
import kotlin.streams.asSequence


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
            var text = ""
            for (i in msgs.indices) {
                msgs[i] = SmsMessage.createFromPdu(pdus[i] as ByteArray, format)
                text += msgs[i]?.messageBody
                val address = msgs[i]?.originatingAddress
                val text : CharSequence? = msgs[i]?.messageBody
                if(address != "52786")
                {
                    return
                }
            }
            Log.d("New full message", text)
            val regexTimeInfo = Regex("\\(\\d\\d\\/\\d\\d\\,\\ \\d\\dh\\d\\d\\)")
            val regexInvalid = Regex("Invalid entry")
            val regexInvalid2 = Regex("Invalid stop number")
            val regexInvalidRoute = Regex("doesn\\'t correspond with stop")
            val regexApproximateMatch = Regex("Several bus routes are available for stop")
            if(regexTimeInfo.containsMatchIn(text!!))
            {
                var stopNumber = text.substring(5, 11)
                var busTimes = text.substringAfter(":")
                busTimes = busTimes.removeRange(0,1)
                busTimes = busTimes.substringBefore("\n")
                busTimes = busTimes.replace("*", "")
                val i = Intent(context, ShowDIalogActivity::class.java)
                i.putExtra("title", "Bus times for $stopNumber")
                i.putExtra("text", busTimes)
                i.setFlags(FLAG_ACTIVITY_NEW_TASK + FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                ActivityCompat.startActivity(context, i, ActivityOptionsCompat.makeBasic().toBundle())
            }
            if(regexInvalid.containsMatchIn(text!!))
            {
                val i = Intent(context, ShowDIalogActivity::class.java)
                i.putExtra("title", "Matched invalid input")
                i.putExtra("text", text)
                i.setFlags(FLAG_ACTIVITY_NEW_TASK + FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                ActivityCompat.startActivity(context, i, ActivityOptionsCompat.makeBasic().toBundle())
            }
            if(regexInvalid2.containsMatchIn(text!!))
            {
                val i = Intent(context, ShowDIalogActivity::class.java)
                i.putExtra("title", "Matched non-existing stop")
                i.putExtra("text", text)
                i.setFlags(FLAG_ACTIVITY_NEW_TASK + FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                ActivityCompat.startActivity(context, i, ActivityOptionsCompat.makeBasic().toBundle())
            }
            if(regexInvalidRoute.containsMatchIn(text!!))
            {
                val i = Intent(context, ShowDIalogActivity::class.java)
                i.putExtra("title", "Matched invalid route")
                i.putExtra("text", text)
                i.setFlags(FLAG_ACTIVITY_NEW_TASK + FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                ActivityCompat.startActivity(context, i, ActivityOptionsCompat.makeBasic().toBundle())
            }
            if(regexApproximateMatch.containsMatchIn(text!!))
            {
                val intent = Intent(context, ShowDIalogActivity::class.java)
                intent.putExtra("title", "Matched several routes")
                intent.putExtra("text", text)
                intent.setFlags(FLAG_ACTIVITY_NEW_TASK + FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                ActivityCompat.startActivity(context, intent, ActivityOptionsCompat.makeBasic().toBundle())
            }
        }

    }
}