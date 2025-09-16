package com.example.telasparcial

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp


@Composable
fun AddCtt(initialPhoneNumber: String,
           onSaveContact: (String, String) -> Unit
) {
    // Estado para o campo de nome
    var name by remember { mutableStateOf("") }

    // O número de telefone é passado como um parâmetro
    val phoneNumber by remember { mutableStateOf(initialPhoneNumber) }

    // A tela é um Column centralizada
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Adicionar Novo Contato", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(32.dp))

        // Campo para o nome do contato
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo para o número de telefone, desabilitado para edição
        OutlinedTextField(
            value = phoneNumber,
            onValueChange = {}, // Não permite edição
            label = { Text("Número de Telefone") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            readOnly = true, // Torna o campo somente leitura
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botão para salvar
        Button(
            onClick = {
                // Chama a função de salvamento, passando o nome e o número
                onSaveContact(name, phoneNumber)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Salvar Contato")
        }
    }
}

// Preview da tela para visualização no Android Studio
@Preview(showBackground = true)
@Composable
fun AddContactScreenPreview() {
    AddCtt(
        initialPhoneNumber = "41998765432",
        onSaveContact = { name, number ->
            println("Contato salvo! Nome: $name, Número: $number")
        }
    )
}