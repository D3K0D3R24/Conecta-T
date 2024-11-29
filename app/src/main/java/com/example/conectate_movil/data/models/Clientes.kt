package com.example.conectate_movil.data.models

data class Clientes(
    val correo_electronico: String,
    val cp: Int,
    val created_at: String, //no se usan
    val direccion: String,
    val es_cliente: Int,
    val fk_paquete: Int,
    val id_cliente: Int,
    val municipio: String,
    val nombre_completo: String,
    val telefono: String,
    val updated_at: String//no se usan
)