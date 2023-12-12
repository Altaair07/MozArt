package com.example.mozart.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.mozart.R
import com.example.mozart.model.Place
import com.example.mozart.navigation.Navigator
import com.example.mozart.ui.components.Cart

@Composable
fun PlaceListScreen(
    navController: NavHostController
) {
    LazyRow {
        items(dummyJakartaPusatPlace) { place ->
            Cart(
                modifier = Modifier
                    .padding(8.dp),
                place = place,
                onItemClick = {
                }
            )
        }
    }
}

@Composable
fun navigateToDetailScreen(navHostController: NavHostController,place: Place) {
     navHostController.navigate("destination_screen/${place.id}")
}

val dummyJakartaPusatPlace = listOf(
    Place(
        id = 1,
        image =   R.drawable.galnas,
        title =  "Galnas",
        description =   "Museum Nasional Indonesia, yang juga dikenal sebagai Museum Gajah, merupakan salah satu museum terbesar di Jakarta, Indonesia. Terletak di Jalan Medan Merdeka Barat, museum ini didirikan pada tahun 1778 dan menjadi salah satu institusi budaya utama di negara ini.",
    ),

    Place(
        2,
        R.drawable.atsiri,
        "Atsiri Museum Sarinah",
        "Museum Nasional Indonesia, yang juga dikenal sebagai Museum Gajah, merupakan salah satu museum terbesar di Jakarta, Indonesia. Terletak di Jalan Medan Merdeka Barat, museum ini didirikan pada tahun 1778 dan menjadi salah satu institusi budaya utama di negara ini.",
    ),
    Place(
        3,
        R.drawable.musenumnasional,
        "Museum Nasional",
        "Museum Nasional Indonesia, yang juga dikenal sebagai Museum Gajah, merupakan salah satu museum terbesar di Jakarta, Indonesia. Terletak di Jalan Medan Merdeka Barat, museum ini didirikan pada tahun 1778 dan menjadi salah satu institusi budaya utama di negara ini.",
    ),
    Place(
        4,
        R.drawable.bertelegalery,
        "Bertele Galery",
        "Museum Nasional Indonesia, yang juga dikenal sebagai Museum Gajah, merupakan salah satu museum terbesar di Jakarta, Indonesia. Terletak di Jalan Medan Merdeka Barat, museum ini didirikan pada tahun 1778 dan menjadi salah satu institusi budaya utama di negara ini.",
    )
)