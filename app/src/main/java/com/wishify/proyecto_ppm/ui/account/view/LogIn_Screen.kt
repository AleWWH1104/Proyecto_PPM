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

@Preview(showBackground = true)
@Composable
fun LogIn() {

    Scaffold(
        //bottomBar = { AppBar() }
    ) { paddingValues ->
        // parte de arriba
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .background(Color(0xFFb2422d))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.gift_banner),
                    contentDescription = "gift",
                    modifier = Modifier
                        .size(700.dp)
                        .fillMaxWidth()
                        //.padding(vertical = 1.dp)
                )

            }

            // parte de los botones
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(370.dp)
                    .background(Color(0xFFfef0e1))
                    .padding(paddingValues)
            ){
                Button(
                    onClick = { /* Acción aquí */ },
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 32.dp)
                        .width(300.dp)        // Ancho fijo
                        .height(50.dp),        // Altura fija

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFb2422d),  // Color de fondo del botón
                        contentColor = Color.White           // Color del texto o contenido del botón
                    )
                ) {
                    Text("Sign In")
                }


                Button(
                    onClick = { /* Acción aquí */ },
                    border = BorderStroke(3.dp, Color(0xFFb2422d)),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 0.dp)
                        .width(300.dp)        // Ancho fijo
                        .height(50.dp),        // Altura fija
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,  // Color de fondo del botón
                        contentColor = Color(0xFFb2422d)           // Color del texto o contenido del botón
                    )
                ) {
                    Text("Sign Up")
                }



            }

            //parte de abajo digase nubes
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color(0xFFfef0e1))
            ){
                Image(
                    painter = painterResource(id = R.drawable.nubes),
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



