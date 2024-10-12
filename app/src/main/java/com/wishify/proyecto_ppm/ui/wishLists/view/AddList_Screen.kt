package com.wishify.proyecto_ppm.ui.wishLists.view
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import com.wishify.proyecto_ppm.ui.catalogs.view.selectEvent
import com.wishify.proyecto_ppm.ui.elements.Inputs_TextField
import com.wishify.proyecto_ppm.ui.elements.Botones

@Preview
@Composable
fun AddList_Screen(){
    Scaffold(
        bottomBar = { AppBar() }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFfef0e1))
                .padding(paddingValues)
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFfef0e1))
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Image(
                        painter = painterResource(id = R.drawable.gift_banner2),
                        contentDescription = "gift",
                        modifier = Modifier
                            .size(130.dp)
                    )
                    Text(
                        text= stringResource(id = R.string.WishList),
                        color= Color(0xFFb2422d),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFb2422d))
                        .padding(16.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text= stringResource(id = R.string.listName),
                        color= Color.White
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Inputs_TextField()
                    Spacer(modifier = Modifier.padding(16.dp))
                    Text(
                        text= stringResource(id = R.string.event),
                        color= Color.White
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ){
                            selectEvent("Boda")
                            selectEvent("Cumpleanos")
                            selectEvent("Graduacion")
                            selectEvent("Navidad")
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ){
                            selectEvent("Halloween")
                            selectEvent("New Year")
                            selectEvent("San Valentin")
                            selectEvent("Otros")
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Botones(texto = R.string.createList)
                    }
                }
            }
        }
    }
}