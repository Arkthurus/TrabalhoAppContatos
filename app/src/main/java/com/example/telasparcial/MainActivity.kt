package com.example.telasparcial

import android.R
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.util.Log
import android.view.Surface
import android.widget.Button
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
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
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.telasparcial.ui.theme.TelasParcialTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TelasParcialTheme {
                Scaffold {
                    var context = LocalContext.current
                    Column(modifier = Modifier.padding(it)) {
                        SearchBar()
                        Spacer(modifier = Modifier.height(5.dp))
                        FavoriteContacts()
                        Spacer(modifier = Modifier.height(10.dp))
                        RecentContactsList()
                        ContactsCards()
                        BottomBar(context)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar(context: Context) {

    Scaffold(
        bottomBar = {
            Surface(
                modifier = Modifier
                    .height(80.dp)
                    .fillMaxWidth(),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    BottomButtonEsquerda(context)
                    BottomButtonCentral(context)
                    BottomButtonDireita(context)
                }
            }
        },
        content = { innerPadding ->
            // Aqui vai o conteúdo principal da tela
            // Você pode usar innerPadding para evitar sobreposição
            Box(modifier = Modifier.padding(innerPadding)) {
                // Conteúdo da tela
            }
        }
    )
}


@Composable
fun BottomButtonCentral(context: Context) {

    Column {
        Button(
            modifier = Modifier
                .width(70.dp)
                .height(80.dp)
                .padding(
                    top = 15.dp,
                    end = 5.dp,
                    bottom = 10.dp
                ),
            shape = ButtonDefaults.filledTonalShape,
            onClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "",
                modifier = Modifier.size(70.dp)
            )
        }
    }
}

@Composable
fun BottomButtonEsquerda(context: Context) {

    Column {
        Button(
            modifier = Modifier
                .width(70.dp)
                .height(80.dp)
                .padding(
                    top = 15.dp,
                    end = 5.dp,
                    bottom = 10.dp
                ),
            shape = ButtonDefaults.filledTonalShape,
            onClick = {
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
            }) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "",
                modifier = Modifier.size(70.dp)
            )
        }
    }
}

@Composable
fun BottomButtonDireita(context : Context) {

    Column {
        Button(
            modifier = Modifier
                .width(70.dp)
                .height(80.dp)
                .padding(
                    top = 15.dp,
                    end = 5.dp,
                    bottom = 10.dp
                ),
            shape = ButtonDefaults.filledTonalShape,
            onClick = {
                val intent = Intent(context, ListarContatos::class.java)
                context.startActivity(intent)
            }) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "",
                modifier = Modifier.size(70.dp)
            )
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
            .padding(
                start = 22.dp,
                end = 20.dp
            )
    ) {
        Column {
            Row { Text(
                text = "Favoritos",
                modifier = Modifier
                    .padding(16.dp),
                textAlign = TextAlign.Center,
            )}
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
        modifier = Modifier
            .size(width = 400.dp, height = 300.dp)
    ){
        Column {
           Text(
                text = "Recentes",
                modifier = Modifier
                    .padding(start = 35.dp),
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
                        modifier = Modifier
                            .padding(16.dp),
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
                        modifier = Modifier
                            .padding(16.dp),
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
    }
}

@Preview
@Composable
fun Button() {

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Column {
//            Text("Contador: $contador")
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 15.dp,
                        end = 5.dp
                    ),
                shape = ButtonDefaults.filledTonalShape,
                onClick = {
                    Log.d("Eae Delicia", "Vc se Cadastrou")
                }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier.size(40.dp)
                )
            }
        }

    }
}

@Preview
@Composable
fun SearchBar() {

    var contactName by remember {
        mutableStateOf("Pesquisar")
    }

    Surface(
        modifier = Modifier
            .width(600.dp)
            .height(100.dp)
            .padding(
                top = 10.dp,
                bottom = 10.dp,
                start = 10.dp,
                end = 10.dp
            )
    ) {
        Row() {
            TextField(

                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        bottom = 10.dp,
                        start = 15.dp,
                        end = 5.dp
                    ),
                value = contactName,
                onValueChange = {
                    contactName = it
                }
            )
            Button()
        }
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun RecentContactCard() {

        Column(modifier = Modifier.padding()) {

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(
                        top = 5.dp,
                        bottom = 5.dp,
                        start = 22.dp

                    )
                    .border(
                        shape = CircleShape,
                        border = BorderStroke(5.dp, color = Color.LightGray),

                        )
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .width(300.dp)
                        .height(5.dp)
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