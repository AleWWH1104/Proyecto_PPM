package com.wishify.proyecto_ppm.ui.guest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.ui.elements.topNavBar
import com.wishify.proyecto_ppm.ui.wishLists.view.ItemCard

@Composable
fun ViewGuestList(navController: NavController) {
    val listname = "List Name"
    val event = "Event"
    Scaffold(
        topBar = { topNavBar(navController = navController)}
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFb2422d))
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
                Text(
                    text = "User:",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color(0xFFfef0e1),
                )
                Text(
                    text = "ListName:${listname}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color(0xFFfef0e1),
                )
                Text(
                    text = "Event:${event}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color(0xFFfef0e1),
                )
            }
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
                    ItemCard(
                        nameItem = "Item: ${index + 1}",
                        imageItem = painterResource(id = R.drawable.img),
                        icono = Icons.Filled.Info,
                        onClick = {})
                }
            }
        }
    }
}