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
import com.wishify.proyecto_ppm.R

@Preview
@Composable
fun ItemList(nameItem: String="Hola"){
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
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(Color(0xFFb2422d)),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(text= stringResource(id = R.string.deleteBtn), color=Color.White)
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(Color(0xFFb2422d)),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(text= stringResource(id = R.string.reserBtn), color=Color.White)
                    }
                }
            }
        }
    }
}