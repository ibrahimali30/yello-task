package com.ibrahim.receiver.presentation

import android.content.Context
import androidx.appcompat.app.AlertDialog

 fun Context.showNewUserDialog(
    title: String?,
    msg: String?,
    onConfirmed: ()->Unit,
    onCanceled: ()->Unit
): AlertDialog? {
    return AlertDialog.Builder(this)
        .setTitle(title)
        .setMessage(msg)
        .setPositiveButton("OK") { dialog, _ ->
            onConfirmed()
            dialog.dismiss()
        }.setNegativeButton("Cancel") { dialog, _ ->
            onCanceled()
            dialog.dismiss()
        }.show()
}