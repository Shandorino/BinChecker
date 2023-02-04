package com.shandorino.binchecker.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.shandorino.binchecker.data.model.Card

@Composable
fun Card(card: Card, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
            .clip(CircleShape)
            .background(Color(0xFFA8A8A8))
            .clickable { onClick() }

    ) {
        Text(text = card.bin, fontSize = 30.sp, modifier = Modifier.padding(start = 10.dp))
    }
}