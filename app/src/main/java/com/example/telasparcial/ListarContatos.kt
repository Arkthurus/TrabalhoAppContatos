package com.example.telasparcial

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.OverscrollEffect
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.overscroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.telasparcial.components.UserCard
import com.example.telasparcial.ui.theme.TelasParcialTheme

class ListarContatos : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TelasParcialTheme {
                Scaffold {
                    var context = LocalContext.current
                    Column {
                        ListarContatosTela(modifier = Modifier.padding(it))

                        BottomBar2(context)
                    }
                }

            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun BottomBar2(context : Context) {
    Scaffold {
        Surface(modifier = Modifier.padding(bottom = 0.dp, top = 5.dp).
        height(80.dp).
        fillMaxWidth(),
            color = Color(MaterialTheme.colorScheme.surfaceVariant.value)

        ) {
            Row {
                Spacer(modifier = Modifier.width(55.dp))
                BottomButtonEsquerda(context)
                Spacer(modifier = Modifier.width(55.dp))
                BottomButtonCentral(context)
                Spacer(modifier = Modifier.width(55.dp))
                BottomButtonDireita(context)

            }
        }
    }
}



@Composable
@Preview
fun ListarContatosTela(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.scrollable(
            orientation = Orientation.Vertical, reverseDirection = true,
            state = ScrollableState { it },
        )
    ) {

        UserCard()
        UserCard()
        UserCard()
        UserCard()
        UserCard()
        UserCard()
        UserCard()
        UserCard()
        UserCard()
        UserCard()
        UserCard()
        UserCard()
        UserCard()
    }
}
