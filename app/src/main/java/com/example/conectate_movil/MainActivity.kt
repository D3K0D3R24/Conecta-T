package com.example.conectate_movil

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.graphics.fonts.Font
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.conectate_movil.ui.theme.Conectate_MovilTheme
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var istab:Boolean =false
        if (isTablet()){
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT.also { requestedOrientation = it }
            istab=true
        }else{
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT.also { requestedOrientation = it }
        }


        enableEdgeToEdge()

        setContent {
            Conectate_MovilTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    //MyApp(istab, InicioSesionViewModel())
                    AppNavigation(istab)
                }
            }
        }

    }
    fun isTablet():Boolean{
        val displayMetrics = resources.displayMetrics
        val widthInches = displayMetrics.widthPixels / displayMetrics.xdpi
        val heightInches = displayMetrics.heightPixels / displayMetrics.ydpi
        val diagonalInches = sqrt((widthInches * widthInches + heightInches * heightInches).toDouble())
        return diagonalInches >= 7.0
    }

}
