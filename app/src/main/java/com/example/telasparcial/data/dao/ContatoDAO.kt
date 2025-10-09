package com.example.telasparcial.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.telasparcial.data.entities.Contatos

@Dao
interface ContatosDAO {

    @Insert
    suspend fun saveContact(contato: Contatos)

    @Query("SELECT * FROM contatos")
    suspend fun buscarTodos(): List<Contatos>

    @Query("SELECT * FROM contatos LIMIT 4")
    suspend fun buscar4(): List<Contatos>

    @Delete
    suspend fun deletarCtt(contato: Contatos)

    @Update
    suspend fun atualizarCtt(contato: Contatos)
}