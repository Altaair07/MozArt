package com.dicoding.mozart.ui.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.dicoding.mozart.room.FavoritePlacesViewModel
import com.dicoding.mozart.ui.model.Data

@Composable
fun Cart(
    category: String,
    modifier: Modifier = Modifier,
    place: Data,
    onItemClick: (Data) -> Unit
) {
    val viewModel: FavoritePlacesViewModel = viewModel()
    val favoritePlaces by viewModel.favoritePlaces.observeAsState(emptyList())
    val context = LocalContext.current
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
                    model = place.image!!,
                    contentDescription = place.museumName,
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
                            text = place.museumName,
                            color = Color.White,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            maxLines = 2, // Set the maximum number of lines
                            overflow = TextOverflow.Ellipsis, // Truncate with ellipsis if overflow
                            modifier = Modifier
                                .fillMaxWidth()
                                .widthIn(max = 206.dp)
                                .background(
                                    color = Color(0xFF4D5652),
                                    shape = RoundedCornerShape(size = 20.dp)
                                ).padding(all = 5.dp)
                        )
                    }
                }

                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .padding(6.dp),
                    contentAlignment = Alignment.TopStart,
                ) {
                    IconButton(

                        onClick = {
                            Log.d("Press", "Data $place")
                            Toast.makeText(context, "Penambahan Favorit Berhasil!",Toast.LENGTH_SHORT).show()
                            viewModel.saveFavoritePlace(place.museumName ?: "", place.image ?: "", category)
                        },
                        modifier = Modifier
                            .background(
                                color = Color(0xFFFFFFFF),
                                shape = CircleShape
                            )
                            .padding(all = 1.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Love",
                            tint = Color.Red
                        )
                    }
                }
            }
        }
    }
}
