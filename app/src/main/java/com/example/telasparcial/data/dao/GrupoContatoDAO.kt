package com.example.telasparcial.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.telasparcial.data.entities.GrupoContato
import com.example.telasparcial.data.entities.pojos.GrupoComContatos

@Dao
interface GrupoContatoDAO {
    @Transaction
    @Query("SELECT * FROM Grupos")
    suspend fun buscarTodos(): List<GrupoComContatos>

    @Transaction
    @Query("SELECT * FROM Grupos WHERE id = :id")
    suspend fun buscarPorId(id: Int): GrupoComContatos?

    @Transaction
    suspend fun adicionarAoGrupo(grupoId: Int, contatoId: Int) {
        val grupoContato = GrupoContato(
            grupoId = grupoId,
            contatoId = contatoId
        )
        inserirGrupoContato(grupoContato)
    }

    @Insert
    suspend fun inserirGrupoContato(grupoContato: GrupoContato)
}