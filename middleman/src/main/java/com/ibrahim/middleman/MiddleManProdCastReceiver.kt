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
                val input = BufferedReader(InputStreamReader(socket.getInputStream()))

                output.write(userJson + "\n")
                output.flush()

                val response = input.readLine()
                if (response.isNotEmpty()) {
                    Log.i(TAG, "response from receiver to middleman: $response")

                    sendConfirmationToEmitter(
                        applicationContext,
                        response
                    )
                }

                socket.close()

            } catch (e: Exception ) {
                Log.d(TAG, "sendUserJsonToReceiverApp: Exception ${e.message}")
                e.printStackTrace()
            }
        }

    }

    private fun sendConfirmationToEmitter(
        applicationContext: Context,
        userJson: String
    ) {

        val intent = Intent(EMITTER_RECEIVER_NAME)
        intent.action = EMITTER_PACKAGE_NAME
        intent.putExtra(EXTRA_RESPONSE,userJson)
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        intent.component =
            ComponentName(
                EMITTER_PACKAGE_NAME, EMITTER_RECEIVER_package_NAME
            )
        applicationContext.sendBroadcast(intent)

    }

}