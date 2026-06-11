package com.example.examen3

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.examen3.model.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etNombre = findViewById<EditText>(R.id.etNombreRegistro)
        val etPassword = findViewById<EditText>(R.id.etPasswordRegistro)
        val btnRegistrarse = findViewById<Button>(R.id.btnRegistrarseSignUp)

        btnRegistrarse.setOnClickListener {
            val nombre = etNombre.text.toString()
            val password = etPassword.text.toString()

            val db = MyApplication.getDatabase(this)
            lifecycleScope.launch(Dispatchers.IO) {
                val usuario = Usuario(nombre = nombre, password = password)
                db.usuarioDao().insertar(usuario)
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@SignUpActivity, "Usuario registrado", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
