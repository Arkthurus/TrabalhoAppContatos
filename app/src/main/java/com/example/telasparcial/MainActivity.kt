package com.example.telasparcial

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.telasparcial.ui.theme.TelasParcialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TelasParcialTheme {
                // O Scaffold principal que define a estrutura da tela
                Scaffold(
                    bottomBar = {
                        // A sua barra de navegação está aqui, fora da função BottomBar()
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
                                BottomButton(Icons.Default.Call, onClick = { /* Ação do botão */ })
                                BottomButton(Icons.Default.Menu, onClick = { /* Ação do botão */ })
                                BottomButton(Icons.Default.DateRange, onClick = {})
                            }
                        }
                    }
                ) { innerPadding ->
                    // O conteúdo principal da tela, usando o padding fornecido pelo Scaffold
                    Column(modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState())
                    ) {
                        SearchBar()
                        Spacer(modifier = Modifier.height(5.dp))
                        FavoriteContacts()
                        Spacer(modifier = Modifier.height(10.dp))
                        RecentContactsList()
                        ContactsCards()
                    }
                }
            }
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
            .padding(10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
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
            .fillMaxWidth()
            .height(120.dp)
            .padding(start = 22.dp, end = 20.dp)
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
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = Modifier.size(width = 400.dp, height = 300.dp)
    ) {
        Column {
            Text(
                text = "Recentes",
                modifier = Modifier.padding(start = 35.dp),
                textAlign = TextAlign.Center,
            )
            RecentContactCard()
            RecentContactCard()
            RecentContactCard()
            RecentContactCard()
        }
    }
}

@Preview
@Composable
private fun ContactsCards() {
    Text(
        text = "A",
        modifier = Modifier.padding(start = 22.dp)
    )
    Spacer(modifier = Modifier.width(80.dp))
    Column {
        Row {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 190.dp, height = 125.dp)
                    .padding(start = 40.dp)
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
                        text = "Contato1",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Row {
                    Text(
                        "(99)9999-9999",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 190.dp, height = 125.dp)
                    .padding(start = 20.dp, end = 20.dp)
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
                        text = "Contato2",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Row {
                    Text(
                        "(99)9999-9999",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 190.dp, height = 125.dp)
                    .padding(start = 40.dp)
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
                        text = "Contato1",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Row {
                    Text(
                        "(99)9999-9999",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 190.dp, height = 125.dp)
                    .padding(start = 20.dp, end = 20.dp)
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
                        text = "Contato2",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Row {
                    Text(
                        "(99)9999-9999",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 190.dp, height = 125.dp)
                    .padding(start = 40.dp)
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
                        text = "Contato1",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Row {
                    Text(
                        "(99)9999-9999",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 190.dp, height = 125.dp)
                    .padding(start = 20.dp, end = 20.dp)
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
                        text = "Contato2",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Row {
                    Text(
                        "(99)9999-9999",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 190.dp, height = 125.dp)
                    .padding(start = 40.dp)
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
                        text = "Contato1",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Row {
                    Text(
                        "(99)9999-9999",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 190.dp, height = 125.dp)
                    .padding(start = 20.dp, end = 20.dp)
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
                        text = "Contato2",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Row {
                    Text(
                        "(99)9999-9999",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 190.dp, height = 125.dp)
                    .padding(start = 40.dp)
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
                        text = "Contato1",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Row {
                    Text(
                        "(99)9999-9999",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 190.dp, height = 125.dp)
                    .padding(start = 20.dp, end = 20.dp)
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
                        text = "Contato2",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Row {
                    Text(
                        "(99)9999-9999",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 190.dp, height = 125.dp)
                    .padding(start = 40.dp)
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
                        text = "Contato1",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Row {
                    Text(
                        "(99)9999-9999",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 190.dp, height = 125.dp)
                    .padding(start = 20.dp, end = 20.dp)
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
                        text = "Contato2",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Row {
                    Text(
                        "(99)9999-9999",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 190.dp, height = 125.dp)
                    .padding(start = 40.dp)
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
                        text = "Contato1",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Row {
                    Text(
                        "(99)9999-9999",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 190.dp, height = 125.dp)
                    .padding(start = 20.dp, end = 20.dp)
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
                        text = "Contato2",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Row {
                    Text(
                        "(99)9999-9999",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 190.dp, height = 125.dp)
                    .padding(start = 40.dp)
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
                        text = "Contato1",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Row {
                    Text(
                        "(99)9999-9999",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier
                    .size(width = 190.dp, height = 125.dp)
                    .padding(start = 20.dp, end = 20.dp)
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
                        text = "Contato2",
                        modifier = Modifier.padding(16.dp),
                        textAlign = TextAlign.Center,
                    )
                }
                Row {
                    Text(
                        "(99)9999-9999",
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

    }
}
@Preview
@Composable
fun RecentContactCard() {
    Column(modifier = Modifier.padding()) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(top = 5.dp, bottom = 5.dp, start = 22.dp)
                .border(shape = CircleShape, border = BorderStroke(5.dp, color = Color.LightGray))
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxSize()
                    .background(shape = CircleShape, color = Color.LightGray)
                    .padding(start = 5.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "",
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "NomeContato",
                        modifier = Modifier.padding(bottom = 15.dp, start = 15.dp, top = 10.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}