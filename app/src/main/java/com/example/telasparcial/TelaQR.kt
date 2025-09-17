package com.example.telasparcial

import android.annotation.SuppressLint
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabScreen(navController: NavController) {
    val tabNavController = rememberNavController()
    var selectedTabIndex by remember { mutableStateOf(0) }
    Scaffold(
        bottomBar = {BottomBar(navController)}
    ) {
        Column {
            PrimaryTabRow(selectedTabIndex = selectedTabIndex) {
                Tab(
                    selected = selectedTabIndex == 0,
                    onClick = {
                        selectedTabIndex = 0
                        tabNavController.navigate("meucodigo")
                    },
                    text = { Text("Meu código") }
                )

                Tab(
                    selected = selectedTabIndex == 1,
                    onClick = {
                        selectedTabIndex = 1
                        tabNavController.navigate("escanearcodigo")
                    },
                    text = { Text("Escanear Código") }
                )
            }

            NavHost(navController = tabNavController, startDestination = "meucodigo") {
                composable("meucodigo") {
                    // A tela de QR Code agora precisa de um parâmetro (ex: o número de telefone)
                    // Se o número de telefone vier de uma tela anterior, ele deve ser
                    // passado para a TabScreen e depois para TelaQR
                    TelaQR()
                }
                composable("escanearcodigo") { TelaEscanearCodigo() }
            }
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