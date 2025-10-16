package com.example.telasparcial.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.telasparcial.data.dao.GrupoContatoDAO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GrupoContatoViewModel constructor(private val grupoContatoDAO: GrupoContatoDAO): ViewModel() {
    val gruposComContatos = grupoContatoDAO.buscarTodos()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList(),
        )

    fun adicionarAoGrupo(grupoId: Int, contatoId: Int) {
        viewModelScope.launch {
            val grupo = grupoContatoDAO.buscarPorId(grupoId).first()

            if (grupo == null) {
                Log.e("GrupoContatoViewModel", "Grupo não encontrado")
                return@launch
            }

            if (grupo.contatos.any { it.id == contatoId }) {
                Log.e("GrupoContatoViewModel", "Contato já está no grupo")
                return@launch
            }

            grupoContatoDAO.adicionarAoGrupo(grupoId, contatoId)
        }
    }
}
