package com.example.olamundo20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.olamundo20.extension.alert

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //delega o tratamento para o metodo correto
        findViewById<Button>(R.id.btLogin).setOnClickListener{
            onClickLogin()
        }
        findViewById<Button>(R.id.btCadastrar).setOnClickListener {
            onClickCadastrar()
        }
        findViewById<Button>(R.id.btEsqueciSenha).setOnClickListener {
            onClickEsqueciSenha()
        }
    }
//    private fun onClickLogin() {
//        startActivity(Intent(this,HomeActivity::class.java))
//    }
    private fun onClickCadastrar() {
        startActivity(Intent(this,CadastrarActivity::class.java))
    }
    private fun onClickEsqueciSenha() {
        startActivity(Intent(this,EsqueciSenhaActivity::class.java))
    }

    private fun onClickLogin() {
        //encontra as views
        val tLogin = findViewById<TextView>(R.id.tLogin)
        val tSenha = findViewById<TextView>(R.id.tSenha)
        //lê os textos
        val login = tLogin.text.toString()
        val senha = tSenha.text.toString()
        if (login == "ricardo" && senha == "123") {
            //login okay, val para o nome
            startActivity(Intent(this, com.example.olamundo20.HomeActivity::class.java))
        } else {
            //erro de login
            alert("Login incorreto, digite os dados novamente")
//            val dialog = AlertDialog.Builder(this).create()
//            dialog.setTitle("Android")
//            dialog.setMessage("Login incorreto, digite os dados novamente")
////            dialog.setButton(AlertDialog.BUTTON_NEUTRAL, 'OK')
////            ) { _ , which ->
////                dialog.dismiss()
////            }
//            dialog.show()
//            }
        }
    }
}