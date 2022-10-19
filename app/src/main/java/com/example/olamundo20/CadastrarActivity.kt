package com.example.olamundo20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.example.olamundo20.domain.CadastroModel
import com.example.olamundo20.domain.CadastroService
import com.example.olamundo20.extension.alert
//import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastrarActivity : LogActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        findViewById<Button>(R.id.btCadastrar).setOnClickListener {
            onClickCadastrar()
        }
    }
    private fun onClickCadastrar() {
        val termosOk = findViewById<CheckBox>(R.id.checkTermos).isChecked
        if (!termosOk) {
            alert("Aceite os teros para continuar")
        } else {
            // cria objeto de cadastro
            val model = getCadastroModel()
            val service = CadastroService()
            val ok: Boolean = service.cadastrar(model)
            if (ok) {
                alert("Cadastro realizado com sucesso. \nSua senha foi enviara para o email.")
                {
                    finish()
                }
                } else {
                    alert("Ocorreu um erro ao cadastrar.")
                }
            }
        }

    // cria o objeto de modelo copiando os dados do form
    private fun getCadastroModel(): CadastroModel {
        val model = CadastroModel()
        model.nome = findViewById<EditText>(R.id.tNome).text.toString()
        model.login = findViewById<EditText>(R.id.tLogin).text.toString()
        model.email = findViewById<EditText>(R.id.tEmail).text.toString()
        model.sexo = if (findViewById<RadioGroup>(R.id.radioSexo) == findViewById<RadioButton>(R.id.radioMasculino)) "M" else "F"
        return model

    }
}
