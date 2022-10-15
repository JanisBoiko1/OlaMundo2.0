package com.example.olamundo20.domain

import android.content.Intent
import com.example.olamundo20.HomeActivity

class LoginService {
    fun login (login:String, senha:String): Usuario?{
        if (login == "ricardo" && senha == "123") {
            return Usuario("Ricardo", "a@a.com")
        } else if (login == "teste" && senha =="123"){
            return Usuario("Teste",  "b@b.com")
        }
        return null
    }
}