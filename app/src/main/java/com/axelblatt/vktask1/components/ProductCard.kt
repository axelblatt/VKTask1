package com.axelblatt.vktask1.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.axelblatt.vktask1.network.model.Product
import com.axelblatt.vktask1.network.viewmodel.ProductsUiState
import com.axelblatt.vktask1.network.viewmodel.ProductsViewModel
import com.axelblatt.vktask1.screens.InfoScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCard(
    modifier: Modifier,
    product: Product,
    productsViewModel: ProductsViewModel
) {
    Card(
        modifier = modifier.fillMaxWidth().height(160.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF6F6F6)
        ),
        onClick = { productsViewModel.productsUiState = ProductsUiState.Info(product.id!!-1) } // clickedProduct = product.id!!
    ) {
        Row(
            modifier = modifier.fillMaxSize()
        ) {
            // Thumbnail
            AsyncImage(
                modifier = modifier.width(140.dp).height(160.dp),
                model = product.thumbnail,
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
            Column(
                modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp)
            ) {
                // Title
                Text(
                    text = product.title!!,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(600)
                )
                // Rating
                Text(
                    text = product.category + " • ★ " + product.rating!!,
                    fontSize = 12.sp
                )
                // Description
                Text(
                    text = product.description!!
                )
            }
        }
    }

}