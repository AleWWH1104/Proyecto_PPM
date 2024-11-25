package com.wishify.proyecto_ppm.ui.account.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.ui.elements.LargeButtons
import com.wishify.proyecto_ppm.ui.elements.smallTexField
import com.wishify.proyecto_ppm.ui.elements.topNavBar
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.wishify.proyecto_ppm.ui.elements.smallTexFieldSignIn

import com.wishify.proyecto_ppm.ui.account.repository.SignInRepository
import com.wishify.proyecto_ppm.ui.account.viewmodel.SignInViewModel

@Composable
fun SignInScreen(navController: NavController) {
    // Manual Dependency Injection
    val auth = FirebaseAuth.getInstance()
    val repository = remember { SignInRepository(auth) }
    val viewModel = remember { SignInViewModel(repository) }

    val context = LocalContext.current

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
                    text = viewModel.email.value,
                    onTextChange = { viewModel.email.value = it }
                )
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = "Password",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
                    color = Color.White
                )
                smallTexFieldSignIn(
                    text = viewModel.password.value,
                    onTextChange = { viewModel.password.value = it }
                )
                Spacer(modifier = Modifier.padding(16.dp))
                LargeButtons(
                    texto = R.string.signIn,
                    onClick = {
                        viewModel.tryLogIn(
                            onSuccess = { navController.navigate(NavigationState.AllLists.route) },
                            onError = { error ->
                                Toast.makeText(
                                    context,
                                    "Sign-in failed: $error",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                    },
                    buttonColor = Color(0xFFfef0e1),
                    textColor = Color(0xFFb2422d),
                    enabled = viewModel.email.value.isNotEmpty() && viewModel.password.value.isNotEmpty()
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
