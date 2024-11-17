package com.wishify.proyecto_ppm.ui.wishLists.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import com.wishify.proyecto_ppm.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.wishify.proyecto_ppm.ui.elements.AppBar
import com.wishify.proyecto_ppm.ui.elements.Banner
import com.wishify.proyecto_ppm.ui.elements.SearchingBar
import com.wishify.proyecto_ppm.ui.elements.topNavBar

data class ListData(
    val listNameP: String = "",
    val eventP: String = "",
    val codeList: String = ""
)

@Composable
fun MainLists(navController: NavController) {
    val db = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser
    val uid = currentUser?.uid

    // Estado para almacenar las listas obtenidas de la base de datos
    var lists by remember { mutableStateOf<List<ListData>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    var tempCodeList by remember { mutableStateOf("")}

    // Obtener los datos al entrar en la pantalla
    LaunchedEffect(Unit) {
        isLoading = true
        if (uid != null) {
            // Paso 1: Obtener los CodeList del usuario
            db.collection("UsuariosP").document("UsuarioP")
                .collection("UsuarioP").document(uid)
                .get()
                .addOnSuccessListener { document ->
                    val codeLists = document.get("CodeList") as? List<String> ?: emptyList()

                    // Paso 2: Buscar los datos de cada CodeList en la base de datos "ListasP"
                    val fetchedLists = mutableListOf<ListData>()
                    codeLists.forEach { codeList ->
                        db.collection("ListasP").document("ListaP")
                            .collection("ListaP").document(codeList)
                            .get()
                            .addOnSuccessListener { listDocument ->
                                val listNameP = listDocument.getString("listNameP") ?: ""
                                val eventP = listDocument.getString("EventP") ?: ""

                                tempCodeList = codeList

                                println("Funciona en MainList")
                                println("ListName: $listNameP, Event: $eventP")
                                println("$codeList, El tipo de dato de codelist en mainlist es:" )
                                println(codeList::class.simpleName)

                                fetchedLists.add(ListData(listNameP = listNameP, eventP = eventP, codeList = codeList))


                                // Actualizar el estado con las listas obtenidas
                                lists = fetchedLists.toList()
                                isLoading = false
                            }
                            .addOnFailureListener {
                                println("Error al obtener datos de CodeList: ${it.message}")
                                isLoading = false
                            }
                    }
                }
                .addOnFailureListener {
                    println("Error al obtener CodeList: ${it.message}")
                    isLoading = false
                }
        } else {
            println("Usuario no autenticado.")
            isLoading = false
        }
    }

    // Composición UI
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
            Banner(texto = R.string.slogan)
            SearchingBar()

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
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
                    items(lists) { list ->
                        ListCard(
                            nameList = list.listNameP,
                            event = list.eventP,
                            codeList = list.codeList,
                            imagen = painterResource(id = R.drawable.img),
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}



