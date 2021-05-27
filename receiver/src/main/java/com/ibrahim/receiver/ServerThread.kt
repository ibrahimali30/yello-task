package com.ibrahim.receiver

import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket


class ServerThread : Thread() {

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
            socket.close()
        }
    }

    private fun handleReceivedSocketMessage(userJson: String) {
        Log.d(TAG, "handleReceivedSocketMessage: $userJson")

        // TODO: 5/27/2021 parse user json to user object
    }

    private fun connectSocket() {
        socket = serverSocket.accept()
        writer = PrintWriter(socket.getOutputStream())
        reader = BufferedReader(InputStreamReader(socket.getInputStream()))
    }

}