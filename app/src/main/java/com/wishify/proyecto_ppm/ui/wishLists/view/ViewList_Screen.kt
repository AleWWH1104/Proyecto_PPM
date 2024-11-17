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
import com.wishify.proyecto_ppm.ui.elements.AppBar
import com.wishify.proyecto_ppm.ui.elements.iconButtons
import com.wishify.proyecto_ppm.ui.elements.topNavBar

data class ListData2(
    val listNameP: String = "",
    val eventP: String = ""
)

@Composable
fun ViewList(navController: NavController, codeList: String) {
    println("Llegó a ViewList con codeList: $codeList")


    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser
    val uid = currentUser?.uid

    var verCodeList = codeList
    println("$verCodeList, El tipo de dato de verCodeList en viewlist es:")
    println(verCodeList::class.simpleName)

    // Estados para almacenar los datos de Firestore
    var listNameP by remember { mutableStateOf("") }
    var eventP by remember { mutableStateOf("") }
    var lists by remember { mutableStateOf<List<ListData2>>(emptyList()) }

    println("$codeList, El tipo de dato de codelist en viewlist es:")
    println(codeList::class.simpleName)

    // Obtener datos de Firebase
    LaunchedEffect(codeList) {
        val fetchedLists = mutableListOf<ListData2>() // Lista temporal
        db.collection("ListasP").document("ListaP")
            .collection("ListaP").document(codeList)
            .get()
            .addOnSuccessListener { listDocument ->
                val fetchedListNameP = listDocument.getString("listNameP") ?: ""
                val fetchedEventP = listDocument.getString("EventP") ?: ""

                // Actualizar estados
                listNameP = fetchedListNameP
                eventP = fetchedEventP

                println("Funciona en viewlist1")
                println("ListName1: $listNameP, Event: $eventP")

                fetchedLists.add(ListData2(fetchedListNameP, fetchedEventP))
                lists = fetchedLists // Asignar la lista al estado principal

                println("Funciona en viewList2")
                println("ListName2: $listNameP, Event: $eventP")
            }
            .addOnFailureListener { exception ->
                println("Error al obtener datos de CodeList: ${exception.message}")
            }
    }

    Scaffold(
        topBar = { topNavBar(navController = navController) },
        bottomBar = { AppBar(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
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
                ){
                    Text(text ="Code List: $codeList", modifier = Modifier
                        .background(Color.White)
                        .padding(16.dp) )
                    iconButtons(icon = Icons.Filled.AddCircle, texto = R.string.addBtn, onClick = { navController.navigate(NavigationState.Categories.route)})
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(6) { index ->
                        ItemCard(nameItem = "Item: ${index +1}", imageItem = painterResource(id = R.drawable.img), icono = Icons.Filled.Delete, onClick = {})
                    }
                }
            }
        }
    }
}