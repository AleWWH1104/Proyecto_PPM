package com.wishify.proyecto_ppm.ui.elements
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.wishify.proyecto_ppm.R
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.wishify.proyecto_ppm.navigation.NavigationState

@Composable
fun AppBar(navController: NavController){
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            iconButtons(icon = Icons.Filled.Home, texto = R.string.home, onClick = {navController.navigate(NavigationState.AllLists.route)})
            iconButtons(icon = Icons.Filled.AddCircle, texto = R.string.newList, onClick = {navController.navigate(NavigationState.NewList.route)})
            iconButtons(icon = Icons.Filled.AccountCircle, texto = R.string.profile, onClick = {navController.navigate(NavigationState.Profile.route)})
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topNavBar(navController: NavController){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        TopAppBar(
            title = { },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "atras",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFb2422d)
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SearchingBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = {}) { //Accion pendiente
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
        TextField(
            value = "text",
            onValueChange = { },
            placeholder = {
                Text(
                    text = "Search list code",
                    color = Color.Black,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        )
    }
}

// barra para buscar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchingBar(
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { }) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = Color.Black,
                modifier = Modifier.size(24.dp)
            )
        }
        TextField(
            value = searchText,
            onValueChange = { onSearchTextChange(it) },
            placeholder = {
                Text(
                    text = "Search by list name",
                    color = Color.Gray
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        )
    }
}

// guest searching bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchingBarGuest(onSearch: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text(
                    text = "Enter list code",
                    color = Color.Black,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White
            ),
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        )
        Button(
            onClick = { onSearch(text) },
            modifier = Modifier.height(50.dp)
        ) {
            Text("Search")
        }
    }
}
