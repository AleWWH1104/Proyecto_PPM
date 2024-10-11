package com.wishify.proyecto_ppm.navigation
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.graphics.Color
import com.wishify.proyecto_ppm.R
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wishify.proyecto_ppm.ui.theme.AppTheme

@Preview(showBackground = true)
@Composable
fun AppBar(){
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
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Color(0xFFb2422d)),
                contentPadding = PaddingValues(10.dp)
            ) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "")
                Text(text=  stringResource(id = R.string.home), color = Color.White)
            }
            Button(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(10.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFb2422d))
            ) {
                Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "")
                Text(text=  stringResource(id = R.string.newList), color = Color.White)
            }
            Button(
                onClick = { /*TODO*/ },
                contentPadding = PaddingValues(10.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFb2422d))
            ){
                Icon(imageVector = Icons.Filled.AccountCircle, contentDescription = "")
                Text(text=  stringResource(id = R.string.profile), color = Color.White)
            }
        }
    }
}