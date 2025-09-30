package com.example.telasparcial.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.telasparcial.ui.screens.AddCtt
import com.example.telasparcial.ui.screens.TabScreen
import com.example.telasparcial.ui.screens.TelaDiscagem
import com.example.telasparcial.ui.screens.TelaEscanearCodigo
import com.example.telasparcial.ui.screens.TelaLista
import com.example.telasparcial.ui.screens.TelaQR

@Composable
fun AppNav() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "TelaLista") {
        composable("TelaLista") {
            // Passa o navController para a tela principal
            TelaLista(navController)
        }
        composable("TelaDiscar") {
            TelaDiscagem(
                navController = navController,
                onNavigateToAddCtt = { numeroCtt : String ->
                    navController.navigate("TelaAddCtt/$numeroCtt")
                }
            )
        }
        composable("TabScreen"){
            TabScreen(navController)
        }
        composable(
            route = "TelaAddCtt/{numeroCtt}",
            arguments = listOf(navArgument("numeroCtt") { type = NavType.StringType })
        ) { backStackEntry ->
            val numeroCtt = backStackEntry.arguments?.getString("numeroCtt") ?: ""

            // Chama a TelaAddCtt, passando o número e a função onSaveContact
            AddCtt(
                numeroCtt = numeroCtt,
                onSaveContact = { name, number ->
                    // Esta lambda é chamada quando o botão Salvar é clicado
                    // Aqui você pode adicionar a lógica para salvar o contato em um banco de dados, etc.
                    println("Contato a ser salvo: Nome: $name, Número: $number")

                    // Em seguida, voltamos para a tela anterior
                    navController.popBackStack()
                }
            )
        }
        composable("meuCodigo") { TelaQR() }
        composable("escanearCodigo") { TelaEscanearCodigo() }
    }
}
