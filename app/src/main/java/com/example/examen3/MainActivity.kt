package com.example.examen3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNombre = findViewById<EditText>(R.id.etNombre)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)
        val btnRegistrarse = findViewById<Button>(R.id.btnRegistrarse)

        btnIniciarSesion.setOnClickListener {
            val nombre = etNombre.text.toString()
            val password = etPassword.text.toString()

            val db = MyApplication.getDatabase(this)
            lifecycleScope.launch(Dispatchers.IO) {
                val usuario = db.usuarioDao().buscarUsuario(nombre, password)
                withContext(Dispatchers.Main) {
                    if (usuario != null) {
                        val intent = Intent(this@MainActivity, ProfileActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@MainActivity, "Las credenciales no son correctas", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnRegistrarse.setOnClickListener {
            val nombre = etNombre.text.toString()

            val db = MyApplication.getDatabase(this)
            lifecycleScope.launch(Dispatchers.IO) {
                val existente = db.usuarioDao().buscarPorNombre(nombre)
                withContext(Dispatchers.Main) {
                    if (existente != null) {
                        Toast.makeText(this@MainActivity, "El usuario ya está registrado", Toast.LENGTH_SHORT).show()
                    } else {
                        val intent = Intent(this@MainActivity, SignUpActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}