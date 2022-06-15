package com.example.myapplication.network


import com.example.myapplication.data.OrderX
import retrofit2.Response
import retrofit2.http.GET

interface InterfaceService {

    @GET(ORDER_PATH)
    suspend fun getOrders(): Response<List<OrderX>>

    companion object {
        const val BASE_URL = "https://raw.githubusercontent.com/pgiani/KotlinTask/main/"
        private const val ORDER_PATH = "order.json"
    }
}