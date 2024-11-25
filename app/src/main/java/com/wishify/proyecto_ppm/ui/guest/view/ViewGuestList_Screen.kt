package com.wishify.proyecto_ppm.ui.guest.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.firestore.FirebaseFirestore
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.networking.WishWebService
import com.wishify.proyecto_ppm.networking.response.WishProduct
import com.wishify.proyecto_ppm.ui.elements.topNavBar
import com.wishify.proyecto_ppm.ui.guest.repository.GuestListRepository
import com.wishify.proyecto_ppm.ui.guest.viewmodel.GuestListViewModel
import com.wishify.proyecto_ppm.ui.wishLists.view.ItemCardGuestList

@Composable
fun ViewGuestList(navController: NavController, codeList: String){

    val repository = remember { GuestListRepository() }
    val viewModel = remember { GuestListViewModel(repository) }

    val listNameP by viewModel.listName.collectAsState()
    val eventP by viewModel.event.collectAsState()
    val productList by viewModel.productList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    LaunchedEffect(codeList) {
        viewModel.loadGuestList(codeList)
    }

    // UI
    Scaffold(
        topBar = { topNavBar(navController = navController) }
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
                    .background(Color(0xFFb2422d))
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp)
                )
                Text(
                    text = "List Name: $listNameP",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color(0xFFfef0e1),
                )
                Text(
                    text = "Event: $eventP",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    color = Color(0xFFfef0e1),
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
                        val imagePainter = rememberAsyncImagePainter(
                            model = product.imageUrl,
                            placeholder = painterResource(id = R.drawable.img), // Placeholder mientras carga
                            error = painterResource(id = R.drawable.img) // Imagen de error
                        )

                        ItemCardGuestList(
                            nameItem = product.nameItem,
                            imagePainter = imagePainter,
                            icono = Icons.Filled.Info,
                            onClick = {
                                val route = NavigationState.AboutWish.createRoute(
                                    codeList = codeList,
                                    productID = product.itemID,
                                    productName = product.nameItem
                                )
                                navController.navigate(route)
                            }
                        )
                    }
                }
            }
        }
    }
}

