package com.example.examen3

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvUltimaConexion = findViewById<TextView>(R.id.tvUltimaConexion)
        val prefs = getSharedPreferences("prefs", MODE_PRIVATE)

        // ultima conexión guardada
        val ultimaConexion = prefs.getString("ultima_conexion", "Sin registro")
        tvUltimaConexion.text = "Última conexión: $ultimaConexion"

        // Guardar la fecha y hora actual como ultima conexion
        val formato = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault())
        val fechaActual = formato.format(Date())
        prefs.edit().putString("ultima_conexion", fechaActual).apply()
    }
}
