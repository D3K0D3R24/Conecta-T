package com.example.conectate_movil.utils.globals_data

import com.example.conectate_movil.data.models.Clientes

//clase global para guardar los datos del cliente obtenidos de la api por email o id
object DataClientGlobal {
    private var sharedClient: Clientes?=null

    fun getClient(): Clientes?{
        return sharedClient
    }

    fun setClient(client: Clientes){
        sharedClient = client
    }

}