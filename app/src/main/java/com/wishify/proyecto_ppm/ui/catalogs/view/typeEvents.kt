package com.wishify.proyecto_ppm.ui.catalogs.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment


@Composable
fun SelectEvent(text: String){
    Box(
        modifier = Modifier
            .background(Color(0xFFb2422d))
    ) {
        Text(
            text = text,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(8.dp)
        )
    }
}
