package com.ibrahim.middleman

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import java.io.PrintWriter
import java.net.Socket
import kotlin.concurrent.thread

class MiddleManProdCastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val userJson = intent?.getStringExtra("EXTRA_USER")
        Log.d(TAG, "onReceive: $userJson")
        sendUserJsonToReceiverApp(userJson)
    }

    private fun sendUserJsonToReceiverApp(userJson: String?) {
        Log.i(TAG, "doWork")

        thread {
            try {

                val socket = Socket("localhost", 8080)
                val output = PrintWriter(socket.getOutputStream())

                output.write(userJson + "\n")
                output.flush()

                socket.close()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

}