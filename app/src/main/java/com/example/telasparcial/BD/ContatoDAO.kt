package com.example.telasparcial.BD

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContatosDAO{

    @Insert
    suspend fun SaveContact(contato : Contatos)
    @Query("SELECT * FROM contatos")
    suspend fun buscarTodos()      : List<Contatos>
}