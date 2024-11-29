package com.example.conectate_movil.ui.screens.dashboard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.conectate_movil.R

@Composable
fun Advertencia(isTablet: Boolean, poppinsFontFamily: FontFamily
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ){
        Row(
            modifier = Modifier
                .align(Alignment.Center)
                .width(if (isTablet) 600.dp else 300.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.alerta),
                contentDescription = "advertencia",
                modifier = Modifier.size(50.dp).padding(top = 5.dp),
            )

            // Espacio
            Spacer(modifier = Modifier.width(if (isTablet) 20.dp else 10.dp))

            Text(
                text = "Su Fecha de pago está a próxima de vencer, " +
                        "acuda al sucursal para resolver su situacio.",
                color = Color.Red,
                textAlign = TextAlign.Justify,
                fontFamily = poppinsFontFamily,
                fontSize = if (isTablet) 24.sp else 14.sp,
            )
        }
    }
}