package com.axelblatt.vktask1.network.data

import com.axelblatt.vktask1.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val productsRepository: ProductsRepository
}

class DefaultAppContainer : AppContainer {
    private val baseUrl = "https://dummyjson.com"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    override val productsRepository: ProductsRepository by lazy {
        NetworkProductsRepository(retrofitService)
    }

}