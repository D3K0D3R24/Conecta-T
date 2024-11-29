package com.example.conectate_movil.ui.screens.dashboard.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.conectate_movil.R
import com.example.conectate_movil.dashboard.ui.components.OpcionesTitulo

// Opciones de pago y soporte
@Composable
fun Opciones(isTablet: Boolean, poppinsFontFamily: FontFamily) {
    val context = LocalContext.current

    Column{

        // Pagos y recibos
        OpcionesTitulo(
            isTablet = isTablet,
            texto = "Pagos y recibos",
            color = Color(0xFF8195D8),
            poppinsFontFamily = poppinsFontFamily
        )

        // Espacio
        Spacer(modifier = Modifier.height(if (isTablet) 40.dp else 30.dp))

        // Historial de pagos
        OpcionesCard(
            isTablet = isTablet,
            image = R.drawable.baseline_calendar_month_24,
            title = "Historial de pagos",
            color = Color(0xFFFFFADB),
            poppinsFontFamily = poppinsFontFamily,
            colorLetra = Color(0xFF8195D8),
            onClick = {
                Toast.makeText(context, "Próximamente Historial de pagos", Toast.LENGTH_SHORT).show()
            }
        )

        // Espacio
        Spacer(modifier = Modifier.height(if (isTablet) 40.dp else 30.dp))

        // Ayuda y soporte
        OpcionesTitulo(
            isTablet = isTablet,
            texto = "Ayuda y soporte",
            color = Color(0xFF945C96),
            poppinsFontFamily = poppinsFontFamily
        )

        // Espacio
        Spacer(modifier = Modifier.height(if (isTablet) 40.dp else 30.dp))

        // Preguntas frecuentes
        OpcionesCard(
            isTablet = isTablet,
            image = R.drawable.baseline_forum_24,
            title = "Preguntas frecuentes",
            color = Color(0xFFFFEAF9),
            poppinsFontFamily = poppinsFontFamily,
            colorLetra = Color(0xFF8195D8),
            onClick = {
                Toast.makeText(context, "Próximamente Preguntas frecuentes", Toast.LENGTH_SHORT).show()
            }
        )

        // Espacio
        Spacer(modifier = Modifier.height(if (isTablet) 40.dp else 30.dp))

        // Solicitar soporte Técnico
        OpcionesCard(
            isTablet = isTablet,
            image = R.drawable.baseline_support_agent,
            title = "Solicitar soporte Técnico",
            color = Color(0xFFE0FAFF),
            poppinsFontFamily = poppinsFontFamily,
            colorLetra = Color(0xFF8195D8),
            onClick = {
                Toast.makeText(context, "Próximamente Solicitar soporte Técnico", Toast.LENGTH_SHORT).show()
            }
        )

        // Espacio
        Spacer(modifier = Modifier.height(if (isTablet) 40.dp else 30.dp))

        // Contacta WhatsApp
        OpcionesCard(
            isTablet = isTablet,
            image = R.drawable.baseline_whatsapp_24,
            title = "Contacta con nuestro soporte vía WhatsApp",
            color = Color(0xFFE0FFE4),
            poppinsFontFamily = poppinsFontFamily,
            colorLetra = Color(0xFF8195D8),
            onClick = {
                Toast.makeText(context, "Próximamente Contacta con nuestro soporte vía WhatsApp", Toast.LENGTH_SHORT).show()
            }

        )
    }
}