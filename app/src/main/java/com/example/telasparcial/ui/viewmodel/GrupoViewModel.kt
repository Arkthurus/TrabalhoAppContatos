package com.example.telasparcial.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.telasparcial.data.dao.GrupoDAO
import com.example.telasparcial.data.entities.Contato
import com.example.telasparcial.data.entities.Grupo
import com.example.telasparcial.data.repository.ContatosRepository
import com.example.telasparcial.data.repository.GrupoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class  GrupoUiState(
    val listaDeGrupos: List<Grupo> = emptyList(),
    val nome:   String = "",
    val contatoEmEdit: Contato? = null
){}

class GrupoViewModel (private val grupoRepository: GrupoRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(GrupoUiState())

    val uiState: StateFlow<GrupoUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            grupoRepository.buscarTodos().collect { grupos ->
                _uiState.update { currentState ->
                    currentState.copy(listaDeGrupos = grupos)
                }
            }
        }
    }

    fun inserirGrupo(){

        val state = _uiState.value

        if (state.nome.isBlank()) return

        val grupoInserir = Grupo(nome = state.nome)

        viewModelScope.launch { grupoRepository.inserirGrupo(grupoInserir) }
    }

    fun deletarGrupo(grupo: Grupo){
        viewModelScope.launch {
            grupoRepository.deletarGrupo(grupo)
        }
    }

    suspend fun buscarPeloNome(nome: String): Grupo?{
        return grupoRepository.buscarPeloNome(nome)
    }
}

class GrupoViewModelFactory(private val grupoRepository: GrupoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GrupoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GrupoViewModel(grupoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}