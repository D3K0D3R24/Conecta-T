package com.example.conectate_movil.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitIntance {

    const val BASE_URL = "http://conectat247.atwebpages.com/apis/api-rest/" //url base en conectate
//    const val BASE_URL = "http://44.193.42.74/apis/api-rest/"

    //url base para api-rest local
    //const val BASE_URL = "http://192.168.4.217/apis/api-rest/"

    private val retrofit by lazy {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)

        // Aquí es donde se usa OkHttp3
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)  // Se pasa el cliente OkHttp3 a Retrofit
            .build()
    }

    //es con la que accedemos a la api con retrofit a traves de la url
    val api: ApiService by lazy { //se crea una instancia de la api con retrofit
        retrofit.create(ApiService::class.java)
    }

}