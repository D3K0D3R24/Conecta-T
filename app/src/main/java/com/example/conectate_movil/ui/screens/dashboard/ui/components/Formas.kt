package com.example.conectate_movil.dashboard.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color

@Composable
fun Formas(isTablet: Boolean, backgroundAlpha: Float){
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .alpha(backgroundAlpha)
    ) {

        // Forma azul en la parte superior
        drawRoundRect(
            color = Color(0xFF33ACC1),
            size = Size(
                width = size.width * 1.0f,
                height = size.width * 1.0f
            ), // Tamaño del cuadrado
            topLeft = Offset(
                x = size.width * if (isTablet) 0.0f else 0.0f,
                y = -size.height * if (isTablet) 0.2f else 0.1f
            ), // Posición en la esquina inferior derecha
            cornerRadius = CornerRadius(
                if (isTablet) 400f else 150f,
                if (isTablet) 400f else 150f)
        )

        // Forma azul en la parte suerior derecha
        drawCircle(
            color = Color(0xFF8FD1DD),
            radius = size.width * 0.4f, // Radio del círculo pequeño
            center = Offset(
                x = size.width * 1.0f,
                y = size.height * 0.0f
            ) // Posiciona en la esquina superior derecha
        )


    }
}
