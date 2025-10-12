package com.example.telasparcial.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.telasparcial.data.dao.GrupoDAO
import com.example.telasparcial.data.entities.Grupo
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class GrupoViewModel @Inject constructor(private val grupoDAO: GrupoDAO) : ViewModel() {
    val grupos: StateFlow<List<Grupo>> = grupoDAO.buscarTodos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun inserirGrupo(grupo: Grupo) {
        viewModelScope.launch {
            grupoDAO.inserirGrupo(grupo)
        }
    }

    fun deletarGrupo(grupo: Grupo) {
        viewModelScope.launch {
            grupoDAO.deletarGrupo(grupo)
        }
    }

    suspend fun buscarGrupoPeloNome(nome: String): Grupo? {
        return grupoDAO.buscarPeloNome(nome)
    }
}
