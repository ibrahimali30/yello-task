package com.ibrahim.receiver


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ibrahim.engine.users.presentation.viewmodel.UsersLocalViewModel
import com.ibrahim.receiver.presentation.showNewUserDialog
import com.ibrahim.receiver.users.UsersListActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_receiver_main.*
import javax.inject.Inject

@AndroidEntryPoint
class ReceiverMainActivity : AppCompatActivity() {

    lateinit var serverThread: ServerThread

    @Inject
    lateinit var wordsViewModel: UsersLocalViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiver_main)

        startServerService()
        initViews()
    }

    private fun startServerService() {
        serverThread  = ServerThread()
        serverThread.start()

        serverThread.liveData.observe(this, Observer {

            showNewUserDialog(
                it.name,
                "do you want to add ${it.username}",
                onConfirmed = {
                    serverThread.replyResponseToMiddleMan("OK")
                    wordsViewModel.insertUserInToLocalDB(it)
                },
                onCanceled = {
                    serverThread.replyResponseToMiddleMan("NOK")
                }
            ).also {
                it?.setCancelable(false)
            }
        })
    }

    private fun initViews() {
        bt_saved_users.setOnClickListener {
            startActivity(Intent(this, UsersListActivity::class.java))
        }
    }

    override fun onBackPressed() {

    }
}




