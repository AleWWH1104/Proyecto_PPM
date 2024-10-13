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
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.wishify.proyecto_ppm.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SignIn() {

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFfef0e1))
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Botón "Back" sin ancho fijo
                Button(
                    onClick = { /* Acción aquí */ },
                    modifier = Modifier
                        .fillMaxWidth(0.3f) // Ocupa el 30% del ancho
                        .height(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFb2422d)
                    )
                ) {
                    Text("< Back")
                }
            }
        }

    ) { paddingValues ->

        // Contenido principal, centrado verticalmente
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFb2422d))
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Sección superior con el texto "Welcome Back"
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFfef0e1)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Welcome\n\nBack!",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 60.sp),
                    color = Color(0xFFb2422d),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }

            // Campos de texto
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "User Name",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                    color = Color.White
                )

                TextField(
                    value = "Mientras",
                    onValueChange = { /* Acción aquí */ },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFC97763),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.85f) // 85% del ancho
                        .padding(vertical = 16.dp)
                )

                Text(
                    text = "Password",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                    color = Color.White
                )

                TextField(
                    value = "Mientras",
                    onValueChange = { /* Acción aquí */ },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFC97763),
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White
                    ),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.85f) // 85% del ancho
                        .padding(vertical = 16.dp)
                )
            }

            // Botón "Sign In" sin ancho fijo
            Button(
                onClick = { /* Acción aquí */ },
                modifier = Modifier
                    .fillMaxWidth(0.7f) // Ocupa el 70% del ancho
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFfef0e1),
                    contentColor = Color(0xFFb2422d)
                )
            ) {
                Text("Sign In")
            }

            // Imagen inferior
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(0xFFb2422d))
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painter = painterResource(id = R.drawable.nubes_invert),
                    contentDescription = "nubes",
                    contentScale = ContentScale.FillBounds,  // Ajusta la imagen al ancho y alto del contenedor
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()  // Asegura que la imagen llene el contenedor en ambas dimensiones
                        .align(Alignment.CenterHorizontally)
                )
            }

        }
    }
}
