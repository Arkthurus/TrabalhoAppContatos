package com.example.telasparcial

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.filled.Call

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun TelaDiscagem(){
    Scaffold(
        bottomBar = {BottomBar()}
    ) {
        Column(modifier = Modifier.padding(top = 50.dp)
        ) {
            Discagem()
        }
    }
}
@Preview
@Composable
fun BottomBar() {
    Surface(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(),
        color = Color.LightGray
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Buttons da sua bottom bar
            BottomButton(Icons.Default.Menu, onClick = { /* Ação do botão */ })
            BottomButton(Icons.Default.Call, onClick = { /* Ação do botão */ })
            BottomButton(Icons.Default.AccountCircle, onClick = {})
        }
    }
}

@Preview
@Composable
fun Discagem() {
    // Estado que guarda o número digitado
    var phoneNumber by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier

            .padding(16.dp),

    ) {
        // Usa a coluna para organizar os elementos verticalmente
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Linha para o texto e o botão de apagar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Exibe o número digitado
                Text(
                    text = phoneNumber,
                    fontSize = 28.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp),
                    textAlign = TextAlign.End
                )

                // Botão de apagar
                IconButton(
                    onClick = {
                        if (phoneNumber.isNotEmpty()) {
                            phoneNumber = phoneNumber.dropLast(1)
                        }
                    },
                    modifier = Modifier.size(58.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Apagar"
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Grid de botões numéricos
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Primeira linha (1,2,3)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    NumberButton("1", onClick = { phoneNumber += "1" })
                    NumberButton("2", onClick = { phoneNumber += "2" })
                    NumberButton("3", onClick = { phoneNumber += "3" })
                }
                Spacer(modifier = Modifier.height(8.dp))

                // Segunda linha (4,5,6)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    NumberButton("4", onClick = { phoneNumber += "4" })
                    NumberButton("5", onClick = { phoneNumber += "5" })
                    NumberButton("6", onClick = { phoneNumber += "6" })
                }
                Spacer(modifier = Modifier.height(8.dp))

                // Terceira linha (7,8,9)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    NumberButton("7", onClick = { phoneNumber += "7" })
                    NumberButton("8", onClick = { phoneNumber += "8" })
                    NumberButton("9", onClick = { phoneNumber += "9" })
                }
                Spacer(modifier = Modifier.height(8.dp))

                // Quarta linha (*,0,#)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    NumberButton("*", onClick = { phoneNumber += "*" })
                    NumberButton("0", onClick = { phoneNumber += "0" })
                    NumberButton("#", onClick = { phoneNumber += "#" })
                }
                Spacer(modifier = Modifier.height(40.dp)) // Espaço entre os botões numéricos e os de ação

                // Quinta linha (Ligar, Adicionar Contato)
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    // Botão de Ligar
                    CallActionButton(
                        icon = Icons.Default.Call,
                        contentDescription = "Ligar",
                        onClick = {
                            // Ação do botão Ligar (pode ser implementada aqui)
                        }
                    )
                    Spacer(modifier = Modifier.width(70.dp))
                    // Botão de Adicionar Contato
                    CallActionButton(
                        icon = Icons.Default.AddCircle,
                        contentDescription = "Adicionar Contato",
                        onClick = {
                            // Ação do botão Adicionar Contato (pode ser implementada aqui)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun NumberButton(digit: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.size(110.dp)
    ) {
        Text(digit, fontSize = 32.sp)
    }
}

@Composable
fun CallActionButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(64.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(48.dp)
        )
    }
}
