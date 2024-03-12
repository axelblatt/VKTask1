package com.axelblatt.vktask1.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.axelblatt.vktask1.R
import com.axelblatt.vktask1.network.model.Product

@Composable
fun InfoScreen(
    product: Product,
    back: MutableState<Boolean>? = null,
    modifier: Modifier
) {
    BackHandler(enabled = true) {
        if (back != null)
            back.value = true
    }

    val scrollState = rememberScrollState()

    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(240.dp)
                .horizontalScroll(scrollState)
        ) {
            AsyncImage(
                modifier = modifier.fillMaxSize(),
                model = product.thumbnail,
                contentScale = ContentScale.FillHeight,
                contentDescription = null
            )
            for (i in product.images) {
                Spacer(modifier = modifier.size(10.dp))
                AsyncImage(
                    modifier = modifier.fillMaxSize(),
                    model = i,
                    contentScale = ContentScale.FillHeight,
                    contentDescription = null
                )
            }
        }
        Column(
            modifier = modifier
                .padding(15.dp)
                .fillMaxSize(),
            // verticalArrangement = Arrangement.Center,
            // horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = product.title!!,
                fontSize = 30.sp,
                fontWeight = FontWeight(600),
                // textAlign = TextAlign.Center
            )
            // Category
            Text(
                text = product.category!!,
                fontWeight = FontWeight(600),
                fontSize = 16.sp,
                color = Color(0xFF646464)
                // textAlign = TextAlign.Center
            )
            // Rating
            Text(
                text = "★ " + product.rating!!,
                fontSize = 20.sp
            )
            Spacer(modifier = modifier.size(20.dp))

            // Price
            Column(
                modifier = modifier.width(160.dp)
                    .height(70.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(Color(0xFFDAD9D7)),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = modifier.padding(start = 15.dp),
                    text = "$" + product.price.toString(),
                    fontWeight = FontWeight(600),
                    fontSize = 36.sp
                )
            }

            Spacer(modifier = modifier.size(20.dp))
            // Description
            Text(
                text = stringResource(R.string.description),
                fontWeight = FontWeight(600),
                fontSize = 16.sp
            )
            Text(
                text = product.description!!
            )
        }

    }
}