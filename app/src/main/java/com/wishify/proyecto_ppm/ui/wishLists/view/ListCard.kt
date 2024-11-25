package com.wishify.proyecto_ppm.ui.wishLists.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.ui.elements.smallButtons

@Composable
fun ListCard(
    nameList: String,
    event: String,
    codeList: String,
    imagenRes: Int,
    navController: NavController,
    onListChange: (String) -> Unit // Callback para actualizar las listas basado en codeList
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(180.dp)
            .height(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Image(
            painter = painterResource(id = imagenRes),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = nameList,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                text = event,
                style = MaterialTheme.typography.titleSmall,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = {
                    // Llama al callback para eliminar esta lista
                    onListChange(codeList)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Eliminar",
                        tint = Color(0xFFb2422d)
                    )
                }
                IconButton(
                    onClick = {
                        if (codeList.isNotEmpty()) {
                            navController.navigate("MyList/$codeList")
                        } else {
                            println("Error: codeList está vacío.")
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Info,
                        contentDescription = "Información",
                        tint = Color(0xFFb2422d)
                    )
                }
            }
        }
    }
}


@Composable
fun ListCardInfo(nameItem: String, navController: NavController){
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(180.dp)
            .height(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .weight(0.3f),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
                Text(text = nameItem)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            IconButton(onClick = {}) { //Accion pendiente
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "iTrash", tint = Color(0xFFb2422d))
            }
            IconButton(onClick = {navController.navigate(NavigationState.InfoItem.route)}) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "iInfo", tint = Color(0xFFb2422d))
            }
        }
        
    }
}