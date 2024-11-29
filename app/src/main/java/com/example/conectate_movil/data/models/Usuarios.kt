package com.example.conectate_movil.data.models

data class UsuariosPOST(
    val message: String, // CORRECTO o  ERROR
    val status: String, //    B4C5   o  D204
    val fk_cliente: Int
)

//message = "E001" // significa que ya existe
//message = "A102" // significa que no existe
data class UsuariosGET(
    val message: String
)

data class UsuarioGETLogin(
    val nombre_usuario: String,
    val alias: String,
    val password: String,
    val fk_cliente: Int
)