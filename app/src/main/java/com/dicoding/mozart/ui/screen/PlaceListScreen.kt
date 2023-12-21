package com.dicoding.mozart.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dicoding.mozart.R
import com.dicoding.mozart.model.Place

@Composable
fun PlaceListScreen(
    navController: NavHostController
) {
//    LazyRow {
//        items(dummyJakartaPusatPlace) { place ->
//            Cart(
//                modifier = Modifier
//                    .padding(8.dp),
//                place = place,
//                onItemClick = {
//                }
//            )
//        }
//    }
}

@Composable
fun navigateToDetailScreen(navHostController: NavHostController,place: Place) {
     navHostController.navigate("destination_screen/${place.id}")
}