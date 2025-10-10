package com.example.telasparcial.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.telasparcial.ui.telas.AddCtt
import com.example.telasparcial.ui.telas.TabScreen
import com.example.telasparcial.ui.telas.TelaDiscagem
import com.example.telasparcial.ui.telas.TelaEdit
import com.example.telasparcial.ui.telas.TelaEscanearCodigo
import com.example.telasparcial.ui.telas.TelaLista
import com.example.telasparcial.ui.telas.TelaQR

@Composable
fun AppNav() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "TelaLista") {
        composable("TelaLista") {
            // Passa o navController para a tela principal
            TelaLista(navController,)
        }
        composable(
            route = "TelaEdit/{nomeCtt}/{numeroCtt}/{idCtt}",
            arguments = listOf(
                navArgument("numeroCtt") { type = NavType.StringType },
                navArgument("nomeCtt") { type = NavType.StringType },
                navArgument("idCtt") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val numeroCtt = backStackEntry.arguments?.getString("numeroCtt") ?: ""
            val nomeCtt = backStackEntry.arguments?.getString("nomeCtt") ?: ""
            val idCtt = backStackEntry.arguments?.getInt("idCtt") ?: -1

            TelaEdit(
                numeroCtt = numeroCtt,
                nomeCtt = nomeCtt,
                idCtt = idCtt,
                onNavigateToTelaEdit = {},
                navController = navController
            )
        }
        composable("TelaDiscar") {
            TelaDiscagem(
                navController = navController,
                onNavigateToAddCtt = { numeroCtt: String ->
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
