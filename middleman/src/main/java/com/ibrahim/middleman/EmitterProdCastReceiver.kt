package com.ibrahim.middleman

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.ibrahim.emitter.users.data.model.UsersResponseItem

class MiddleManProdCastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val user = intent?.getSerializableExtra("EXTRA_USER") as UsersResponseItem
        Log.d(TAG, "onReceive: $user")
    }
}