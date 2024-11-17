package com.wishify.proyecto_ppm.ui.wishLists.view
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.ui.elements.Banner
import com.wishify.proyecto_ppm.ui.elements.LargeButtons
import com.wishify.proyecto_ppm.ui.elements.smallTexField
import com.wishify.proyecto_ppm.ui.elements.topNavBar
import kotlin.random.Random

@Composable
fun AddList(navController: NavController){

    val firestore = FirebaseFirestore.getInstance()
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser

    // Estados para el nombre de la lista y el evento seleccionado
    val listName = remember { mutableStateOf("") }
    val selectedEvent = remember { mutableStateOf("") }


    // Lista de eventos disponibles
    val eventOptions = listOf(
        "Cumpleaños", "Bodas", "Bautizos", "San Valentín",
        "Aniversarios", "Graduaciones", "Navidad", "Nacimientos"
    )

    // Función para generar un CodeList aleatorio
    fun generateCodeList(): String {
        return Random.nextInt(10000, 99999).toString()
    }

    fun addCodeListToUser(uid: String, newCodeList: String) {
        val db = FirebaseFirestore.getInstance()

        // Actualiza el array CodeList del documento correspondiente al UID
        db.collection("UsuariosP").document("UsuarioP")
            .collection("UsuarioP").document(uid)
            .update("CodeList", FieldValue.arrayUnion(newCodeList))
            .addOnSuccessListener {
                println("CodeList agregado exitosamente al usuario con UID: $uid")
            }
            .addOnFailureListener { e ->
                println("Error al agregar CodeList: ${e.message}")
            }
    }


    // Función para crear la lista en Firestore
    fun createList() {
        val codeList = generateCodeList()
        if (selectedEvent.value.isNotEmpty() && listName.value.isNotEmpty()) {
            val listData = hashMapOf(
                "CodeList" to codeList,
                "EventP" to selectedEvent.value,
                "itemListCategID" to emptyList<Int>(),
                "itemListProdID" to emptyList<Int>(),
                "listNameP" to listName.value
            )

            // Guardar en Firestore
            firestore.collection("ListasP").document("ListaP")
                .collection("ListaP").document(codeList)
                .set(listData)
                .addOnSuccessListener {
                    println("Lista creada exitosamente.")
                    //auth.currentUser?.uid
                    //val currentUID = auth.currentUser?.uid
                    val uid = auth.currentUser?.uid
                    if (uid != null) {
                        // guaradar la codelist en la lista del UID current
                        addCodeListToUser(uid, codeList)
                    } else {
                        println("el UID es incorrecto.")
                    }
                    println("$codeList, El tipo de dato de codelist en addlist es:")
                    println(codeList::class.simpleName)
                    navController.navigate(NavigationState.AllLists.route)

                }
                .addOnFailureListener { e ->
                    println("Error al crear la lista: ${e.message}")
                }
        } else {
            println("Faltan campos por completar.")
        }
    }

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
                Text(text= stringResource(id = R.string.listName))
                smallTexField(
                    text = listName.value,
                    onTextChange = { listName.value = it}
                )
                Spacer(modifier = Modifier.padding(8.dp))

                Text(text= stringResource(id = R.string.event))
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(eventOptions) { event ->
                        EventCard(
                            text = event,
                            image = painterResource(id = R.drawable.img),
                            isSelected = selectedEvent.value == event,
                            onClick = { selectedEvent.value = event }
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(8.dp))

                LargeButtons(
                    texto = R.string.createList,
                    onClick = { createList() },
                    buttonColor = Color(0xFFfef0e1),
                    textColor = Color(0xFFb2422d)
                )
            }
        }
    }
}