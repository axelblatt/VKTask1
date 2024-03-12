package com.axelblatt.vktask1

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.axelblatt.vktask1.network.viewmodel.ProductsUiState
import com.axelblatt.vktask1.network.viewmodel.ProductsViewModel
import com.axelblatt.vktask1.screens.ErrorScreen
import com.axelblatt.vktask1.screens.InfoScreen
import com.axelblatt.vktask1.screens.LoadingScreen
import com.axelblatt.vktask1.screens.ProductsScreen

@Composable
fun ProductsApp(
    modifier: Modifier = Modifier
) {
    // ViewModel
    val productsViewModel: ProductsViewModel = viewModel(factory = ProductsViewModel.Factory)

    // Scroll state
    val feedScrollState = rememberLazyListState()

    // Back
    val back: MutableState<Boolean> = remember { mutableStateOf(false) }
    if (back.value) {
        productsViewModel.productsUiState = ProductsUiState.Success
        back.value = false
    }

    // Surface
    Surface(
        modifier = modifier.fillMaxSize()
    ) {
        when (productsViewModel.productsUiState) {
            is ProductsUiState.Loading -> LoadingScreen(modifier)
            is ProductsUiState.Disconnected ->
                ErrorScreen({ productsViewModel.get() }, modifier)
            is ProductsUiState.Info -> {
                InfoScreen(
                    productsViewModel.products[(productsViewModel.productsUiState as ProductsUiState.Info).id],
                    back, modifier
                )
            }
            else -> {
                ProductsScreen(
                    productsViewModel,
                    feedScrollState,
                    modifier
                )
            }
        }
    }
}