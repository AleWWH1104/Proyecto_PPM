package com.wishify.proyecto_ppm.ui.guest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.ui.elements.AppBar
import com.wishify.proyecto_ppm.ui.elements.Banner
import com.wishify.proyecto_ppm.ui.elements.LargeButtons
import com.wishify.proyecto_ppm.ui.elements.topNavBar

@Preview
@Composable
fun AboutWish(navController: NavController){
    Scaffold(
        topBar = { topNavBar(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ){
            Banner(texto = R.string.aboutWish)
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
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
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "Descripcion", style = MaterialTheme.typography.titleMedium)
                Text(text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
                Spacer(modifier = Modifier.padding(16.dp))
                LargeButtons(texto = R.string.reserBtn, onClick = { }, buttonColor = Color.White, textColor = Color(0xFFb2422d) )
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