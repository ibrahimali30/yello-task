package com.ibrahim.emitter

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.ibrahim.emitter.base.TAG
import java.io.PrintWriter
import java.net.Socket
import kotlin.concurrent.thread

class EmitterProdCastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val response = intent?.getStringExtra("EXTRA_USER")
        Log.d(TAG, "onReceive: $response")

        showResponseDialog(response)

    }

    private fun showResponseDialog(response: String?) {
        TODO("Not yet implemented")
    }

}