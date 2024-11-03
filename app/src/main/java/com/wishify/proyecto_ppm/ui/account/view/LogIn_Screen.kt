package com.wishify.proyecto_ppm.ui.account.view

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
import androidx.navigation.NavController
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.ui.elements.LargeButtons
@Composable
fun Beginning(navController: NavController) {
    Scaffold(

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
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )

                // Banner de regalo
                Image(
                    painter = painterResource(id = R.drawable.gift1),
                    contentDescription = "gift",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.5f)
                        .padding(vertical = 8.dp)
                )
            }

            // Parte de los botones
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp)
                    .background(Color(0xFFfef0e1)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(16.dp))
                LargeButtons(
                    texto = R.string.signIn,
                    onClick = { navController.navigate(NavigationState.SignIn.route) },
                    buttonColor = Color(0xFFb2422d),
                    textColor = Color.White
                )
                Spacer(modifier = Modifier.padding(16.dp))
                LargeButtons(
                    texto = R.string.signUp,
                    onClick = { navController.navigate(NavigationState.SignUp.route)},
                    buttonColor = Color.White,
                    textColor = Color(0xFFb2422d)
                )
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
