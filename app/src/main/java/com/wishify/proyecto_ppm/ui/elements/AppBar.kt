package com.wishify.proyecto_ppm.ui.elements
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
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            iconButtons(icon = Icons.Filled.Home, texto = R.string.home)
            iconButtons(icon = Icons.Filled.AddCircle, texto = R.string.newList)
            iconButtons(icon = Icons.Filled.AccountCircle, texto = R.string.profile)
        }
    }
}