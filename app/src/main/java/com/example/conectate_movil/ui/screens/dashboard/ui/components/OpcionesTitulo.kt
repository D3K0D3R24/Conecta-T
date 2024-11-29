package com.example.conectate_movil.dashboard.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OpcionesTitulo(
    isTablet: Boolean,
    texto: String,
    color : Color,
    poppinsFontFamily: FontFamily
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ){
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .background(color,
                    shape = RoundedCornerShape(15.dp)
                )
                .padding(
                    horizontal = if (isTablet) 100.dp else 50.dp,
                    vertical = if (isTablet) 10.dp else 4.dp)
        ){
            Text(
                text = texto,
                fontSize = if (isTablet) 24.sp else 14.sp,
                fontFamily = poppinsFontFamily,
                color = Color.White,
            )
        }
    }
}