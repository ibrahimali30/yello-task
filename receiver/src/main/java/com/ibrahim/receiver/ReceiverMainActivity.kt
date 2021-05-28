package com.ibrahim.receiver


import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ibrahim.receiver.presentation.showNewUserDialog
import kotlin.concurrent.thread


class ReceiverMainActivity : AppCompatActivity() {

    lateinit var serverThread: ServerThread


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver_main)
        Log.d("log***", "onCreate: receiver")

        startServerService()

//        delay (1000) { response("test response") }
//        delay (2000) { response("test response") }
//        delay (3000) { response("test response") }


    }

    private fun delay(delay: Long = 1000, function: () -> Unit = {}) {
        Handler().postDelayed({
            function()
        },delay)
    }

    private fun response(s: String) {
        thread {
//            serverThread.reply(s)
        }

    }


    private fun startServerService() {
        serverThread  = ServerThread()
        serverThread.start()

        serverThread.liveData.observe(this, Observer {

            showNewUserDialog(
                it.name,
                "do you want to add ${it.username}",
                {
                    serverThread.replyResponseToMiddleMan("OK")
                },{
                    serverThread.replyResponseToMiddleMan("NOK")
                }
            )
        })
    }

}
