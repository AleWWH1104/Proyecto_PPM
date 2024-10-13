package com.wishify.proyecto_ppm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wishify.proyecto_ppm.ui.theme.AppTheme
import com.wishify.proyecto_ppm.ui.account.view.LogIn
import com.wishify.proyecto_ppm.ui.account.view.SignIn
import com.wishify.proyecto_ppm.ui.account.view.SignUp
import com.wishify.proyecto_ppm.ui.account.view.UserAccount

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                GreetingPreview()
            }
        }
    }
}

@Composable
fun GreetingPreview() {
    // Variable de estado para rastrear qué pantalla mostrar
    var currentScreen by remember { mutableStateOf("menu") }

    // Manejador de acción para cuando se presiona el botón de regresar
    BackHandler(enabled = currentScreen != "menu") {
        currentScreen = "menu" // Regresa al menú principal
    }

    when (currentScreen) {
        "menu" -> MainMenu(onButtonClick = { selectedScreen ->
            currentScreen = selectedScreen
        })
        "login" -> LogIn()
        "signin" -> SignIn()
        "signup" -> SignUp()
        "useraccount" -> UserAccount()
    }
}

@Composable
fun MainMenu(onButtonClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { onButtonClick("login") }) {
            Text("Go to Log In")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onButtonClick("signin") }) {
            Text("Go to Sign In")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onButtonClick("signup") }) {
            Text("Go to Sign Up")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onButtonClick("useraccount") }) {
            Text("Go to User Account")
        }
    }
}
