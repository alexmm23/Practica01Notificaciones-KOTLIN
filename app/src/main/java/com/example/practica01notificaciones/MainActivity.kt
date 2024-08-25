package com.example.practica01notificaciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var name: EditText
    private lateinit var password: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // init components
        name = findViewById(R.id.edtName)
        password = findViewById(R.id.edtPwd)
        btnLogin = findViewById(R.id.btnLogin)
        btnLogin.setOnClickListener { login(name.text.toString(), password.text.toString())}

    }

    private fun login(name: String, password: String){
        if(name.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Campos vacios", Toast.LENGTH_SHORT).show()
            return
        }
        if(name == "admin" && password == "admin"){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
        }
    }
}