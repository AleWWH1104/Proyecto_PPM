package com.wishify.proyecto_ppm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.wishify.proyecto_ppm.ui.theme.AppTheme
import com.wishify.proyecto_ppm.ui.wishLists.view.MainLists
import com.wishify.proyecto_ppm.ui.wishLists.view.ViewList


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                MainLists()
            }
        }
    }
}