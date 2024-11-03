package com.wishify.proyecto_ppm.ui.wishLists.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.wishify.proyecto_ppm.R
import com.wishify.proyecto_ppm.navigation.NavigationState
import com.wishify.proyecto_ppm.ui.elements.LargeButtons
import com.wishify.proyecto_ppm.ui.elements.smallButtons

@Preview
@Composable
fun ItemList(nameItem: String="Hola"){
    val navController = rememberNavController()
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(180.dp)
            .height(200.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.gift2),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(9.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = nameItem,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){

                    smallButtons(
                        texto = R.string.deleteBtn,
                        // creo que esta no es la ruta que va al final, pero para mientras
                        onClick = { }

                    )
                    smallButtons(
                        texto = R.string.reserBtn,
                        // creo que esta no es la ruta que va al final, pero para mientras
                        onClick = { }
                    )
                }
            }
        }
    }
}