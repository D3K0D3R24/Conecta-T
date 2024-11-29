package com.example.conectate_movil.ui.screens.dashboard.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OpcionesCard(
    isTablet: Boolean,
    image: Int,
    title: String,
    color : Color,
    poppinsFontFamily: FontFamily,
    colorLetra: Color,
    onClick: () -> Unit
) {

    Box (
        modifier = Modifier.fillMaxWidth()
    ){
        Card(
            modifier = Modifier.align(Alignment.Center)
                .width(if (isTablet) 600.dp else 300.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = color
            ),
            onClick = onClick
        ) {
            Row(
                modifier = Modifier.padding(
                    start = 26.dp,
                    top = 16.dp,
                    bottom = 16.dp,
                    end = 60.dp
                ),
            ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "Arrow",
                    modifier = Modifier.size(if (isTablet) 50.dp else 30.dp),
                )
                Text(
                    text = title,
                    fontSize = if (isTablet) 22.sp else 12.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = colorLetra,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
