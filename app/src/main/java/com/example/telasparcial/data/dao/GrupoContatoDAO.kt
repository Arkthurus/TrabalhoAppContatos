package com.example.telasparcial.data.dao

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import com.example.telasparcial.data.entities.pojos.GrupoComContatos

interface GrupoContatoDAO {
    @Transaction
    @Query("SELECT * FROM GruposContatos")
    suspend fun buscarTodos(): GrupoComContatos

    @Transaction
    @Query("SELECT * FROM Grupos WHERE id = :id")
    suspend fun buscarPorId(id: Int): GrupoComContatos

    @Transaction
    @Delete
    suspend fun deletar(id: Int)
}