package com.ibrahim.receiver

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ibrahim.engine.users.data.model.UserUiModel
import com.ibrahim.engine.users.domain.mapper.userJsonToUserModel
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import kotlin.concurrent.thread


class ServerThread() : Thread() {

    val liveData = MutableLiveData<UserUiModel>()
    private val TAG = "log*** receiver"

    lateinit var serverSocket: ServerSocket
    lateinit var socket: Socket
    lateinit var writer: PrintWriter
    lateinit var reader: BufferedReader

    override fun run() {
        super.run()

        try {
            serverSocket = ServerSocket(8080)
            serverSocket.reuseAddress = true
            connectSocket()
            while (true) {
                val userJson = reader.readLine() ?: ""
                handleReceivedSocketMessage(userJson)
                connectSocket()
            }

        } catch (e: IOException) {
            e.printStackTrace()
//            socket.close()
        }
    }

    private fun handleReceivedSocketMessage(userJson: String) {
        Log.d(TAG, "handleReceivedSocketMessage e: $userJson")
        liveData.postValue(userJsonToUserModel(userJson))
    }

    private fun connectSocket() {
        socket = serverSocket.accept()
        writer = PrintWriter(socket.getOutputStream())
        reader = BufferedReader(InputStreamReader(socket.getInputStream()))
    }

    fun replyResponseToMiddleMan(response: String) {
        thread {
            writer.write(response + "\n")
            writer.flush()
        }
    }

}