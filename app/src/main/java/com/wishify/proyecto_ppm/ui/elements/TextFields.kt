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
fun LargeTextField(){
    TextField(
        value = "",
        onValueChange ={},
        colors = TextFieldDefaults.textFieldColors(
        containerColor = Color(0xFFa8a6a4),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .padding(vertical = 16.dp)
            .height(100.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun smallTexField(){
    TextField(
        value = "",
        onValueChange = { },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xFFC97763),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White
        ),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth(0.85f)
            .padding(vertical = 16.dp)
    )
}


