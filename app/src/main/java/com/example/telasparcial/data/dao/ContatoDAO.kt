package com.example.telasparcial.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.telasparcial.data.entities.Contatos

@Dao
interface ContatosDAO {
    @Insert
    fun addContato(contato: Contatos)

    @Query("SELECT * FROM contatos")
    fun buscarTodos(): List<Contatos>
}