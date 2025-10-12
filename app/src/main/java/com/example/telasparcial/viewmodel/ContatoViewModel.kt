package com.example.telasparcial.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.telasparcial.data.dao.ContatosDAO
import com.example.telasparcial.data.entities.Contato
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContatoViewModel @Inject constructor(private val contatoDAO: ContatosDAO): ViewModel() {

    val contatos: StateFlow<List<Contato>> = contatoDAO.buscarTodos()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val contatos4: StateFlow<List<Contato>> = contatoDAO.buscar(4)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun addContato(contato: Contato) {
        viewModelScope.launch {
            contatoDAO.salvarContato(contato)
        }
    }

    fun deletarContato(contato: Contato) {
        viewModelScope.launch {
            contatoDAO.deletarContato(contato)
        }
    }

    fun atualizarContato(contato: Contato) {
        viewModelScope.launch {
            contatoDAO.atualizarContato(contato)
        }
    }
}