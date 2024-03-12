package com.axelblatt.vktask1.network


import com.axelblatt.vktask1.network.model.ServiceResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/products")
    suspend fun getResponse(
        @Query("skip") skip: Int,
        @Query("limit") limit: Int = 20
    ): ServiceResponse
}