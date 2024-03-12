package com.axelblatt.vktask1.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.axelblatt.vktask1.components.ProductCard
import com.axelblatt.vktask1.network.viewmodel.ProductsViewModel

@Composable
fun ProductsScreen(
    productsViewModel: ProductsViewModel,
    feedScrollState: LazyListState,
    modifier: Modifier
) {
    val initialDataLoaded = remember { mutableStateOf(false) }

    LaunchedEffect(productsViewModel) {
        if (!initialDataLoaded.value) {
            productsViewModel.get()
            initialDataLoaded.value = true
        }
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(10.dp),
        state = feedScrollState
    ) {
        itemsIndexed(productsViewModel.products) { index, product ->
            ProductCard(modifier, product, productsViewModel)
        }
        if (initialDataLoaded.value && feedScrollState.firstVisibleItemIndex + feedScrollState.layoutInfo.visibleItemsInfo.size >= productsViewModel.products.size) {
            if (productsViewModel.products.size != productsViewModel.total)
                productsViewModel.get()
        }
    }

}