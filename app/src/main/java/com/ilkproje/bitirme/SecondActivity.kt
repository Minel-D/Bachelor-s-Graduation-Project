package com.ilkproje.bitirme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
            MyApp(sharedPreferences = sharedPreferences)
        }
    }
}
