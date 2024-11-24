package com.wishify.proyecto_ppm.ui.guest

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.networking.WishWebService
import com.wishify.proyecto_ppm.ui.elements.AppBar
import com.wishify.proyecto_ppm.ui.elements.Banner
import com.wishify.proyecto_ppm.ui.elements.LargeButtons
import com.wishify.proyecto_ppm.ui.elements.topNavBar

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FieldValue



@Composable
fun AboutWish(
    navController: NavController,
    codeList: String,
    productID: Int,
    productName: String
) {
    val categoryName = remember { mutableStateOf("Cargando...") }
    val productImageUrl = remember { mutableStateOf<String?>(null) }
    val firestore = FirebaseFirestore.getInstance()

    // Lógica para obtener la categoría y URL de imagen
    LaunchedEffect(productID) {
        try {
            val wishWebService = WishWebService() // Crear instancia del servicio

            // Obtener categorías y buscar producto
            val categories = wishWebService.getWishCategories()
            for (category in categories) {
                val products = wishWebService.getCategoryFilter(categoryID = category.id)
                val product = products.find { it.itemID == productID }
                if (product != null) {
                    categoryName.value = category.category
                    productImageUrl.value = product.imageUrl
                    break
                }
            }
        } catch (e: Exception) {
            categoryName.value = "Error al cargar datos"
            println("Error al buscar la categoría: ${e.message}")
        }
    }

    // Función para eliminar el producto y su categoría
    fun removeItemFromDatabase() {
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

    Scaffold(
        topBar = { topNavBar(navController = navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ) {
            Banner(texto = R.string.aboutWish)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    if (!productImageUrl.value.isNullOrEmpty()) {
                        Image(
                            painter = rememberAsyncImagePainter(model = productImageUrl.value),
                            contentDescription = "Imagen del producto",
                            modifier = Modifier
                                .width(250.dp)
                                .height(250.dp)
                                .padding(16.dp)
                        )
                    } else {
                        Text(
                            text = "Cargando imagen...",
                            modifier = Modifier
                                .width(180.dp)
                                .height(180.dp)
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }

                    Column(
                        modifier = Modifier
                            .weight(0.5f)
                            .padding(start = 16.dp)
                    ) {
                        Text(
                            text = productName,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = categoryName.value,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                Text(
                    text = "Más información sobre el producto estará disponible aquí.",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.padding(16.dp))
                LargeButtons(
                    texto = R.string.reserBtn,
                    onClick = { removeItemFromDatabase() },
                    buttonColor = Color.White,
                    textColor = Color(0xFFb2422d)
                )
            }
        }
    }
}
