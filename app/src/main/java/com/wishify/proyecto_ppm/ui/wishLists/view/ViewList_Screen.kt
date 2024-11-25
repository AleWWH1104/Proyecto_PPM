package com.wishify.proyecto_ppm.ui.wishLists.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.wishify.proyecto_ppm.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.networking.WishWebService
import com.wishify.proyecto_ppm.networking.response.WishProduct
import com.wishify.proyecto_ppm.ui.elements.AppBar
import com.wishify.proyecto_ppm.ui.elements.iconButtons
import com.wishify.proyecto_ppm.ui.elements.topNavBar

import coil.compose.rememberAsyncImagePainter
import com.wishify.proyecto_ppm.ui.wishLists.repository.ViewListRepository
import com.wishify.proyecto_ppm.ui.wishLists.viewmodel.ViewListViewModel

@Composable
fun ViewList(
    navController: NavController,
    codeList: String
) {

    val repository = remember { ViewListRepository() }
    val viewModel = remember { ViewListViewModel(repository) }
    val listNameP by viewModel.listNameP.collectAsState()
    val eventP by viewModel.eventP.collectAsState()
    val productList by viewModel.productList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(codeList) {
        viewModel.loadData(codeList)
    }

    // Interfaz
    Scaffold(
        topBar = { topNavBar(navController = navController) },
        bottomBar = { AppBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = listNameP,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 45.sp, fontWeight = FontWeight.Bold),
                    color = Color(0xFFb2422d)
                )
                Text(
                    text = eventP,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 45.sp, fontWeight = FontWeight.Bold),
                    color = Color(0xFFb2422d)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 26.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Code List: $codeList",
                        modifier = Modifier
                            .background(Color.White)
                            .padding(16.dp)
                    )
                    iconButtons(
                        icon = Icons.Filled.AddCircle,
                        texto = R.string.addBtn,
                        onClick = {
                            val route = NavigationState.Categories.createRoute(codeList)
                            println("depu Navigating to route: $route") // Depuración
                            navController.navigate(NavigationState.Categories.createRoute(codeList))
                        }
                    )
                }
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                } else if (errorMessage != null) {
                    Text(
                        text = errorMessage!!,
                        color = Color.Red,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(productList) { product ->
                            ItemCard(
                                nameItem = product.nameItem,
                                imageItem = rememberAsyncImagePainter(model = product.imageUrl),
                                icono = Icons.Filled.Delete,
                                onClick = {
                                    viewModel.removeItem(codeList, product.itemID)
                                }
                            )
                        }
                    }

                }
            }
        }
    }
}