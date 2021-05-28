package com.ibrahim.receiver


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ibrahim.engine.users.presentation.viewmodel.UsersLocalViewModel
import com.ibrahim.engine.users.presentation.viewmodel.UsersViewModel
import com.ibrahim.receiver.presentation.showNewUserDialog
import com.ibrahim.receiver.users.UsersListActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_receiver_main.*
import java.lang.Exception
import javax.inject.Inject
import kotlin.concurrent.thread

@AndroidEntryPoint
class ReceiverMainActivity : AppCompatActivity() {

    @Inject
    lateinit var wordsViewModel: UsersLocalViewModel
    val serverThread: ServerThread by lazy {
        ServerThread()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver_main)

        initViews()
        startServerService()

    }

    override fun onResume() {
        super.onResume()
        try {
            serverThread.start()
        }catch (e: Exception){
            e
        }

    }

    private fun startServerService() {

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

    private fun initViews() {
        bt_saved_users.setOnClickListener {
            startActivity(Intent(this, UsersListActivity::class.java))
        }
    }

}
