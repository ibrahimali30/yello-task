package com.ibrahim.receiver


import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ibrahim.engine.users.presentation.viewmodel.UsersLocalViewModel
import com.ibrahim.engine.users.presentation.viewmodel.UsersViewModel
import com.ibrahim.receiver.presentation.showNewUserDialog
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.concurrent.thread

@AndroidEntryPoint
class ReceiverMainActivity : AppCompatActivity() {

    lateinit var serverThread: ServerThread

    @Inject
    lateinit var wordsViewModel: UsersLocalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver_main)

        startServerService()
        wordsViewModel.getUsersFromLocalDB()

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
                    wordsViewModel.insertUserInToLocalDB(it)
                },{
                    serverThread.replyResponseToMiddleMan("NOK")
                }
            )
        })
    }

}
