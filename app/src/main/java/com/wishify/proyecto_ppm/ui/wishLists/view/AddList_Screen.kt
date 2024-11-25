package com.wishify.proyecto_ppm.ui.wishLists.view
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.ui.elements.AppBar
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.ui.elements.Banner
import com.wishify.proyecto_ppm.ui.elements.LargeButtons
import com.wishify.proyecto_ppm.ui.elements.smallTexField
import com.wishify.proyecto_ppm.ui.elements.topNavBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import kotlin.random.Random

import com.wishify.proyecto_ppm.ui.wishLists.repository.AddListRepository
import com.wishify.proyecto_ppm.ui.wishLists.viewmodel.AddListViewModel
import com.wishify.proyecto_ppm.ui.wishLists.viewmodel.Event

@Composable
fun AddList(navController: NavController){

    println("DEBUG Esta en AddList")

    // Inicializamos el repositorio y el ViewModel usando `remember`
    val repository = remember { AddListRepository() }
    val viewModel = remember { AddListViewModel(repository) }

    // Observamos los estados desde el ViewModel
    val listName by viewModel.listName.collectAsState()
    val selectedEvent by viewModel.selectedEvent.collectAsState()

    // Lista de eventos con sus textos e imágenes
    val events = listOf(
        Event("Boda", R.drawable.boda),
        Event("Cumpleaños", R.drawable.cumple),
        Event("Navidad", R.drawable.navidad),
        Event("Año Nuevo", R.drawable.newyear),
        Event("Halloween", R.drawable.hallowen),
        Event("San Valentin", R.drawable.valentin),
        Event("Despedida de soltera", R.drawable.soltera),
        Event("Baby Shower", R.drawable.baby),
        Event("Día de Acción de Gracias", R.drawable.diagracias),
        Event("Día de la Madre", R.drawable.madre),
        Event("Día del Padre", R.drawable.padre),
        Event("Día del niño", R.drawable.nino),
        Event("Graduación", R.drawable.graduacion),
        Event("Otros", R.drawable.gift1),
    )

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
            Banner(texto = R.string.WishList)
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = stringResource(id = R.string.listName), color = Color.Black)
                smallTexField(
                    text = listName,
                    onTextChange = { viewModel.updateListName(it) }
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.padding(16.dp)
                ) {
                    items(events) { event ->
                        EventCard(
                            text = event.text,
                            imageRes = event.imageRes,
                            isSelected = selectedEvent == event,
                            onClick = { viewModel.selectEvent(event) }
                        )
                    }
                }
                LargeButtons(
                    texto = R.string.createList,
                    onClick = {
                        viewModel.createList(
                            onNavigate = { navController.navigate(NavigationState.AllLists.route) },
                            onError = { println(it) }
                        )
                    },
                    buttonColor = Color(0xFFb2422d),
                    textColor = Color(0xFFfef0e1)
                )
            }
        }
    }
}