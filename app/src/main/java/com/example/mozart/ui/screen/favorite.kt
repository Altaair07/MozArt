package com.example.mozart.ui.screen

import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mozart.model.Place
import com.example.mozart.ui.components.Cart
import com.example.mozart.ui.theme.MozArtTheme





//@Composable
//
//fun FavoriteContent(
//    modifier: Modifier = Modifier,
//    listPlace: List<Place>,
//){
//    Text(text = "Favorite")
//    Column {
//        LazyVerticalGrid(
//            columns = GridCells.Fixed(2),
//            modifier = modifier,
//            contentPadding = PaddingValues(16.dp),
//            verticalArrangement = Arrangement.spacedBy(16.dp),
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            items(listPlace, key = { it.title!! }) { place ->
//                Cart(modifier, place, on)
//            }
//        }
//    }
//}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MozArtTheme {
    }
}