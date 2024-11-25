package com.wishify.proyecto_ppm.ui.wishLists.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.wishify.proyecto_ppm.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.wishify.proyecto_ppm.ui.elements.AppBar
import com.wishify.proyecto_ppm.ui.elements.Banner
import com.wishify.proyecto_ppm.ui.elements.SearchingBar
import com.wishify.proyecto_ppm.ui.elements.topNavBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.firebase.firestore.FieldValue
import com.wishify.proyecto_ppm.ui.account.repository.SignInRepository
import com.wishify.proyecto_ppm.ui.account.viewmodel.SignInViewModel
import com.wishify.proyecto_ppm.ui.wishLists.repository.MainListsRepository
import com.wishify.proyecto_ppm.ui.wishLists.viewmodel.MainListsViewModel

@Composable
fun MainLists(
    navController: NavController
) {

    //val repository = MainListsRepository()
    //val viewModel = MainListsViewModel(repository)
    val repository = remember { MainListsRepository() }
    val viewModel = remember { MainListsViewModel(repository) }

    val lists by viewModel.lists.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var searchText by remember { mutableStateOf("") }
    val filteredLists = if (searchText.isEmpty()) {
        lists
    } else {
        lists.filter { it.listNameP.contains(searchText, ignoreCase = true) }
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
        ) {
            Banner(texto = R.string.slogan)
            SearchingBar(
                searchText = searchText,
                onSearchTextChange = { searchText = it }
            )
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
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
                    items(filteredLists) { list ->
                        ListCard(
                            nameList = list.listNameP,
                            event = list.eventP,
                            codeList = list.codeList,
                            imagenRes = list.imageRes,
                            navController = navController,
                            onListChange = { codeListToRemove ->
                                viewModel.deleteList(codeListToRemove)
                            }
                        )
                    }
                }
            }
        }
    }
}

