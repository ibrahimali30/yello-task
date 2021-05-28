package com.ibrahim.middleman

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ibrahim.engine.base.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}


 fun sendUserJsonToReceiverApp(userJson: String?, applicationContext: Context) {

    thread {
        try {

            val socket = Socket("localhost", 8080)
            val output = PrintWriter(socket.getOutputStream())
            val input = BufferedReader(InputStreamReader(socket.getInputStream()))

            output.write(userJson + "\n")
            output.flush()

            val response = input.readLine()
            if (response.isNotEmpty()) {
                sendConfirmationToEmitter(applicationContext, response)
            }

            socket.close()

        } catch (e: Exception ) {
            e.printStackTrace()
        }
    }

}

 fun sendConfirmationToEmitter(
    applicationContext: Context,
    userJson: String
) {
    val intent = Intent(EMITTER_RECEIVER_NAME)
    intent.action = EMITTER_PACKAGE_NAME
    intent.putExtra(EXTRA_RESPONSE,userJson)
    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
    intent.component =
        ComponentName(EMITTER_PACKAGE_NAME, EMITTER_RECEIVER_package_NAME)
    applicationContext.sendBroadcast(intent)
}