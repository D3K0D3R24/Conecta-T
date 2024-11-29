package com.example.conectate_movil.ui.screens.dashboard.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun Copyrigt(isTablet: Boolean, poppinsFontFamily: FontFamily
){

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Conect@t© 2024",
                fontSize = if (isTablet) 24.sp else 14.sp,
                fontFamily = poppinsFontFamily,
                textAlign = TextAlign.Center,
                color = Color(0xFF9adbd3),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "y todos sus afiliados",
                fontSize = if (isTablet) 24.sp else 14.sp,
                fontFamily = poppinsFontFamily,
                textAlign = TextAlign.Center,
                color = Color(0xFF9adbd3),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }

}