package com.example.olamundo20.extension

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


    fun AppCompatActivity.alert(msg: String) {
        val dialog = AlertDialog.Builder(this).create()
        dialog.setTitle("Android")
        dialog.setMessage(msg)
        dialog.setButton(
            AlertDialog.BUTTON_NEUTRAL, "OK"
        ) {_, which ->
            dialog.dismiss()
        }
        dialog.show()
    }