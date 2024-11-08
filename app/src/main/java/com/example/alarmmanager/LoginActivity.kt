package com.example.alarmmanager

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Verificar credenciales
            if (username == "Equipo1" && password == "Proyecto") {
                // Iniciar MainActivity si las credenciales son correctas
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Cerrar LoginActivity para evitar que se regrese al inicio de sesión
            } else {
                // Mostrar mensaje de error si las credenciales son incorrectas
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}