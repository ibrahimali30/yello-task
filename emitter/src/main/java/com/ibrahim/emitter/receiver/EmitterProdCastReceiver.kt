package com.ibrahim.emitter.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.ibrahim.emitter.base.extensions.showNewUserDialog
import com.ibrahim.emitter.users.UsersListActivity
import com.ibrahim.engine.base.EXTRA_RESPONSE

open class  EmitterProdCastReceiver  : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val response = intent?.getStringExtra(EXTRA_RESPONSE)
        Log.d(com.ibrahim.engine.base.TAG, "EmitterProdCastReceiver onReceive: $response")
        UsersListActivity.uiContext.showNewUserDialog("Response from receiver is $response")
    }

}