package com.example.olamundo20

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtils {
    //solicita uma permissao se ainda nao foi concedida
    fun request (activity: Activity, permissions: Array<String>) : Boolean {
        val ok = validate(activity, permissions)
        if(!ok){
            ActivityCompat.requestPermissions(
                activity,
                permissions,
                1
            )
        }
        return ok
    }

    //valida se as permissões foram concedidas
    fun validate(context: Context, permissions: Array<String>): Boolean {
        for (permission in permissions) {
            val result = ContextCompat.checkSelfPermission(context, permission)
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false
            }
        }
        return true
    }

}