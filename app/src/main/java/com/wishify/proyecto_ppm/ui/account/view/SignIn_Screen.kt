package com.wishify.proyecto_ppm.ui.account.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.ui.elements.LargeButtons
import com.wishify.proyecto_ppm.ui.elements.smallTexField
import com.wishify.proyecto_ppm.ui.elements.smallTexFieldSignIn
import com.wishify.proyecto_ppm.ui.elements.topNavBar

@Composable
fun SignInScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val context = LocalContext.current // Obtén el contexto para usar con Toast
    println("al iniciar Email: ${email.value}, Password: ${password.value}")


    fun tryLogIn() {
        println("tryLogIn called")
        println(" en trylogin Email: ${email.value}, Password: ${password.value}")

        try {
            auth.signInWithEmailAndPassword(email.value, password.value)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        println("Login successful")
                        navController.navigate(NavigationState.AllLists.route)
                    } else {
                        println("Sign-in failed: ${task.exception?.localizedMessage}")
                        Toast.makeText(
                            context,
                            "Sign-in failed: ${task.exception?.localizedMessage ?: "Unknown error"}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } catch (e: Exception) {
            println("Exception during login: ${e.localizedMessage}")
            Toast.makeText(
                context,
                "An error occurred: ${e.localizedMessage ?: "Unknown error"}",
                Toast.LENGTH_SHORT
            ).show()
        }
    }


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
                    text = stringResource(id = R.string.welcome),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 35.sp,
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
                    text = "Email",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                    color = Color.White
                )
                smallTexFieldSignIn(
                    text = email.value,
                    onTextChange = { email.value = it } // Actualiza el estado
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = "Password",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                    color = Color.White
                )
                smallTexFieldSignIn(
                    text = password.value,
                    onTextChange = { password.value = it } // Actualiza el estado
                )
                Spacer(modifier = Modifier.padding(16.dp))
                LargeButtons(
                    texto = R.string.signIn,

                    onClick = {
                        println(" en el boton Email: ${email.value}, Password: ${password.value}")

                        tryLogIn()
                              },
                    buttonColor = Color(0xFFfef0e1),
                    textColor = Color(0xFFb2422d),
                    enabled = email.value.isNotEmpty() && password.value.isNotEmpty()
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





