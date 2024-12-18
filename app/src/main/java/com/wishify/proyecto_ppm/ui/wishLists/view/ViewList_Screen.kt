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


data class ListData2(
    val listNameP: String = "",
    val eventP: String = ""
)

@Composable
fun ViewList(navController: NavController, codeList: String) {

    println("Esta en viewList")
    println("$codeList, El tipo de dato de codelist en VIEWLIST es:" )
    println(codeList::class.simpleName)

    val db = FirebaseFirestore.getInstance()

    // Estados para datos
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

    // Obtener productos desde la API filtrados por `itemListProdID`
    LaunchedEffect(itemListProdID) {
        if (itemListProdID.isNotEmpty()) {
            try {
                val wishWebService = WishWebService()
                val allProducts = mutableListOf<WishProduct>()

                // Obtener categorías de la API (puede ajustarse si ya están disponibles)
                val categories = wishWebService.getWishCategories()

                for (category in categories) {
                    // Obtener productos por categoría
                    val productsFromApi = wishWebService.getCategoryFilter(category.id)

                    // Filtrar productos cuyo itemID coincida con los de itemListProdID
                    val matchingProducts = productsFromApi.filter { it.itemID in itemListProdID }

                    // Agregar los productos coincidentes a la lista acumulada
                    allProducts.addAll(matchingProducts)
                }

                // Actualizar productList con los productos filtrados
                productList = allProducts
                println("Lista final de productos: $productList") // Log de depuración
                isLoading = false
            } catch (e: Exception) {
                errorMessage = "Error al obtener productos de la API: ${e.message}"
                isLoading = false
            }
        } else {
            isLoading = false
        }
    }

    println("itemListProdID: ")
    println(itemListProdID)

    println("all prod productList: ")
    println(productList)

    fun removeItemFromDatabase(
        navController: NavController,
        firestore: FirebaseFirestore,
        codeList: String,
        productID: Int
    ) {
        val documentRef = firestore.collection("ListasP").document("ListaP")
            .collection("ListaP").document(codeList)

        documentRef.get().addOnSuccessListener { documentSnapshot ->
            val itemListProdID = (documentSnapshot.get("itemListProdID") as? List<Number>)?.map { it.toInt() }?.toMutableList()
            val itemListCategID = (documentSnapshot.get("itemListCategID") as? List<Number>)?.map { it.toInt() }?.toMutableList()

            println("Ln75, itemListProdID: $itemListProdID, itemListCategID: $itemListCategID")

            if (itemListProdID != null && itemListCategID != null) {
                val indexToRemove = itemListProdID.indexOf(productID)
                println("ProductID: $productID")
                println("Índice a eliminar: $indexToRemove")

                if (indexToRemove != -1) {
                    itemListProdID.removeAt(indexToRemove)
                    itemListCategID.removeAt(indexToRemove)

                    documentRef.update(
                        mapOf(
                            "itemListProdID" to itemListProdID,
                            "itemListCategID" to itemListCategID
                        )
                    ).addOnSuccessListener {
                        println("Producto y categoría eliminados correctamente.")
                        navController.navigateUp()
                    }.addOnFailureListener { e ->
                        println("Error al actualizar Firestore: ${e.message}")
                    }
                } else {
                    println("Producto no encontrado en la lista.")
                }
            } else {
                println("No se pudieron obtener los arrays de Firestore.")
            }
        }.addOnFailureListener { e ->
            println("Error al obtener el documento: ${e.message}")
        }
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
                                    removeItemFromDatabase(navController, db, codeList, product.itemID)
                                }
                            )
                        }
                    }

                }
            }
        }
    }
}