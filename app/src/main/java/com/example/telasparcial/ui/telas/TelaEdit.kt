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
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.telasparcial.data.AppDataBase
import com.example.telasparcial.data.entities.Contato
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun TelaEdit(
    numeroCtt: String,
    onNavigateToTelaEdit: (String) -> Unit,
    navController: NavController,
    nomeCtt: String,
    idCtt: Int
) {
    var name by remember { mutableStateOf(nomeCtt) }
    var phoneNumber by remember { mutableStateOf(numeroCtt) }
    val id = idCtt

    val context = LocalContext.current
    val db = AppDataBase.getDataBase(context)
    val contatosDAO = db.contatosDao()

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
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        //Numero de telefone
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Número de Telefone") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            readOnly = false,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                if (name.isNotBlank() && phoneNumber.isNotBlank()) {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val contatoAtualizado = Contato(id = id, nome = name, numero = phoneNumber)
                            Log.d("DEBUG_UPDATE", "Tentando atualizar o ID: $id")
                            contatosDAO.atualizarCtt(contatoAtualizado)
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