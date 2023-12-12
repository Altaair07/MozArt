package com.example.mozart.ui.screen

import android.os.Bundle
import android.provider.Settings.Global.putInt
import android.provider.Settings.Global.putString
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.example.mozart.R
import com.example.mozart.model.Place
import com.example.mozart.model.PlaceDataSource
import com.example.mozart.navigation.Navigator
import com.example.mozart.navigation.Screen
import com.example.mozart.ui.components.Cart
import com.example.mozart.ui.components.HomeSection
import com.example.mozart.ui.components.Search
import com.ramcosta.composedestinations.navargs.DestinationsStringNavType.put
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun DashboardScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(bottom = 50.dp)) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(start = 15.dp, top = 40.dp, bottom = 50.dp)
        ) {
            Text(text = "Explore", style = MaterialTheme.typography.bodyMedium, fontSize = 14.sp)
            Text(text = "Mozart", style = MaterialTheme.typography.headlineMedium, fontSize = 32.sp)
            Search()
            HomeSection(title = stringResource(R.string.section_timur), Modifier) {
                MenuRow(PlaceDataSource.dummyJakartaTimurPlace) { selectedPlace ->
                    Log.d("PLACEEE", "Place $selectedPlace")
                    navHostController.navigate("detail/${selectedPlace.id}")
                }
            }

            HomeSection(title = stringResource(R.string.section_utara), Modifier) {
                MenuRow(PlaceDataSource.dummyJakartaBaratPlace) { selectedPlace ->
                    Log.d("PLACEEE", "Place $selectedPlace")
                    navHostController.navigate(
                        "detail/{$selectedPlace.id}",
                        navOptions {
                            Bundle().apply {
                                putBundle("detailArgs", Bundle().apply {
                                    putInt("id", selectedPlace.id!!)
                                    putString("title", selectedPlace.title)
                                    putString("description", selectedPlace.description)
                                    putInt("image", selectedPlace.image!!)
                                })
                            }
//                            putBundle("detailArgs", Bundle().apply {
//                                putInt("id", selectedPlace.id)
//                                putString("title", selectedPlace.title)
//                                putString("description", selectedPlace.description)
//                                putInt("image", selectedPlace.image)
//                            })
                        }
                    )
                }
            }
            HomeSection(title = stringResource(R.string.section_barat), Modifier) {
                MenuRow(PlaceDataSource.dummyJakartaBaratPlace){selectedPlace ->
                    Log.d("PLACEEE", "Place $selectedPlace")
                    navHostController.navigate("detail/{${selectedPlace.id}}")
                }
            }
            HomeSection(title = stringResource(R.string.section_selatan), Modifier) {
                MenuRow(PlaceDataSource.dummyJakartaSelatanPlace){selectedPlace ->
                    Log.d("PLACEEE", "Place $selectedPlace")
                    navHostController.navigate("detail/{${selectedPlace.id}}")
                }
            }
            HomeSection(title = stringResource(R.string.section_timur), Modifier) {
                MenuRow(PlaceDataSource.dummyJakartaTimurPlace){selectedPlace ->
                    Log.d("PLACEEE", "Place $selectedPlace")
                    navHostController.navigate("detail/{${selectedPlace.id}}")
                }
            }
        }
    }
}

@Composable
fun MenuRow(
    listMenu: List<Place>,
    modifier: Modifier = Modifier,
    onItemClick: (Place) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listMenu, key = { it.id!! }) { place ->
            Cart(modifier, place, onItemClick)
        }
    }
}
@Composable
@Preview(showSystemUi = true, showBackground = true)
fun previewDashboardScreen() {
    DashboardScreen(navHostController = rememberNavController())
}