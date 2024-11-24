package com.wishify.proyecto_ppm.ui.account.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.text.ClickableText
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import com.wishify.proyecto_ppm.R
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.ui.elements.LargeButtons

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold() { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1.8f)
                    .background(Color(0xFFb2422d)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.gift1),
                    contentDescription = "gift",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.5f)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.padding(16.dp))
                LargeButtons(
                    texto = R.string.signIn,
                    onClick = { navController.navigate(NavigationState.SignIn.route) },
                    buttonColor = Color(0xFFb2422d),
                    textColor = Color.White,
                    enabled = true
                )
                Spacer(modifier = Modifier.padding(8.dp))
                LargeButtons(
                    texto = R.string.signUp,
                    onClick = { navController.navigate(NavigationState.SignUp.route)},
                    buttonColor = Color.White,
                    textColor = Color(0xFFb2422d),
                    enabled = true
                )
                Spacer(modifier = Modifier.padding(16.dp))
                ClickableText(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = Color(0xFFb2422d),
                                fontStyle = FontStyle.Italic,
                                textDecoration = TextDecoration.Underline
                            )
                        ) {
                            append(stringResource(id = R.string.guest))
                        }
                    },
                    style = MaterialTheme.typography.titleSmall,
                    onClick = {navController.navigate(NavigationState.Guest.route)}
                )
            }
            Image(
                painter = painterResource(id = R.drawable.nubes),
                contentDescription = "nubes",
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}
