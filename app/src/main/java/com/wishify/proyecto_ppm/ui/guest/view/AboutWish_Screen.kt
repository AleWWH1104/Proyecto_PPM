package com.wishify.proyecto_ppm.ui.guest.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.networking.WishWebService
import com.wishify.proyecto_ppm.ui.elements.Banner
import com.wishify.proyecto_ppm.ui.elements.LargeButtons
import com.wishify.proyecto_ppm.ui.elements.topNavBar

import com.google.firebase.firestore.FirebaseFirestore
import com.wishify.proyecto_ppm.ui.guest.repository.AboutWishRepository
import com.wishify.proyecto_ppm.ui.guest.viewmodel.AboutWishViewModel


@Composable
fun AboutWish(
    navController: NavController,
    codeList: String,
    productID: Int,
    productName: String
) {
    // Inicializa el repositorio y el ViewModel con remember
    val repository = remember {
        AboutWishRepository(
            firestore = FirebaseFirestore.getInstance(),
            wishWebService = WishWebService()
        )
    }
    val viewModel = remember { AboutWishViewModel(repository) }

    // Observa los estados desde el ViewModel
    val categoryName by viewModel.categoryName
    val productImageUrl by viewModel.productImageUrl

    // Llama a la lógica inicial en LaunchedEffect
    LaunchedEffect(productID) {
        viewModel.fetchCategoryAndImage(productID)
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
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    if (!productImageUrl.isNullOrEmpty()) {
                        Image(
                            painter = rememberAsyncImagePainter(model = productImageUrl),
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
                        Text(text = productName, style = MaterialTheme.typography.titleMedium)
                        Text(text = categoryName, style = MaterialTheme.typography.titleSmall)
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
                    onClick = {
                        viewModel.removeItemFromDatabase(codeList, productID) {
                            navController.navigateUp()
                        }
                    },
                    buttonColor = Color.White,
                    textColor = Color(0xFFb2422d)
                )
            }
        }
    }
}
