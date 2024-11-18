package com.wishify.proyecto_ppm.ui.catalogs.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.Text
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
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.networking.WishWebService
import com.wishify.proyecto_ppm.ui.elements.AppBar
import com.wishify.proyecto_ppm.ui.elements.Banner
import com.wishify.proyecto_ppm.ui.elements.LargeButtons
import com.wishify.proyecto_ppm.ui.elements.LargeTextField
import com.wishify.proyecto_ppm.ui.elements.topNavBar


@Composable
fun AddItem(navController: NavController = rememberNavController(), codeList: String, productID: Int, productName: String) {

    println("Esta en AddItemscreen")
    println("con el codeList: $codeList")
    println("con el productID es: $productID")
    println("con el productname es: $productName")

    val categoryName = remember { mutableStateOf("Cargando...") }
    val productImageUrl = remember { mutableStateOf<String?>(null) }

    // Obtener la categoría asociada al producto y urldel prod
    LaunchedEffect(productID) {
        try {
            val wishWebService = WishWebService() // Crear instancia del servicio

            // Paso 1: Obtener todas las categorías
            val categories = wishWebService.getWishCategories()

            // Paso 2: Iterar categorías y buscar el producto
            var foundCategoryName: String? = null
            var foundProductImageUrl: String? = null
            for (category in categories) {
                val products = wishWebService.getCategoryFilter(categoryID = category.id)
                val product = products.find { it.itemID == productID }
                if (product != null) {
                    foundCategoryName = category.category // Guardar el nombre de la categoría
                    foundProductImageUrl = product.imageUrl // Guardar el URL de la imagen
                    println("found URL img: $foundProductImageUrl")
                    productImageUrl.value = foundProductImageUrl
                    break // Terminar la búsqueda
                }
            }

            // Actualizar estados con los valores encontrados o mensajes de error
            categoryName.value = foundCategoryName ?: "Categoría no encontrada"
            //productImageUrl.value = foundProductImageUrl
        } catch (e: Exception) {
            categoryName.value = "Error al cargar categoría"
            println("Error al buscar la categoría: ${e.message}")
        }
    }

    println("URL img: $productImageUrl")
    println("URL img con value : ${productImageUrl.value}")

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
            Banner(texto = R.string.describeWish)
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
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    "Agrega una descripcion mas especifica para tu deseo (colores/tamaño/marca/etc).\n\nEj. Unas botas largas de color negro de Zara ...",
                    textAlign = TextAlign.Center
                )
                LargeTextField()
                Spacer(modifier = Modifier.padding(8.dp))
                LargeButtons(texto = R.string.addBtn, onClick = { navController.navigate(NavigationState.AllLists.route) }, buttonColor = Color(0xFFb2422d), textColor =Color(0xFFfef0e1) )
            }
        }
    }
}
