package com.wishify.proyecto_ppm.ui.wishLists.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.wishify.proyecto_ppm.R
import androidx.navigation.NavController
import com.wishify.proyecto_ppm.ui.elements.AppBar
import com.wishify.proyecto_ppm.ui.elements.Banner


@Composable
fun MainLists(navController: NavController){
    Scaffold(
        bottomBar = { AppBar(navController) }
    ){paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ){
            Banner(texto = R.string.slogan, painterResource(id = R.drawable.gift2), navController)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(6) { index ->
                    ListCard(
                        nameList = "List Name ${index + 1}",
                        event = "Event ${index + 1}",
                        navController = navController
                    )
                }
            }
        }
    }
}



