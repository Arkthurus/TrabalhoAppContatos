package com.example.telasparcial.BD

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ContatosDAO{

    @Insert
    suspend fun SaveContact(contato : Contatos)
    @Query("SELECT * FROM contatos")
    suspend fun buscarTodos()      :List<Contatos>
    @Delete
    suspend fun deletarCtt(contato: Contatos)
    @Update
    suspend fun atualizarCtt(contato: Contatos)
}