package com.axelblatt.vktask1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.axelblatt.vktask1.ui.theme.VKTask1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VKTask1Theme {
                ProductsApp()
            }
        }
    }
}