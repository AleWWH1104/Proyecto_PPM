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

@Composable
fun AddList(navController: NavController){
    // Lista de eventos con sus textos e imágenes
    val events = listOf(
        Event("Boda", painterResource(id = R.drawable.boda)),
        Event("Cumpleaños", painterResource(id = R.drawable.cumple)),
        Event("Navidad", painterResource(id = R.drawable.navidad)),
        Event("Año Nuevo", painterResource(id = R.drawable.newyear)),
        Event("Halloween", painterResource(id = R.drawable.hallowen)),
        Event("San Valentin", painterResource(id = R.drawable.valentin)),
        Event("Despedida de soltera", painterResource(id = R.drawable.soltera)),
        Event("Baby Shower", painterResource(id = R.drawable.baby)),
        Event("Día de Acción de Gracias", painterResource(id = R.drawable.diagracias)),
        Event("Día de la Madre", painterResource(id = R.drawable.madre)),
        Event("Día del Padre", painterResource(id = R.drawable.padre)),
        Event("Día del niño", painterResource(id = R.drawable.nino)),
        Event("Graduación", painterResource(id = R.drawable.graduacion)),
        Event("Otros", painterResource(id = R.drawable.gift1)),
    )
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
            Banner(texto = R.string.WishList)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text= stringResource(id = R.string.listName), color= Color.Black)
                smallTexField()
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text= stringResource(id = R.string.event), color= Color.Black)
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(events) { event ->
                        EventCard(text = event.text, image = event.image)
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
                LargeButtons(
                    texto = R.string.createList,
                    onClick = { navController.navigate(NavigationState.AllLists.route) },
                    buttonColor = Color(0xFFb2422d),
                    textColor = Color(0xFFfef0e1)
                )
            }
        }
    }
}