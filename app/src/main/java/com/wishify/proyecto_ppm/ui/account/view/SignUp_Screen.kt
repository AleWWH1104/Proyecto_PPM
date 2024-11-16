package com.wishify.proyecto_ppm.ui.account.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.wishify.proyecto_ppm.R
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.ui.elements.LargeButtons
import com.wishify.proyecto_ppm.ui.elements.smallTexField
import com.wishify.proyecto_ppm.ui.elements.smallTexFieldSignIn
import com.wishify.proyecto_ppm.ui.elements.smallTexFieldSignUp
import com.wishify.proyecto_ppm.ui.elements.topNavBar

@Composable
fun SignUpScreen(navController: NavController) {

    // Estados para Username y Password
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Scaffold(
        topBar = { topNavBar(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFb2422d))
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(Color(0xFFfef0e1)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.createAccount),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 45.sp,
                        fontWeight = FontWeight.Bold
                    ), color = Color(0xFFb2422d),
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.8f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Username",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                    color = Color.White
                )
                smallTexFieldSignUp(
                    text = username.value,
                    onTextChange = { username.value = it } // Actualiza el estado
                ) // Campo de user
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = "Password",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                    color = Color.White
                )
                smallTexFieldSignUp(
                    text = password.value,
                    onTextChange = { password.value = it } // Actualiza el estado
                )  // Campo de contraseña
                Spacer(modifier = Modifier.padding(16.dp))
                LargeButtons(
                    texto = R.string.signUp,
                    onClick = { navController.navigate(NavigationState.AllLists.route) },
                    buttonColor = Color(0xFFfef0e1),
                    textColor = Color(0xFFb2422d)
                )
            }
            Image(
                painter = painterResource(id = R.drawable.nubes_invert),
                contentDescription = "nubes",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}



