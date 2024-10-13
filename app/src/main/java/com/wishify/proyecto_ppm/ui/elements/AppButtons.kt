package com.wishify.proyecto_ppm.ui.elements

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun smallButtons(@StringRes texto: Int){
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(Color(0xFFb2422d)),
        contentPadding = PaddingValues(5.dp),
    ){
        Text(text = stringResource(id = texto), color= Color.White)
    }
}

@Composable
fun iconButtons(icon: ImageVector, @StringRes texto: Int){
    Button(
        onClick = { },
        contentPadding = PaddingValues(10.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFb2422d))
    ) {
        Icon(imageVector = icon, contentDescription = "", tint = Color.White)
        Text(text=  stringResource(id = texto), color = Color.White)
    }
}

@Composable
fun LargeButtons(@StringRes texto: Int){
    Button(
        onClick = {  },
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFb2422d),
        )
    ) {
        Text(text= stringResource(id = texto), color = Color.White)
    }
}