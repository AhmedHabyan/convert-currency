package com.example.currencyconverter.ui.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import androidx.core.view.isVisible
import androidx.transition.Visibility


fun View.setVisiblity(){
    isVisible=true
}

fun View.clearVisiblity(){
    isVisible=false
}

fun showErrorDialog(
    context: Context,
    onPositiveButtonClicked:(dialogInterface:DialogInterface?,p1:Int)->Unit,
    onNegativeButtonClicked:(dialogInterface:DialogInterface?,p1:Int)->Unit,
    message: String) {
    AlertDialog.Builder(context) // use "requireContext()" if inside Fragment
        .setTitle("Error")
        .setMessage(message)
        .setPositiveButton("refresh"){p0,p1->
            onPositiveButtonClicked(p0,p1)

        }
        .setNegativeButton("cancel"){p0,p1->
            onNegativeButtonClicked(p0,p1)
        }
        .setCancelable(true)

        .show()
}