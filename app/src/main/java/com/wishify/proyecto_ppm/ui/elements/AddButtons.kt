package com.wishify.proyecto_ppm.ui.elements

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource

@Composable
fun Botones(@StringRes texto: Int){
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(Color(0xFFfef0e1)),
        modifier = Modifier
            .fillMaxWidth(0.8f)
    ) {
        Text(text = stringResource(id = texto), color= Color.Black)
    }
}