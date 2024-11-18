package com.wishify.proyecto_ppm.ui.catalogs.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.ui.elements.AppBar
import com.wishify.proyecto_ppm.ui.elements.Banner
import com.wishify.proyecto_ppm.ui.elements.LargeButtons
import com.wishify.proyecto_ppm.ui.elements.LargeTextField
import com.wishify.proyecto_ppm.ui.elements.topNavBar

@Preview
@Composable
fun AddItem(navController: NavController= rememberNavController()) {

    println("Esta en AddItemscreen")

    Scaffold(
        topBar = { topNavBar(navController = navController) },
        bottomBar = { AppBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ) {
            Banner(texto = R.string.describeWish)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = "products",
                        modifier = Modifier
                            .width(180.dp)
                            .weight(0.5f)
                            .padding(16.dp)
                    )
                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(start= 16.dp)
                    ) {
                        Text(
                            text = "Product 1",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = "Categoria",
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    "Agrega una descripcion mas especifica para tu deseo (colores/tamaño/marca/etc).\n\nEj. Unas botas largas de color negro de Zara ...",
                    textAlign = TextAlign.Center
                )
                LargeTextField()
                Spacer(modifier = Modifier.padding(8.dp))
                LargeButtons(texto = R.string.addBtn, onClick = { navController.navigate(NavigationState.MyList.route) }, buttonColor = Color(0xFFb2422d), textColor =Color(0xFFfef0e1) )
            }
        }
    }
}
