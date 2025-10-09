package com.example.telasparcial.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.telasparcial.data.entities.Contato

@Dao
interface ContatosDAO {
    @Insert
    suspend fun salvarContato(contato: Contato)

    @Query("SELECT * FROM Contatos")
    suspend fun buscarTodos(): List<Contato>

    @Query("SELECT * FROM Contatos LIMIT 4")
    suspend fun buscar4(): List<Contato>

    @Delete
    suspend fun deletarCtt(contato: Contato)

    @Update
    suspend fun atualizarCtt(contato: Contato)
}