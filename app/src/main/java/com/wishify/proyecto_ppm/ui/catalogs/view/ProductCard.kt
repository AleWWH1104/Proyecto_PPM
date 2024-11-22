package com.wishify.proyecto_ppm.ui.catalogs.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.networking.response.WishProduct
import com.wishify.proyecto_ppm.ui.elements.smallButtons

@Composable
fun ProductCard(navController: NavController, product: WishProduct, codeList: String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImage(
                model = product.imageUrl,
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.3f)
                    .fillMaxHeight()
                    .padding(end = 8.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text= product.nameItem,
                color = Color.Black,
                fontSize = 10.sp,
                lineHeight = 12.sp,
                modifier = Modifier
                    .weight(0.7f)
            )
            smallButtons(texto = R.string.seeBtn, onClick = {navController.navigate(NavigationState.addDetail.createRoute(codeList, product))})
        }
    }
}