package com.wishify.proyecto_ppm.ui.guest

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.firestore.FirebaseFirestore
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.networking.WishWebService
import com.wishify.proyecto_ppm.networking.response.WishProduct
import com.wishify.proyecto_ppm.ui.elements.topNavBar
import com.wishify.proyecto_ppm.ui.wishLists.view.ItemCard

@Composable
fun ViewGuestList(navController: NavController, codeList: String) {
    val db = FirebaseFirestore.getInstance()

    // Estados para los datos
    var listNameP by remember { mutableStateOf("") }
    var eventP by remember { mutableStateOf("") }
    var itemListProdID by remember { mutableStateOf<List<Int>>(emptyList()) }
    var productList by remember { mutableStateOf<List<WishProduct>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Obtener datos de Firebase
    LaunchedEffect(codeList) {
        db.collection("ListasP").document("ListaP")
            .collection("ListaP").document(codeList)
            .get()
            .addOnSuccessListener { document ->
                listNameP = document.getString("listNameP") ?: ""
                eventP = document.getString("EventP") ?: ""
                itemListProdID = (document.get("itemListProdID") as? List<Long>)?.map { it.toInt() } ?: emptyList()
            }
            .addOnFailureListener {
                errorMessage = "Error al obtener datos de Firestore: ${it.message}"
            }
    }

    // Obtener productos desde la API
    LaunchedEffect(itemListProdID) {
        if (itemListProdID.isNotEmpty()) {
            try {
                val wishWebService = WishWebService()
                val allProducts = mutableListOf<WishProduct>()

                val categories = wishWebService.getWishCategories()

                for (category in categories) {
                    val productsFromApi = wishWebService.getCategoryFilter(category.id)
                    val matchingProducts = productsFromApi.filter { it.itemID in itemListProdID }
                    allProducts.addAll(matchingProducts)
                }

                productList = allProducts
                isLoading = false
            } catch (e: Exception) {
                errorMessage = "Error al obtener productos de la API: ${e.message}"
                isLoading = false
            }
        } else {
            isLoading = false
        }
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
                        ItemCard(
                            nameItem = product.nameItem,
                            imageItem = painterResource(id = R.drawable.img),
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

