package com.axelblatt.vktask1.network.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.axelblatt.vktask1.ProductsApplication
import com.axelblatt.vktask1.network.data.ProductsRepository
import com.axelblatt.vktask1.network.model.Product
import com.axelblatt.vktask1.network.model.ServiceResponse
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface ProductsUiState {
    data object Success : ProductsUiState
    data class Info(val id: Int) : ProductsUiState
    data object Loading : ProductsUiState
    data object Disconnected : ProductsUiState
}

class ProductsViewModel(
    private val productsRepository: ProductsRepository
) : ViewModel() {

    var products = mutableListOf<Product>()
    var productsUiState: ProductsUiState by mutableStateOf(ProductsUiState.Loading)
    var total: Int = 100
    var skipped: Int = -20
    var clickedProduct: Int = -1

    fun get() {
        viewModelScope.launch {
            if (skipped == -20) {
                productsUiState = ProductsUiState.Loading
            }
            productsUiState =
                try {
                    skipped += 20
                    val response = productsRepository.get(skipped)
                    products += response.products
                    total = response.total!!
                    ProductsUiState.Success
                }
                catch (e: IOException) {
                    e.printStackTrace()
                    skipped -= 20
                    ProductsUiState.Disconnected
                }
                catch (e: HttpException) {
                    e.printStackTrace()
                    skipped -= 20
                    ProductsUiState.Disconnected
                }
        }
    }

    init {
        get()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as ProductsApplication)
                val productsRepository = application.container.productsRepository
                ProductsViewModel(productsRepository = productsRepository)
            }
        }
    }
}