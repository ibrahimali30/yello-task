package com.ibrahim.emitter.base.extensions

import android.content.Context
import androidx.appcompat.app.AlertDialog

fun Context.showNewUserDialog(
    msg: String?
): AlertDialog? {
    return AlertDialog.Builder(this)
        .setMessage(msg)
        .setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }.show()
}