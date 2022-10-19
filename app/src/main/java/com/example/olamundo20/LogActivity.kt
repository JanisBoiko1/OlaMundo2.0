package com.example.olamundo20

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/*Activity que imprime log nos métodos do ciclo de vida*/
open class LogActivity : AppCompatActivity() {
    private val TAG = "ciclo de vida"

    //Nome da classe se o pacote
    private val className: String
    get() {
        val s = javaClass.name
        return s.substring(s.lastIndexOf(".")+1)
    }

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        Log.d(TAG, className + ".onCreate().")
    }
    override fun onStart() {
        super.onStart()
        Log.d(TAG, className + ".onStart().")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, className + ".onStart().")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, className + ".onResume().")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, className + ".onPause().")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d(TAG, className + ".onSaveInstanceState().")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, className + ".onStop().")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, className + ".onDestroy().")
    }


}