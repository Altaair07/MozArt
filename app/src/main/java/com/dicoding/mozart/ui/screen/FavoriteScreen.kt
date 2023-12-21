package com.dicoding.mozart.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dicoding.mozart.room.FavoritePlace
import com.dicoding.mozart.room.FavoritePlacesViewModel

@Composable
fun FavoriteScreen(navHostController: NavHostController) {
    val viewModel: FavoritePlacesViewModel = viewModel()
    val favoritePlaces by viewModel.favoritePlaces.observeAsState(emptyList())

    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        columns = GridCells.Fixed(2)
    ) {
        if (favoritePlaces.isNotEmpty()) {
            itemsIndexed(favoritePlaces) { index, item ->
                RowItem(item = item, navHostController)
            }
        } else {
            item {
                Text(
                    text = "Tidak Ada Data",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(26.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun RowItem(item: FavoritePlace, navHostController: NavHostController){
    Column (
        modifier = Modifier
            .height(250.dp)
            .width(200.dp)
            .clickable {
                Log.d("CLICK", item.toString())
                navHostController.navigate("detailfav/${item.category}/${item.name}/${item.id}")
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        AsyncImage(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(30.dp)),
            model = item.imageUrl,
            contentDescription = item.name,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = item.name, fontWeight = FontWeight.SemiBold)

    }
}

