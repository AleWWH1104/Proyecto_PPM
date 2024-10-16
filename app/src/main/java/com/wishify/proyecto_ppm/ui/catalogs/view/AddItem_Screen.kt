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
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.ui.elements.AppBar
import com.wishify.proyecto_ppm.ui.elements.Banner
import com.wishify.proyecto_ppm.ui.elements.LargeButtons
import com.wishify.proyecto_ppm.ui.elements.LargeTextField

@Preview
@Composable
fun AddItem() {
    Scaffold(
        bottomBar = { AppBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ) {
            // Banner
            Banner(texto = R.string.describeWish, painterResource(id = R.drawable.gift2))

            // Contenido desplazable
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
                            .padding(8.dp)
                    )
                    Column {
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
                LargeButtons(texto = R.string.addBtn)
            }
        }
    }
}
