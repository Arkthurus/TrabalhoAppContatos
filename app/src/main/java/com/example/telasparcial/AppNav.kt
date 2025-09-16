package com.example.telasparcial

import android.service.autofill.OnClickAction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNav() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "TelaLista") {
        composable("TelaLista") {
            // Passa o navController para a tela principal
            Telalista(navController)
        }
        composable("TelaDiscar") {
            // Passa o navController para a tela de discagem
            TelaDiscagem(navController)
        }
        composable("TelaAddCtt") {
            // Tela de adicionar contato
            TelaAddCtt(navController)
        }
        composable("TelaQRCode") {
            // Tela de QRCode
            TelaQRCode(navController)
        }
        composable("TelaQRCodeScan") {
            // Tela de scanner de QRCode
            TelaQRCodeScan(navController)
        }
    }
}
@Composable
fun Telalista(navController: NavController){
    Button(
        onClick = {
            navController.navigate("TelaDiscar")
        }
    ) {
        Text("Discagem")
    }
}
@Composable
fun TelaDiscagem(navController: NavController){
    Button(
        onClick = {
            navController.navigate("TelaDiscar")
        }
    ) {
        Text("Discagem")
    }
}

// Em um novo arquivo, ou no mesmo, dependendo da sua organização
@Composable
fun TelaAddCtt(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Tela Adicionar Contato")
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}

@Composable
fun TelaQRCode(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Tela QR Code")
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}

@Composable
fun TelaQRCodeScan(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Tela QR Code Scan")
        Button(onClick = { navController.popBackStack() }) {
            Text("Voltar")
        }
    }
}
