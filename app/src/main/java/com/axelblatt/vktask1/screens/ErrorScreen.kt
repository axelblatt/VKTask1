package com.axelblatt.vktask1.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.axelblatt.vktask1.R

@Composable
fun ErrorScreen(
    retryAction: () -> Unit,
    modifier: Modifier
) {
    Column (
        modifier = modifier.fillMaxSize().padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(R.string.error_message),
            fontSize = 20.sp, textAlign = TextAlign.Center, fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = modifier.size(10.dp))
        Button( onClick = retryAction )
        { Text(stringResource(R.string.retry)) }
    }

}