package com.dicoding.mozart.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.dicoding.mozart.model.Place
import com.dicoding.mozart.ui.model.detail.DataDetail
import com.dicoding.mozart.ui.model.detail.Item

@Composable
fun CardBarang(
    modifier: Modifier = Modifier,
    place: Item,
    onItemClick: (Item) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(),
        ) {
            Box(
                modifier = modifier
                    .height(240.dp)
                    .clickable {
                        onItemClick(place)
                    }
            ) {
                AsyncImage(
                    model = place.image,
                    contentDescription = place.name,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(240.dp)
                )

                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black
                                ),
                                startY = 240f,
                            )
                        ),
                )
                Box(
                    modifier = modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomStart,
                ) {
                    Column(
                        modifier = modifier
                            .padding(16.dp)
                    ) {
                        Text(
                            text = place.name,
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            maxLines = 2, // Set the maximum number of lines
                            overflow = TextOverflow.Ellipsis, // Truncate with ellipsis if overflow
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = 206.dp)
                        )
                    }
                }
            }
        }
    }
}