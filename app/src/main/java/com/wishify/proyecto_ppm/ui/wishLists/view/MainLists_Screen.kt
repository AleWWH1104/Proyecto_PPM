package com.wishify.proyecto_ppm.ui.wishLists.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.wishify.proyecto_ppm.R
import androidx.compose.ui.tooling.preview.Preview
import com.wishify.proyecto_ppm.ui.elements.AppBar
import com.wishify.proyecto_ppm.ui.elements.Banner

@Preview(showBackground = true)
@Composable
fun MainLists(){
    Scaffold(
        bottomBar = { AppBar() }
    ){paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ){
            Banner(texto = R.string.slogan, painterResource(id = R.drawable.gift2))
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(4) { index ->
                    ListCard(
                        nameList = "List Name ${index + 1}",
                        event = "Event ${index + 1}"
                    )
                }
            }
        }
    }
}



