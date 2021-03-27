package com.salvation.proxylist

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

fun log(mTag: String, message: String) {
    if (BuildConfig.DEBUG) {
        Log.d(mTag, message)
    }
}

fun showDialog(cxt: Context?, message: Int, function: () -> Unit) {
    cxt?.let {
        AlertDialog.Builder(it)
            .setMessage(it.getString(message))
            .setPositiveButton(R.string.ok) { dialog, which ->
                function()
                dialog.dismiss()
            }.show()
    }
}

fun showDialog(cxt: Context?, message: String, function: () -> Unit) {
    cxt?.let {
        AlertDialog.Builder(it)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { dialog, which ->
                function()
                dialog.dismiss()
            }.show()
    }
}

fun toasty(cxt: Context?, message: String) {
    cxt?.let { context ->
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}