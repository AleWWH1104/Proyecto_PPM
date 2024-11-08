package com.wishify.proyecto_ppm.ui.wishLists.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale



@Composable
fun EventCard(text: String, image: Painter){
    Row(
        modifier = Modifier
            .background(Color.White)
            .width(180.dp)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = image,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f),
            contentScale = ContentScale.Crop
        )
        Text(
            text = text,
            color = Color.Black,
            modifier = Modifier
                .weight(0.6f)
                .padding(8.dp)
        )
    }
}