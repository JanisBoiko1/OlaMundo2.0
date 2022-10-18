package com.example.olamundo20.domain

import android.util.Log
import com.example.olamundo20.extension.alert

class CadastroService{
        fun cadastrar (model: CadastroModel) : Boolean {
            Log.d("[Aula4-prog]","Cadastro $model")
            return true
        }
    }
