package com.example.olamundo20

import android.Manifest
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.olamundo20.extension.alert
import kotlinx.android.synthetic.main.activity_contato.*

class ContatoActivity : LogActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ok = PermissionUtils.request(this, arrayOf(Manifest.permission.CALL_PHONE))
        if(!ok){
            alert("Permissão não aceita ou negada")
        }
        setContentView(R.layout.activity_contato)
        btCelular.setOnClickListener{
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:*222#"))
            startActivity(intent)
        }


    }

    //OVERRIDES FUN NÃO TA SOBREESCREVENDO NADA
//    override fun onRequestPermissionResult(
//        requestCode: Int,
//        permission: Array<String>,
//        grantResults: IntArray
//    ){
//        super.onRequestPermissionsResult(requestCode, permission, grantResults)
//        val ok = PermissionUtils.validate(this, permission)
//        if (ok){
//            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:*222#"))
//            startActivity(intent)
//        }
//    }
}