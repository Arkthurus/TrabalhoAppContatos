package com.example.telasparcial.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.telasparcial.BottomButton

@Composable
fun TelaDiscagem(){
    Scaffold(
        bottomBar = { BottomBar() }
    ) { innerPadding ->
        Column(modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
        ) {
//            coisas
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
        color = MaterialTheme.colorScheme.surfaceVariant
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
fun discagem() {
    Surface(modifier = Modifier.height(300.dp).fillMaxWidth().padding(top = 200.dp)){
        Text("OI")
    }
}
