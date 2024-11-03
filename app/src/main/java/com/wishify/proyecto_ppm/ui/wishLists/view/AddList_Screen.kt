package com.wishify.proyecto_ppm.ui.wishLists.view
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.ui.elements.AppBar
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wishify.proyecto_ppm.ui.catalogs.view.SelectEvent
import com.wishify.proyecto_ppm.ui.elements.Banner
import com.wishify.proyecto_ppm.ui.elements.LargeButtons
import com.wishify.proyecto_ppm.ui.elements.smallTexField

@Composable
fun AddList(navController: NavController){
    Scaffold(
        bottomBar = { AppBar(navController) }
    ){paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ){
            Banner(texto = R.string.WishList, painterResource(id = R.drawable.gift1), navController)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(text= stringResource(id = R.string.listName))
                smallTexField()
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text= stringResource(id = R.string.event))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(12) { index ->
                        SelectEvent(text = "Evento ${index + 1}")
                    }
                }
                Spacer(modifier = Modifier.padding(8.dp))
//                LargeButtons(texto = R.string.createList, {})

            }

        }
    }
}