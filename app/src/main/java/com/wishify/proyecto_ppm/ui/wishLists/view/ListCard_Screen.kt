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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.ui.elements.smallButtons

@Composable
fun ListCard(nameList: String, event: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(180.dp)
            .height(200.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.img),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f),
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Column() {
                Text(
                    text = nameList,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = event,
                    style = MaterialTheme.typography.titleSmall,
                )
            }
            smallButtons(texto = R.string.seeBtn)
        }
    }
}

@Composable
fun ListCardInfo(nameItem: String){
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
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "iTrash", tint = Color(0xFFb2422d))
            }
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Filled.Info, contentDescription = "iTrash", tint = Color(0xFFb2422d))
            }
        }
        
    }
}