package com.wishify.proyecto_ppm.ui.account.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.wishify.proyecto_ppm.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Preview //(showBackground = true)
@Composable
fun LogIn() {

    Scaffold(
        //bottomBar = { AppBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ) {
            // Parte superior con logo y banner
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.8f)  // Distribuye el espacio restante de manera proporcional
                    .background(Color(0xFFb2422d)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logo
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .fillMaxWidth(1f)  // 60% del ancho disponible
                        .padding(vertical = 16.dp)
                        //.aspectRatio(1f)  // Mantiene proporción cuadrada
                )

                // Banner de regalo
                Image(
                    painter = painterResource(id = R.drawable.gift2),
                    contentDescription = "gift",
                    modifier = Modifier
                        .fillMaxWidth(1f)  // 80% del ancho disponible
                        .aspectRatio(0.5f)  // Ajusta la relación de aspecto 2:1
                        .padding(vertical = 8.dp)
                )
            }

            // Parte de los botones
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)  // Espacio proporcional para los botones
                    .background(Color(0xFFfef0e1)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = { /* Acción aquí */ },
                    modifier = Modifier
                        .fillMaxWidth(0.7f)  // 70% del ancho disponible
                        .padding(vertical = 16.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFb2422d),
                        contentColor = Color.White
                    )
                ) {
                    Text("Sign In")
                }

                Button(
                    onClick = { /* Acción aquí */ },
                    border = BorderStroke(2.dp, Color(0xFFb2422d)),
                    modifier = Modifier
                        .fillMaxWidth(0.7f)  // 70% del ancho disponible
                        .padding(vertical = 8.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFFb2422d)
                    )
                ) {
                    Text("Sign Up")
                }
            }

            // Parte inferior (nubes)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.2f)  // Espacio proporcional para la imagen de nubes
                    .background(Color(0xFFfef0e1)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.nubes),
                    contentDescription = "nubes",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(4f)  // Ajusta la proporción para las nubes
                )
            }
        }
    }
}
