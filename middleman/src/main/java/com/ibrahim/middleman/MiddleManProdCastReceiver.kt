package com.ibrahim.middleman

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.util.Log
import com.ibrahim.engine.base.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import kotlin.concurrent.thread

class MiddleManProdCastReceiver : BroadcastReceiver() {

    lateinit var applicationContext: Context

    override fun onReceive(context: Context , intent: Intent?) {
        applicationContext = context
        val userJson = intent?.getStringExtra(EXTRA_USER)
        sendUserJsonToReceiverApp(userJson, applicationContext)
    }


}