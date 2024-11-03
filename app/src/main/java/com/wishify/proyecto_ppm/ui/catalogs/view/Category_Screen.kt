package com.wishify.proyecto_ppm.ui.catalogs.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.ui.elements.AppBar
import com.wishify.proyecto_ppm.ui.elements.Banner

@Composable
fun Categories(navController: NavController) {
    Scaffold(
        bottomBar = { AppBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ) {
            Banner(texto = R.string.lookItem, painterResource(id = R.drawable.gift2), navController)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(id = R.string.typesProductos))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(12) { index ->
                        SelectCategories(
                            text = "Category ${index + 1}",
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
