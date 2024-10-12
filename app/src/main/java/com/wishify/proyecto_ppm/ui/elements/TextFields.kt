package com.wishify.proyecto_ppm.ui.elements

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.wishify.proyecto_ppm.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Inputs_TextField(){
    OutlinedTextField(
        value = "",
        onValueChange = { },
        shape = RoundedCornerShape(50), // Redondeado
        modifier = Modifier
            .fillMaxWidth(0.9f), // Tamaño para que se vea correctamente
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White,
            containerColor = Color(0xFFfef0e1)// Transparente
        )
    )
}


