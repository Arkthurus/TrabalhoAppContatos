package com.example.telasparcial.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.telasparcial.data.dao.ContatosDAO
import com.example.telasparcial.data.entities.Contato
import com.example.telasparcial.data.repository.ContatosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class  ContatosUiState(
    val listaDeContatos: List<Contato> = emptyList(),
    val nome:   String = "",
    val numero: String = " "
){}


@HiltViewModel
class ContatoViewModel @Inject constructor(private val contatosRepository: ContatosRepository): ViewModel() {

    private val _uiState = MutableStateFlow(ContatosUiState())

    val uiState: StateFlow<ContatosUiState> = _uiState.asStateFlow()

    init {

        viewModelScope.launch {
            contatosRepository.buscarTodos().collect { contatos ->
                _uiState.update { currentState ->
                    currentState.copy(listaDeContatos = contatos)
                }
            }
        }
    }

    fun salvarContato(){

        val state = _uiState.value

        if (state.nome.isNotBlank() || state.numero.isNotBlank()) return

        val contatoSalvar = Contato(nome = state.nome, numero = state.numero)

        viewModelScope.launch {
            contatosRepository.salvarContato(contatoSalvar)
        }
    }

    fun buscarTodos(){}


}

class ContatosViewModelFactory(private val contatoRepository: ContatosRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContatoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContatoViewModel(contatoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
