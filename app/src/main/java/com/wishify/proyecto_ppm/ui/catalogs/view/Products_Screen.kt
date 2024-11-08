package com.wishify.proyecto_ppm.ui.catalogs.view

import androidx.compose.ui.Alignment
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.wishify.proyecto_ppm.ui.elements.AppBar
import com.wishify.proyecto_ppm.ui.elements.topNavBar

@Composable
fun ProductsByCategory(category: String, navController: NavController){
    Scaffold(
        topBar = { topNavBar(navController = navController) },
        bottomBar = { AppBar(navController) }
    ){paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFfef0e1))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(
                    text = category,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 35.sp, fontWeight = FontWeight.Bold),
                    color = Color(0xFFb2422d),
                    textAlign = TextAlign.Center
                )
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.padding(paddingValues)
                ){
                    items(4) {index ->
                        ProductCard(navController)
                    }
                }
            }

        }
    }
}


