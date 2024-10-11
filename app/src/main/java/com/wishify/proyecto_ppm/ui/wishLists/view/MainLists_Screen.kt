package com.wishify.proyecto_ppm.ui.wishLists.view

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
import com.wishify.proyecto_ppm.ui.elements.AppBar

@Preview(showBackground = true)
@Composable
fun MainLists() {
    val listData = listOf(
        Pair("Lista 1", "Evento 1"),
        Pair("Lista 2", "Evento 2"),
        Pair("Lista 3", "Evento 3"),
        Pair("Lista 4", "Evento 4") ,
        Pair("Lista 1", "Evento 1"),
        Pair("Lista 2", "Evento 2"),
        Pair("Lista 3", "Evento 3"),
        Pair("Lista 4", "Evento 4")
    )

    Scaffold(
        bottomBar = { AppBar() }
    ) { paddingValues ->
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
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = "Hello, Username!",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.slogan),
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.White
                        )
                    }
                    Image(
                        painter = painterResource(id = R.drawable.gift_banner),
                        contentDescription = "gift",
                        modifier = Modifier
                            .size(130.dp)
                    )
                }
            }

            // Lista de tarjetas (usando LazyVerticalGrid)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),  // Espaciado externo
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(listData) { item ->
                    ListCard(nameList = item.first, event = item.second)
                }
            }
        }
    }
}
@Composable
fun ListCard(nameList: String, event: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(180.dp)
            .height(200.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.gift_banner),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(9.dp)
            ) {
                Text(
                    text = nameList,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = event,
                    style = MaterialTheme.typography.titleSmall,
                )
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(Color(0xFFb2422d)),
                    modifier = Modifier
                        .align(Alignment.End),
                    contentPadding = PaddingValues(0.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = stringResource(id = R.string.seeBtn), color= Color.White)
                }
            }
        }
    }
}


