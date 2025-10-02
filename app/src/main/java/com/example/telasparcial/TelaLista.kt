package com.example.telasparcial

import android.util.Log
import android.webkit.WebViewDatabase
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.telasparcial.BD.ContatosDAO
import com.example.telasparcial.BD.AppDataBase
import com.example.telasparcial.BD.Contatos
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun TelaLista(navController: NavController) {
    Scaffold(
        bottomBar = { BottomBar(navController) },
        topBar = { SearchBar() }
    ) { innerPadding ->
        // O conteúdo principal da tela, usando o padding fornecido pelo Scaffold
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            item{Spacer(modifier = Modifier.height(5.dp))}
            item { FavoriteContacts() }
            item{Spacer(modifier = Modifier.height(10.dp))}
            item{RecentContactsList()}
            item{DuploCtt()}
        }

    }
}


// Composable genérico para os botões da Bottom Bar
@Composable
fun BottomButton(icon: ImageVector, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(90.dp)
            .padding(10.dp),
        shape = ButtonDefaults.filledTonalShape
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
    }
}

// Aqui você pode manter suas outras funções composable
@Composable
fun SearchBar() {
    var contactName by remember { mutableStateOf("Pesquisar") }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(top = 50.dp)
    ) {
        Row() {
            TextField(
                modifier = Modifier
                    .weight(1f) // Usa o peso para ocupar o espaço restante
                    .padding(end = 5.dp),
                value = contactName,
                onValueChange = { contactName = it }
            )
            Button(
                onClick = { /* Ação de pesquisa */ },
                modifier = Modifier.size(60.dp) // Ajusta o tamanho do botão
            ) {
                Icon(Icons.Default.Search, contentDescription = "Pesquisar")
            }
        }
    }
}

@Preview
@Composable
private fun FavoriteContacts() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .width(420.dp)
            .height(120.dp)
            .padding(start = 22.dp, end = 20.dp, top = 10.dp)
    ) {
        Column {
            Text(
                text = "Favoritos",
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center,
            )
            Row {
                Spacer(modifier = Modifier.width(15.dp))
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(start = 10.dp, bottom = 10.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(start = 10.dp, bottom = 10.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(start = 10.dp, bottom = 10.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "",
                    modifier = Modifier
                        .size(60.dp)
                        .padding(start = 10.dp, bottom = 10.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}

@Preview
@Composable
private fun RecentContactsList() {

    var contatos by remember { mutableStateOf<List<Contatos>>(emptyList()) }

    val context     =             LocalContext.current

    val db          = AppDataBase.getDataBase(context)

    val ContatosDAO =                 db.contatosDao()

    LaunchedEffect(Unit) {
        try {
            contatos = ContatosDAO.buscarTodos()
        } catch (e: Exception) {
            Log.e("Erro ao add contato", "Msg: ${e.message}")
        }
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = Modifier.size(width = 400.dp, height = 300.dp)
    ) {
        Text(
            text = "Recentes",
            modifier = Modifier.padding(start = 35.dp),
            textAlign = TextAlign.Center,
        )
        LazyColumn {
            items(contatos) { contatos ->
                var nome : String = contatos.nome
                RecentContactCard(nome)
            }
        }
    }
}

@Composable
fun DuploCtt() {
    var contatos by remember { mutableStateOf<List<Contatos>>(emptyList()) }
    val context = LocalContext.current
    val db = AppDataBase.getDataBase(context)
    val contatosDAO = db.contatosDao()
    val coroutineScope = rememberCoroutineScope()// Use rememberCoroutineScope
    var contatosFlow = remember(contatosDAO) {
        coroutineScope.launch {  contatosDAO.buscarTodos() }}

    // Função para buscar os contatos no banco de dados e atualizar o estado
    fun getContatos() {
        coroutineScope.launch {
            try {
                contatos = contatosDAO.buscarTodos()
            } catch (e: Exception) {
                Log.e("Erro ao buscar contatos", "Msg: ${e.message}")
            }
        }
    }

    LaunchedEffect(Unit) {
        getContatos()
    }

    Column {
        contatos.chunked(2).forEach { parDeContatos ->
            Row(
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                parDeContatos.forEach { contato ->
                    // Passe o callback 'getContatos' para o ContactsCards
                    ContactsCards(contato, contatosDAO, onContatoDeletado = {
                        getContatos()
                    })
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}
@OptIn(DelicateCoroutinesApi::class)
@Composable
private fun ContactsCards(contato : Contatos, contatosDAO: ContatosDAO, onContatoDeletado: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    Spacer(modifier = Modifier.width(20.dp))
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            modifier = Modifier
                .size(width = 190.dp, height = 140.dp)


        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "",
                    modifier = Modifier
                        .size(40.dp)
                        .padding(start = 5.dp, top = 5.dp)
                )
                Text(
                    text = contato.nome,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                )
            }
            Row {
                Text(
                    text = contato.numero,
                    modifier = Modifier.padding(start = 15.dp)
                )
            }
            Row {
                //Editar
                Button(
                    onClick = {},
                    modifier = Modifier
                        .width(95.dp)
                        .padding(10.dp),
                    shape = ButtonDefaults.filledTonalShape
                ) {
                    Icon(
                        Icons.Default.Create,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
                //Deletar
                Button(
                    onClick = {
                        coroutineScope.launch{
                            contatosDAO.deletarCtt(contato)
                            onContatoDeletado()
                        }
                    },
                    modifier = Modifier
                        .width(95.dp)
                        .padding(top = 10.dp, bottom = 10.dp, end = 10.dp),
                    shape = ButtonDefaults.filledTonalShape
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }
        }
    }



@Composable
fun RecentContactCard(nome: String) {

    Column(modifier = Modifier.padding()) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(top = 5.dp, bottom = 5.dp, start = 22.dp)
                .border(
                    shape = CircleShape,
                    border = BorderStroke(5.dp, color = Color.LightGray)
                )
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .background(shape = CircleShape, color = Color.LightGray)
                    .padding(start = 5.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row() {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = nome,
                        modifier = Modifier.padding(bottom = 15.dp, start = 15.dp, top = 10.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}
