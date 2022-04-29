package com.nin0dev.buschecker

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Build
import android.telephony.SmsMessage
import android.util.Log
import android.view.WindowManager
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.internal.ContextUtils.getActivity


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
                i.putExtra("title", "Invalid stop code")
                i.putExtra("text", "Make sure that you entered the right stop code, and try again.")
                i.setFlags(FLAG_ACTIVITY_NEW_TASK + FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                ActivityCompat.startActivity(context, i, ActivityOptionsCompat.makeBasic().toBundle())
            }
            if(regexInvalid2.containsMatchIn(text))
            {
                val i = Intent(context, ShowDIalogActivity::class.java)
                i.putExtra("title", "Invalid stop code")
                i.putExtra("text", "Make sure that you entered the right stop code, and try again.")
                i.setFlags(FLAG_ACTIVITY_NEW_TASK + FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                ActivityCompat.startActivity(context, i, ActivityOptionsCompat.makeBasic().toBundle())
            }
            if(regexInvalidRoute.containsMatchIn(text))
            {
                val i = Intent(context, ShowDIalogActivity::class.java)
                i.putExtra("title", "Invalid route number")
                i.putExtra("text", "Make sure that you entered the right route number, and try again.")
                i.setFlags(FLAG_ACTIVITY_NEW_TASK + FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                ActivityCompat.startActivity(context, i, ActivityOptionsCompat.makeBasic().toBundle())
            }
            if(regexApproximateMatch.containsMatchIn(text))
            {
                var stopNumber = text.substringBefore(".")
                stopNumber = stopNumber.substring(stopNumber.length - 5)
                var routeNumbers = text.substringAfter(":")
                routeNumbers = routeNumbers.substringBefore(".")
                routeNumbers.removeRange(0, 1)
                Log.d("Bus stop", stopNumber)
                Log.d("Route numbers", routeNumbers)
                val i = Intent(context, MultiChoiceDialog::class.java)
                i.putExtra("stop", stopNumber)
                i.putExtra("routes", routeNumbers)
                i.setFlags(FLAG_ACTIVITY_NEW_TASK + FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
                ActivityCompat.startActivity(context, i, ActivityOptionsCompat.makeBasic().toBundle())
            }
        }

    }
}