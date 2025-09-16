package com.example.telasparcial.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.telasparcial.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TabScreen() {
    val navController = rememberNavController()

    Column {
        PrimaryTabRow(0) {
            Tab(
                true,
                onClick = {
                    navController.navigate("meucodigo")
                },
                text = { Text("Meu código") }
            )

            Tab(
                false,
                onClick = {
                    navController.navigate("meucodigo")
                },
                text = { Text("Escanear Código") }
            )
        }

        NavHost(navController = navController, startDestination = "meucodigo") {
            composable("meucodigo") { TelaQR() }
            composable("escanearcodigo") { TelaEscanearCodigo() }
        }
    }
}

@Composable
@Preview
fun TelaQR() {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Column {
            Spacer(Modifier.height(10.dp))
            ProfileStats()
            Spacer(Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Image(painter = painterResource(id = R.drawable.qr_code), contentDescription = null)
            }
        }
    }
}


@Composable
fun TelaEscanearCodigo() {

}

@Composable
fun TabButton(text: String = "Teste") {
    Surface(
        onClick = {},
        modifier = Modifier
            .width(190.dp)
            .fillMaxHeight()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text)
        }
    }
}


@Composable
fun ProfileStats() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            Icons.Default.AccountCircle,
            "User Icon",
            modifier = Modifier
                .size(100.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Text(
            """
                Nome: Janeiro Fevereiro de Março Abril
                Número: (99) 99999-9999
                Email: Janeiro.fevereiro@março.abril
            """.trimIndent()
        )
    }
}
