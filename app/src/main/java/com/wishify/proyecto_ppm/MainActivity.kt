package com.wishify.proyecto_ppm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

import com.wishify.proyecto_ppm.ui.theme.AppTheme

import com.wishify.proyecto_ppm.ui.wishLists.view.AddList_Screen

import com.wishify.proyecto_ppm.ui.account.view.LogIn
import com.wishify.proyecto_ppm.ui.account.view.SignIn
import com.wishify.proyecto_ppm.ui.account.view.SignUp
import com.wishify.proyecto_ppm.ui.account.view.UserAccount


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                //LogIn()
                //SignIn()
                //SignUp()
                UserAccount()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        //LogIn()
        //SignIn()
        //SignUp()
        UserAccount()
    }
}