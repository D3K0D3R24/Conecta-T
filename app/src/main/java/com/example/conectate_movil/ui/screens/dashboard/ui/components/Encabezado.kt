package com.example.conectate_movil.ui.screens.dashboard.ui.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.conectate_movil.R

// Este componente es el encabezado muestra el saludo y el nombre de usuario.
@Composable
fun Encabezado(
    username: String,
    isTablet: Boolean,
    poppinsFontFamily: FontFamily
) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = if (isTablet) 45.dp else 36.dp,
                start = if (isTablet) 33.dp else 24.dp,
                end = if (isTablet) 33.dp else 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "Bienvenido",
                fontWeight = FontWeight.Bold,
                fontSize = if (isTablet) 30.sp else 18.sp,
                fontFamily = poppinsFontFamily,
                color = Color.Black
            )
            Text(
                text = username,
                fontSize = if (isTablet) 25.sp else 16.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFontFamily,
                color = Color.White
            )
        }
        Row {
            Image(
                painter = painterResource(id = R.drawable.baseline_notifications_none_24),
                contentDescription = "Notifications",
                modifier = Modifier
                    .size(if (isTablet) 50.dp else 35.dp)
                    .padding(top = if (isTablet) 8.dp else 5.dp)
                    .clickable {
                        Toast.makeText(context, "Próximamente Notificaciones", Toast.LENGTH_SHORT).show()
                    }
            )
            Spacer(modifier = Modifier.padding(if (isTablet) 15.dp else 8.dp))
            Card(
                modifier = Modifier.size(if (isTablet) 60.dp else 40.dp).align(alignment = Alignment.CenterVertically),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF405084)
                ),
                onClick = {
                    expanded = !expanded
                }
            ) {
                Text(
                    text = username.substring(0,1).uppercase(),
                    color = Color.White,
                    fontSize = if (isTablet) 30.sp else 14.sp,
                    fontFamily = poppinsFontFamily,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxSize().padding(top = if (isTablet) 13.dp else 5.dp)
                )
            }
            // DropdownMenu para mostrar opciones
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.padding(if (isTablet) 15.dp else 8.dp)
                    //.align(alignment = Alignment.CenterVertically)
//                    .background(
//                        color = Color(0xFF405084)
//                    )
            ) {
                DropdownMenuItem(
                    onClick = {},
                    text = { Text("Perfil") },
                    )
                DropdownMenuItem(
                    onClick = {},
                    text = { Text("Cerrar sesión") },
                )
            }
        }
    }
}