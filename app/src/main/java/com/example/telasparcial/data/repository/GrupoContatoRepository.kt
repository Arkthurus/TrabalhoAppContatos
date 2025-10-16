package com.example.telasparcial.data.repository

import com.example.telasparcial.data.dao.GrupoContatoDAO
import com.example.telasparcial.data.entities.GrupoContato
import com.example.telasparcial.data.entities.pojos.GrupoComContatos
import kotlinx.coroutines.flow.Flow

class GrupoContatoRepository (private val grupoContatoDAO: GrupoContatoDAO){

    fun buscarTodos(): Flow<List<GrupoComContatos>>{
        return grupoContatoDAO.buscarTodos()
    }

    fun buscarPorId(grupoId: Int): Flow<GrupoComContatos?>{
        return grupoContatoDAO.buscarPorId(grupoId)
    }

    suspend fun adicionarAoGrupo(grupoId: Int, contatoid: Int){
        grupoContatoDAO.adicionarAoGrupo(grupoId,contatoid)
    }
}