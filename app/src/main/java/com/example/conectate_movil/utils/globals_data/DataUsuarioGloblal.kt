package com.example.conectate_movil.utils.globals_data

import com.example.conectate_movil.data.models.UsuarioGETLogin

//clase global para guardar los datos del Usuario obtenidos de la api por email
object DataUsuarioGloblal {

    private var currentUser: UsuarioGETLogin?=null

    fun getUsuario():UsuarioGETLogin?{
        return currentUser
    }

    fun setUsuario(user:UsuarioGETLogin){
        currentUser = user
    }

}