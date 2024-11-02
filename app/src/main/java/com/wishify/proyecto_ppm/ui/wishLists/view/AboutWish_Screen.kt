package com.wishify.proyecto_ppm.ui.wishLists.view

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
import androidx.navigation.compose.rememberNavController
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.ui.elements.AppBar
import com.wishify.proyecto_ppm.ui.elements.Banner
import com.wishify.proyecto_ppm.ui.elements.LargeButtons

@Preview
@Composable
fun AboutWish(){
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { AppBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ){
            Banner(texto = R.string.aboutWish, painterResource(id = R.drawable.gift1))
            Column (
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
                ){
                    Image(
                        painter = painterResource(id = R.drawable.img),
                        contentDescription = "products",
                        modifier = Modifier
                            .width(180.dp)
                            .padding(8.dp)
                    )
                    Column{
                        Text(text= "Product 1", style= MaterialTheme.typography.titleMedium)
                        Text(text= "Categoria", style= MaterialTheme.typography.titleSmall)
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "Descripcion", style = MaterialTheme.typography.titleMedium)
                Text(text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry.")
                Spacer(modifier = Modifier.padding(16.dp))
//                LargeButtons(texto = R.string.reserBtn, {})
            }
        }
    }
}