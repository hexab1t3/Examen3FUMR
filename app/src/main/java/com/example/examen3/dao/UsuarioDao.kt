package com.example.examen3.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.examen3.model.Usuario

@Dao
interface UsuarioDao {
    @Insert
    suspend fun insertar(usuario: Usuario)

    @Query("SELECT * FROM usuarios_table WHERE nombre = :nombre AND password = :password")
    suspend fun buscarUsuario(nombre: String, password: String): Usuario?

    @Query("SELECT * FROM usuarios_table WHERE nombre = :nombre")
    suspend fun buscarPorNombre(nombre: String): Usuario?
}