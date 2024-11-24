package com.wishify.proyecto_ppm.ui.catalogs.view

import androidx.compose.ui.Alignment
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.wishify.proyecto_ppm.ui.catalogs.viewmodel.CategoryViewModel
import com.wishify.proyecto_ppm.ui.elements.AppBar
import com.wishify.proyecto_ppm.ui.elements.topNavBar
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun ProductsByCategory(categoryID: Int, titleCategory:String, codeList: String, navController: NavController, viewModel: CategoryViewModel= viewModel()){

    println("Esta en ProductsByCategory")
    println("categoryID: $categoryID, codeList: $codeList, titleCategory $titleCategory" )
    val categoryFilter by viewModel.products.observeAsState(null)

    LaunchedEffect(Unit) {
        viewModel.fetchByCategory(categoryID)
    }
    Scaffold(
        topBar = { topNavBar(navController = navController) },
        bottomBar = { AppBar(navController) }
    ){paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFfef0e1))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(
                    text = codeList,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 35.sp, fontWeight = FontWeight.Bold),
                    color = Color(0xFFb2422d),
                    textAlign = TextAlign.Center
                )
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.padding(paddingValues)
                ){
                    categoryFilter?.let {
                        items(it){ product ->
                            ProductCard(navController = navController, product = product, codeList = titleCategory)
                        }
                    }
                }
            }

        }
    }
}


