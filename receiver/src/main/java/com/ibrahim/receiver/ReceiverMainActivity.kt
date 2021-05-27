package com.ibrahim.receiver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat

class ReceiverMainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver_main)
        Log.d("log***", "onCreate: receiver")

        startServerService()


    }


    private fun startServerService() {
        val serverThread = ServerThread()
        serverThread.start()
    }
}