package com.example.conectate_movil.data.models

// class Plan
data class Plan(
    val id: Int,
    val alias: String,
    val nombre_paquete: String,
    val precio: Float,
    val velocidad_paquete: String,
    val fecha_pago: String,
    val fecha_inicio_mensualidad: String,
    val fecha_fin_mensualidad: String,
    val estado_pago: String,
)