package com.example.olamundo20

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.olamundo20.domain.LoginService
//import androidx.appcompat.app.AlertDialog
import com.example.olamundo20.extension.alert
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : LogActivity() {
    private var count = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //usando save instance para evitar retrabalho do usuári quando a tela gira
        if (savedInstanceState != null){
            //recupera o count
            Log.d("ciclo de vida", "recuperando estado")
            count = savedInstanceState.getInt("count")
        }
        Log.d("ciclo de vida", "Count: $count")

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

        btLocalizacao.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo://@-25.3848941, -49.2763565,15z"))
            startActivity(intent)
        }
        btFaleConosco.setOnClickListener {
            onClickContato()
        }
    }
    //save instance
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("ciclo de vida", "Salvando estado")
        count++
        outState.putInt("count", count)
    }

    private fun onClickCadastrar() {
        startActivity(Intent(this,CadastrarActivity::class.java))
    }
    private fun onClickEsqueciSenha() {
        startActivity(Intent(this,EsqueciSenhaActivity::class.java))
    }
    private fun onClickContato() {
        startActivity(Intent(this,ContatoActivity::class.java))
    }

    private fun onClickLogin() {
        //encontra as views
        val tLogin = findViewById<TextView>(R.id.tLogin)
        val tSenha = findViewById<TextView>(R.id.tSenha)
        //lê os textos
        val login = tLogin.text.toString()
        val senha = tSenha.text.toString()
        //log cat
        Log.d("[Aula4-prog]", "Login: $login, senha: $senha")
        //valida o login
        val service = LoginService()
        val user = service.login(login,senha)
        if(user != null) {
            //startActivity(Intent(this, com.example.olamundo20.HomeActivity::class.java))
            //fecha a tela de login
            finish()
            //abre a tela da home
            val intent = Intent(this, HomeActivity::class.java)
            val args = Bundle()
            args.putSerializable("usuario", user)
            intent.putExtras(args)
            startActivity(intent)
        } else {
            //erro de login: alerta de erro feito por pacote de extenções
            alert("Login incorreto, digite os dados novamente")

        }

    }
}