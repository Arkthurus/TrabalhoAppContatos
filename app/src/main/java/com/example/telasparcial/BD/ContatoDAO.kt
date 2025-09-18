package com.example.telasparcial.BD

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ContatosDAO{

    @Insert
    fun addContato(contato : Contatos)
    @Query("SELECT * FROM contatos")
    fun buscarTodos()      : List<Contatos>
}