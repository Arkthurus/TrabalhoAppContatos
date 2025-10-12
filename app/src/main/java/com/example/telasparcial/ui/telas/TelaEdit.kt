package com.example.telasparcial.ui.telas


import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.telasparcial.data.entities.Contato
import com.example.telasparcial.viewmodel.ContatoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun TelaEdit(
    numeroCtt: String,
    navController: NavController,
    nomeCtt: String,
    idContato: Int,
    contatoViewModel: ContatoViewModel = hiltViewModel()
) {
    var nome by remember { mutableStateOf(nomeCtt) }
    var numeroTelefone by remember { mutableStateOf(numeroCtt) }
    val id = idContato

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Modificar Contato", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(32.dp))

        //Nome
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Numero de telefone
        OutlinedTextField(
            value = numeroTelefone,
            onValueChange = { numeroTelefone = it },
            label = { Text("Número de Telefone") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            readOnly = false,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (nome.isNotBlank() && numeroTelefone.isNotBlank()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val contatoAtualizado = Contato(id = id, nome = nome, numero = numeroTelefone)
                            Log.d("DEBUG_UPDATE", "Tentando atualizar o ID: $id")
                            contatoViewModel.atualizarContato(contatoAtualizado)
                            withContext(Dispatchers.Main){
                                navController.popBackStack()
                            }
                        } catch (e: Exception) {
                            Log.e("Erro ao editar contato", "Msg: ${e.message}")
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar Alterações")
        }
    }
}