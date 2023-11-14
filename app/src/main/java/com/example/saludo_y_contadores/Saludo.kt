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
import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//inicializamos variable contador de botones
var contAcep = 0
var contCanc = 0

/**
 * Funcion que nos representa nuestro dialogo
 * @param saludo Funcion lambda en el que pillamos el string escrito en nuestro dialog,
 * y modifica la variable de nuestra funcion de lanzamiento principal
 */
@Composable
fun Dialog(saludo: (String) -> Unit) {
    var nombreSaludo by rememberSaveable { mutableStateOf("") }

    androidx.compose.ui.window.Dialog(onDismissRequest = { }) {
        //Card que define el diseño de nuestro dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(0.dp, 15.dp, 0.dp, 20.dp),
            content = {
                Column(
                    Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
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
                        //Boton en donde al hacer click, utilizamos la variable escrita en nuestro dialog
                        Button(onClick = {
                            saludo("Hola, $nombreSaludo")
                            contAcep += 1
                        }) {
                            Text(text = "A$contAcep")
                        }
                        //Boton que nos limpia el contenido escrito
                        Button(onClick = { nombreSaludo = "" }) {
                            Text(text = "L")
                        }
                        //Boton que cancela la operacion y vuelve al inicio
                        Button(onClick = {
                            saludo("")
                            contCanc += 1
                        }) {
                            Text(text = "C$contCanc")
                        }
                    }
                }
            }
        )

    }

}

/**
 * Funcion principal que realiza el saludo
 */
@Preview
@Composable
fun Saludo() {

    val btnText by rememberSaveable { mutableStateOf("Saludar") }
    var textFieldText by rememberSaveable { mutableStateOf("") }
    var openDialog by rememberSaveable { mutableStateOf(false) }

    Column(
        Modifier.fillMaxSize(),
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
        //Condicional en el usamos nuestra funcion lambda, siempre que se abra el dialog
        if (openDialog) {
            Dialog { newValue ->
                textFieldText = newValue
                openDialog = false
            }
        }
    }
}