package com.wishify.proyecto_ppm.ui.wishLists.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.wishify.proyecto_ppm.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.ui.elements.AppBar
import com.wishify.proyecto_ppm.ui.elements.iconButtons
import com.wishify.proyecto_ppm.ui.elements.topNavBar

@Composable
fun ViewList(navController: NavController) {
    val listname = "List Name"
    val event = "Event"
    val codeList = 12345
    Scaffold(
        topBar = { topNavBar(navController = navController) },
        bottomBar = { AppBar(navController) }
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
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = listname,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 45.sp, fontWeight = FontWeight.Bold),
                    color = Color(0xFFb2422d)
                )
                Text(
                    text = event,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 45.sp, fontWeight = FontWeight.Bold),
                    color = Color(0xFFb2422d)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 26.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(text ="Code List: $codeList", modifier = Modifier
                        .background(Color.White)
                        .padding(16.dp) )
                    iconButtons(icon = Icons.Filled.AddCircle, texto = R.string.addBtn, onClick = { navController.navigate(NavigationState.Categories.route)})
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
                        ItemCard(nameItem = "Item: ${index +1}", imageItem = painterResource(id = R.drawable.img), icono = Icons.Filled.Delete, onClick = {})
                    }
                }
            }
        }
    }
}