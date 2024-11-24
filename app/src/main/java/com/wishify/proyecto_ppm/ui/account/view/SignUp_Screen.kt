package com.wishify.proyecto_ppm.ui.account.view

import android.widget.Toast
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.ui.elements.LargeButtons
import com.wishify.proyecto_ppm.ui.elements.smallTexField
import com.wishify.proyecto_ppm.ui.elements.smallTexFieldSignUp
import com.wishify.proyecto_ppm.ui.elements.topNavBar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun SignUpScreen(navController: NavController) {
    // Estados para Email y Password
    val auth = FirebaseAuth.getInstance()
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isLoading = remember { mutableStateOf(false) } // Para manejar estado de carga
    println(" al iniciar sign up Email: ${email.value}, Password: ${password.value}")

    // funcion para agregar datos de UID y CodeList a db
    fun addUID(uid: String) {
        val db = FirebaseFirestore.getInstance()

        // Datos iniciales del documento
        val userData = hashMapOf(
            "UID" to uid,
            "CodeList" to listOf("0") // Inicializa CodeList con "0" y el UID
        )

        // Agregar datos en la colección UsuariosP -> Subcolección UsuarioP
        db.collection("UsuariosP").document("UsuarioP")
            .collection("UsuarioP").document(uid)
            .set(userData)
            .addOnSuccessListener {
                println("Usuario agregado exitosamente en Firestore.")
            }
            .addOnFailureListener { e ->
                println("Error al agregar usuario: ${e.message}")
            }
    }


    // funcion para crear el susuario
    fun trySignUp() {
        println(" en trysignup Email: ${email.value}, Password: ${password.value}")
        if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
            isLoading.value = true // Indicar que está cargando
            println(" cuando se deberia craer Email: ${email.value}, Password: ${password.value}")
            auth.createUserWithEmailAndPassword(email.value, password.value)
                .addOnCompleteListener { task ->
                    isLoading.value = false // Resetear estado de carga
                    if (task.isSuccessful) {
                        val currentUser = auth.currentUser
                        val uid = currentUser?.uid ?: return@addOnCompleteListener

                        // Llamar a addUID para agregar el UID a Firestore
                        addUID(uid)

                        Toast.makeText(
                            navController.context,
                            "Account created successfully!",
                            Toast.LENGTH_SHORT
                        ).show()

                        navController.navigate(NavigationState.AllLists.route)
                    } else {
                        // Error durante el registro
                        println(" No se rsgistro Email: ${email.value}, Password: ${password.value}")
                        Toast.makeText(
                            navController.context,
                            "Sign-up failed: ${task.exception?.localizedMessage ?: "Unknown error"}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        } else {
            Toast.makeText(
                navController.context,
                "Please fill in all fields.",
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
                    text = stringResource(id = R.string.createAccount),
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
                smallTexFieldSignUp(
                    text = email.value,
                    onTextChange = { email.value = it } // Actualiza el estado
                ) // Campo de correo
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
                    onClick = { trySignUp() },
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



