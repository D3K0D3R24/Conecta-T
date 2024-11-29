package com.example.conectate_movil.data.api

import com.example.conectate_movil.data.models.Clientes
import com.example.conectate_movil.data.models.UsuarioGETLogin
import com.example.conectate_movil.data.models.UsuariosGET
import com.example.conectate_movil.data.models.UsuariosPOST
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    /*
    * NOTA: Aqui se establece el endpoint de la api que se va a consumir. y la solicitud a la api
    *
    * recuerda que para que la api funcione se debe de tener el endpoint establecido o en nombre de la api con extension .php
    *
    *
    * si estas peticiones esperaran que retorne un json se deben de guardar en una objeto de la clase o una lista de objetos
    *
    *
    * en la carpeta models se establecen los datos que se esperan que retorne la api
    *
    * */



    //-------------------- Tabla Clientes --------------------
    //ENDPOINT API o el php que se encuentra dentro de api-rest

    @GET("get_Client_by_email.php")
    //funcion o peticion a la api con los parametros
    suspend fun getClienteByEmail(
        @Query("email") correo_electronico: String
    ): Clientes//se espera a que retorne una lista de objetos o json si que los guardo en una lista de objetos


    //peticion a la api pero no existe todavia esta api
    @GET("get_Client_by_id.php")
    suspend fun getClienteById(@Query("id_cliente") id: Int): Clientes


    //-------------------- Tabla Usuario --------------------
    //para tabla usuario
    @GET("client_validation_in_usuarios_app.php")
    suspend fun getUserAppByEmail(
        @Query("id") id: Int,
        @Query("email") correo_electronico: String
    ): UsuariosGET

    @POST("post_crear_usuario.php")
    suspend fun postCrearUsuario(
        @Query("nombre_usuario") nombre_usuario: String,
        @Query("alias") alias:String,
        @Query("email") correo_electronico: String,
        @Query("password") password: String,
        @Query("activo") activo: Int,
        @Query("fk_cliente") fk_cliente: Int
    ): UsuariosPOST

    //metodo get para obtener datos del ususario para el login
    @GET("verificar_usuarioAPP.php")
    suspend fun getUsuarioforLogin(
        @Query("email") correo_electronico: String
    ): UsuarioGETLogin




}