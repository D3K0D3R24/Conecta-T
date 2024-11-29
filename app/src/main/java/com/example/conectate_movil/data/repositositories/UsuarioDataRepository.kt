package com.example.conectate_movil.data.repositositories

import com.example.conectate_movil.data.api.RetrofitIntance
import com.example.conectate_movil.data.models.UsuarioGETLogin
import com.example.conectate_movil.data.models.UsuariosGET
import com.example.conectate_movil.data.models.UsuariosPOST

class UsuarioDataRepository {

    suspend fun getUserAppByEmail(correo_electronico: String): UsuariosGET {
        return RetrofitIntance.api.getUserAppByEmail(
            id = 0,
            correo_electronico
        )
    }

    suspend fun postCrearUsuario(
        nombre_usuario: String,
        alias: String,
        correo_electronico: String,
        password: String,
        fk_cliente: Int
    ): UsuariosPOST {
        return RetrofitIntance.api.postCrearUsuario(
            nombre_usuario = nombre_usuario,
            alias = alias,
            correo_electronico = correo_electronico,
            password = password,
            activo = 1,
            fk_cliente = fk_cliente
        )
    }

    suspend fun getUsuarioforLogin(correo_electronico: String): UsuarioGETLogin {
        return RetrofitIntance.api.getUsuarioforLogin(
            correo_electronico
        )
    }

}
