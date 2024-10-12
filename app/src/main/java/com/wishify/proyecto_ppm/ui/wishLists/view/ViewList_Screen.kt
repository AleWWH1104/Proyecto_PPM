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
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.wishify.proyecto_ppm.ui.elements.AppBar

@Preview
@Composable
fun ViewList() {
    val listData1 = listOf("Item1", "Item2", "Item3", "Item4", "Item5", "Item6")
    Scaffold(
        bottomBar = { AppBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .background(Color(0xFFb2422d)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = "img",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                )
                Text(
                    text = stringResource(id = R.string.listName),
                    color = Color.White,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(listData1) { item ->
                    ItemList(item)
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(0xFFb2422d)),
                    contentPadding = PaddingValues(5.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Delete, contentDescription = "")
                    Text(text = stringResource(id = R.string.deleteBtn), color = Color.White)
                }
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(0xFFb2422d)),
                    contentPadding = PaddingValues(5.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "")
                    Text(text = stringResource(id = R.string.addBtn), color = Color.White)
                }
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(0xFFb2422d)),
                    contentPadding = PaddingValues(5.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Share, contentDescription = "")
                    Text(text = stringResource(id = R.string.shareBtn), color = Color.White)
                }
            }
        }
    }
}