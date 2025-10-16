package com.example.telasparcial.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.telasparcial.data.AppDatabase
import com.example.telasparcial.data.repository.ContatosRepository
import com.example.telasparcial.data.repository.GrupoRepository
import com.example.telasparcial.ui.telas.AddCtt
import com.example.telasparcial.ui.telas.TabScreen
import com.example.telasparcial.ui.telas.TelaDiscagem
import com.example.telasparcial.ui.telas.TelaEdit
import com.example.telasparcial.ui.telas.TelaEscanearCodigo
import com.example.telasparcial.ui.telas.TelaLista
import com.example.telasparcial.ui.telas.TelaQR
import com.example.telasparcial.ui.viewmodel.ContatoViewModel
import com.example.telasparcial.ui.viewmodel.ContatosViewModelFactory
import com.example.telasparcial.ui.viewmodel.GrupoViewModel
import com.example.telasparcial.ui.viewmodel.GrupoViewModelFactory

@Composable
fun AppNav() {

    val navController = rememberNavController()

    val contatoViewModel: ContatoViewModel = viewModel(
        factory = ContatosViewModelFactory(
            ContatosRepository(AppDatabase.getDatabase(LocalContext.current).contatosDAO())
        )
    )
    val grupoViewModel: GrupoViewModel = viewModel(
    factory = GrupoViewModelFactory(
        GrupoRepository(AppDatabase.getDatabase(LocalContext.current).grupoDAO())
    )
    )
//    val grupoContatoViewModel: GrupoContatoViewModel = viewModel(
//    factory = GrupoContatoViewModelFactory(
//        GrupoContatoRepository(AppDatabase.getDatabase(LocalContext.current).gruposContatosDAO()
//    )
//    )

    val uiStateCtt by contatoViewModel.uiState.collectAsStateWithLifecycle()

    NavHost(navController = navController, startDestination = "TelaLista") {
        composable("TelaLista") {
            // Passa o navController para a tela principal
            TelaLista(navController, contatoViewModel, grupoViewModel)
        }
        composable(
            route = "TelaEdit/{nomeCtt}/{numeroCtt}",
            arguments = listOf(
                navArgument("numeroCtt") { type = NavType.StringType },
                navArgument("nomeCtt") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val numeroCtt = backStackEntry.arguments?.getString("numeroCtt") ?: ""
            val nomeCtt = backStackEntry.arguments?.getString("nomeCtt") ?: ""

            TelaEdit(
                numeroCtt = numeroCtt,
                nomeCtt = nomeCtt,
                navController = navController,
                contatoViewModel = contatoViewModel
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
        composable("TabScreen") {
            TabScreen(navController)
        }
        composable(
            route = "TelaAddCtt/{numeroCtt}",
            arguments = listOf(navArgument("numeroCtt") { type = NavType.StringType })
        ) { backStackEntry ->
            val numeroCtt = backStackEntry.arguments?.getString("numeroCtt") ?: ""

            AddCtt(
                //Manter isso
                numeroCtt = numeroCtt,
                onSaveContact = { name, number ->
                    println("Contato a ser salvo: Nome: $name, Número: $number")
                    contatoViewModel.salvarContato()
                    navController.popBackStack()
                }
            )
        }
        composable("meuCodigo") { TelaQR() }
        composable("escanearCodigo") { TelaEscanearCodigo() }
    }
}
