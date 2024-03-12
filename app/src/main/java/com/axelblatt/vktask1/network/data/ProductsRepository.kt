package com.axelblatt.vktask1.network.data

import com.axelblatt.vktask1.network.ApiService
import com.axelblatt.vktask1.network.model.ServiceResponse

interface ProductsRepository {
    suspend fun get(skip: Int) : ServiceResponse
}

class NetworkProductsRepository (
    private val service: ApiService
): ProductsRepository {
    override suspend fun get(skip: Int) : ServiceResponse = service.getResponse(skip)
}