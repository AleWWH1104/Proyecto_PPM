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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.wishify.proyecto_ppm.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wishify.proyecto_ppm.navigation.NavigationState

import com.wishify.proyecto_ppm.ui.elements.AppBar
import com.wishify.proyecto_ppm.ui.elements.LargeButtons
import com.wishify.proyecto_ppm.ui.elements.topNavBar

@Composable
fun UserAccount(navController: NavController){
    Scaffold(
        topBar = { topNavBar(navController = navController) },
        bottomBar = { AppBar(navController) }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFb2422d))
            ){
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Image(
                    painter = painterResource(id = R.drawable.user_pic),
                    contentDescription = "user pic",
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(300.dp)
                        .align(Alignment.End)
                        .padding(vertical = 0.dp)
                )
                Text(
                    text = "Username\n",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 20.sp
                    ),
                    color = Color(0xFFb2422d),
                    modifier = Modifier
                        .padding(top = 8.dp),
                )
                Spacer(modifier = Modifier.height(16.dp))
                LargeButtons(
                    texto = R.string.signOut,
                    onClick = { navController.navigate(NavigationState.Home.route) },
                    buttonColor = Color(0xFFb2422d),
                    textColor = Color(0xFFfef0e1)
                )
                Spacer(modifier = Modifier.height(16.dp))
                LargeButtons(
                    texto = R.string.DeleteAccount,
                    onClick = { navController.navigate(NavigationState.Home.route) },
                    buttonColor = Color(0xFFb2422d),
                    textColor = Color(0xFFfef0e1)
                )
            }
        }
    }
}
