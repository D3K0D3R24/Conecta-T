package com.example.conectate_movil.data.repositositories

import com.example.conectate_movil.data.api.RetrofitIntance
import com.example.conectate_movil.data.models.Clientes

class ClienteDataRepository {

    //recive un correo y devuelve una lista de clientes
    suspend fun getClienteByEmail(correo_electronico: String): Clientes {
        //se comunica con la ApiService y se obtiene la lista de clientes
        return RetrofitIntance.api.getClienteByEmail(
                correo_electronico
        )
    }

    suspend fun getClienteById(id: Int): Clientes {
        return RetrofitIntance.api.getClienteById(id)
    }
}