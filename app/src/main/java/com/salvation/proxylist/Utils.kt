package com.salvation.proxylist

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

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

fun CoordinatorLayout.showInternetError(cxt: Context?, message: Int): Snackbar? {
    cxt?.let { context ->
        return Snackbar.make(this, cxt.getString(message), Snackbar.LENGTH_INDEFINITE)
            .setActionTextColor(ContextCompat.getColor(context, android.R.color.holo_red_light))
            .setAction(android.R.string.ok) {
                //context.startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
            }
    }
    return null
}

fun CoordinatorLayout.showInternetError(cxt: Context?, message: String): Snackbar? {
    cxt?.let { context ->
        return Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
            .setActionTextColor(ContextCompat.getColor(context, android.R.color.holo_red_light))
            .setAction(android.R.string.ok) {
                //context.startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
            }
    }
    return null
}