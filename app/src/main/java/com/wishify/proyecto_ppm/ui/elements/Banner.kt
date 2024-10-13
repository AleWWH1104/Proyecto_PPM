package com.wishify.proyecto_ppm.ui.elements

import androidx.annotation.StringRes
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.wishify.proyecto_ppm.R


@Composable
fun Banner(@StringRes texto: Int, imagen: Painter) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp) // Mantener la altura fija
            .background(Color(0xFFb2422d))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
        ) {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = texto),
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(0.5f),
            )
            Image(
                painter = imagen,
                contentDescription = "img",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f)
            )
        }
    }
}

