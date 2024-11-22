package com.wishify.proyecto_ppm.ui.catalogs.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.networking.response.WishCategory

@Composable
fun CategoryCard(category: WishCategory,codeList: String, navController: NavController){
    Row(
        modifier = Modifier
            .background(Color.White)
            .width(180.dp)
            .height(60.dp)
            .clickable {navController.navigate(NavigationState.ProductsByCategory.createRoute(category.id, category.category, codeList))},
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(category.imageUrl),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(0.4f),
            contentScale = ContentScale.Crop
        )
        Text(
            text = category.category?: "",
            color = Color.Black,
            fontSize = 10.sp,
            lineHeight = 12.sp,
            modifier = Modifier
                .weight(0.6f)
                .padding(8.dp)
        )
    }
}