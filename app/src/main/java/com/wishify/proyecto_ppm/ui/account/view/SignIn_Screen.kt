package com.wishify.proyecto_ppm.ui.account.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.wishify.proyecto_ppm.R
import androidx.compose.ui.tooling.preview.Preview
import com.wishify.proyecto_ppm.ui.elements.AppBar
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SignIn() {

    Scaffold(
        //bottomBar = { AppBar() }
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()          // Ancho completo de la pantalla
                    .background(Color(0xFFfef0e1))
                    .padding(16.dp),         // Espaciado alrededor del contenido
                horizontalAlignment = Alignment.Start
            ) {
                // Botón "Back"
                Button(
                    onClick = { /* Acción aquí */ },
                    modifier = Modifier
                        .width(100.dp)         // Ancho fijo
                        .height(40.dp),        // Altura fija
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,  // Color de fondo del botón
                        contentColor = Color(0xFFb2422d)  // Color del texto del botón
                    )
                ) {
                    Text("< Back")
                }

            }

        }

    ) { paddingValues ->

        // Contenido de la pantalla, centrado verticalmente
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFb2422d))
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween // Acomoda los elementos con espacio entre ellos
        ) {
            Column(
                modifier = Modifier
                    .background(Color(0xFFfef0e1))
                    .fillMaxWidth(),

                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Welcome\n" + "\n" + "\n" +
                            "Back!",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 60.sp
                    ),
                    color = Color(0xFFb2422d),
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .padding(top = 8.dp),
                )
            }
            Spacer(modifier = Modifier.height(16.dp)) // Espaciado superior después del botón de regresar

            // Campos de texto para Username y Password
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Campo de Username
                //var text by remember { mutableStateOf("") }
                var text = "Mientras"

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "User Name",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp
                    ),
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 8.dp),

                    )

                TextField(
                    value = text,
                    onValueChange = { text = it },
                    //label = { Text("User Name") },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFC97763),         // Color de fondo
                        focusedTextColor = Color.White,           // Color del texto cuando tiene foco
                        unfocusedTextColor = Color.White,         // Color del texto cuando no tiene foco
                        focusedIndicatorColor = Color.White,       // Color del borde cuando tiene foco
                        unfocusedIndicatorColor = Color.White      // Color del borde cuando no tiene foco
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )


                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Password",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp
                    ),
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 8.dp),

                    )

                // Campo de Password
                var password_mintras = "Mientras"

                TextField(
                    value = password_mintras,
                    onValueChange = { text = it },
                    //label = { Text("Password") },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFC97763),         // Color de fondo
                        focusedTextColor = Color.White,           // Color del texto cuando tiene foco
                        unfocusedTextColor = Color.White,         // Color del texto cuando no tiene foco
                        focusedIndicatorColor = Color.White,       // Color del borde cuando tiene foco
                        unfocusedIndicatorColor = Color.White      // Color del borde cuando no tiene foco
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }

            // Botón de Sign Up
            Button(
                onClick = { /* Acción aquí */ },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 0.dp)
                    .width(300.dp)        // Ancho fijo
                    .height(50.dp),        // Altura fija
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFfef0e1),  // Color de fondo del botón
                    contentColor = Color(0xFFb2422d)           // Color del texto o contenido del botón
                )
            ) {
                Text("Sign In")
            }

            //parte de abajo digase nubes
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(0xFFb2422d))
            ){
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.nubes_invert),
                    contentDescription = "nubes",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.End)
                        .padding(vertical = 0.dp)
                )

            }
        }
    }
}



