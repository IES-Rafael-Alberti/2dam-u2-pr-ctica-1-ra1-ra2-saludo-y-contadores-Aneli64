package com.example.saludo_y_contadores

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//@Preview
@Composable
fun Dialog(saludo: (String) -> Unit) {
    //lanzaDialog: Boolean
    var nombreSaludo by rememberSaveable { mutableStateOf("") }

        androidx.compose.ui.window.Dialog(onDismissRequest = {  }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(16.dp),
                content = {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Text(text = "Configuración", fontSize = 20.sp)
                        }
                        Row {
                            TextField(
                                value = nombreSaludo,
                                onValueChange = {
                                    nombreSaludo = it
                                },
                                label = { Text("Introduce tu nombre") }
                            )
                        }
                        Row {
                            Button(onClick = { saludo(nombreSaludo) }) {
                                Text(text = "Acep")
                            }
                            Button(onClick = { /*TODO*/ }) {
                                Text(text = "Limp")
                            }
                            Button(onClick = { /*TODO*/ }) {
                                Text(text = "Canc")
                            }
                        }
                    }
                }
            )

        }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Saludo() {

    val btnText by rememberSaveable { mutableStateOf("Saludar") }
    var textFieldText by rememberSaveable { mutableStateOf("") }
    var openDialog by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Button(onClick = { openDialog = true }) {
                Text(btnText)
            }
        }
        Row {
            TextField(
                value = textFieldText,
                onValueChange = {
                    textFieldText = it
                },
                label = { Text("") }
            )
        }
        if (openDialog) {
            Dialog { newValue ->
                textFieldText = newValue
                openDialog = false
            }
        }
    }
}