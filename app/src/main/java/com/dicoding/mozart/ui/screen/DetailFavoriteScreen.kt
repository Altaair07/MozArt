package com.dicoding.mozart.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dicoding.mozart.ui.components.HomeSection
import com.dicoding.mozart.ui.retrofit.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.mozart.room.FavoritePlacesViewModel
import com.dicoding.mozart.ui.components.CardBarang
import com.dicoding.mozart.ui.model.detail.DataDetail

@Composable
fun DetailFavoriteScreen(
    category : String = "",
    name : String = "",
    id : String = "",
    modifier: Modifier = Modifier,
    navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, top = 40.dp, bottom = 120.dp)
            .verticalScroll(rememberScrollState())
    ) {
        PostListFavorite(navHostController, category, name, id);

    }
}


@Composable
fun PostListFavorite(navHostController: NavHostController, category: String, name: String, id: String) {
    var detailList by remember {
        mutableStateOf(listOf<DataDetail>())
    }
    val context = LocalContext.current
    val viewModel: FavoritePlacesViewModel = viewModel()
    val favoritePlaces by viewModel.favoritePlaces.observeAsState(emptyList())

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true){
        Log.d("logg", "mulai")
        scope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getMuseumDetail(name, category)
            }catch (e: java.io.IOException){
                Log.d("toast", e.message.toString())
                return@launch
            }
            Log.d("logg", "berhasil")
            if (response.isSuccessful && response.body() != null){
                withContext(Dispatchers.Main){
                    Log.d("logg", response.body().toString())
                    detailList = response.body()!!.data

                }
            }
        }
    }

    if (detailList.isNotEmpty()) {
        detailList.forEach { detailItem ->
            AsyncImage(
                model = detailItem.image,
                contentDescription = detailItem.museumName,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(390.dp)
                    .clip(RoundedCornerShape(30.dp)),
            )
            Text(
                text = detailItem.museumName,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 5.dp, top = 20.dp)
            )

            if (detailItem.description != null) {
                Text(
                    text = detailItem.description,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 5.dp, top = 20.dp)
                )
            }
            Text(
                    text = detailItem.alamat,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 5.dp, top = 20.dp)
            )
            Button(onClick = {
                Log.d("PLACEEE", id)
                viewModel.deleteFavoritePlaceById(id.toLong())
                Toast.makeText(context, "Data Berhasil Dihapus!",Toast.LENGTH_SHORT).show()
                navHostController.navigate("fav")
            }) {
                Text("Hapus Favorit")
            }
        }
        HomeSection(title = "", Modifier) {
            MenuRowFaforiteDetail(detailList[0].items) { selectedPlace ->
                Log.d("PLACEEE", "Place $selectedPlace")
//                navHostController.navigate("")
            }
        }
    } else {
        // Handle the case when the list is empty
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .wrapContentSize(Alignment.BottomCenter)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    .height(64.dp),
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        }

    }

}
@Composable
fun MenuRowFaforiteDetail(
    listMenu: List<com.dicoding.mozart.ui.model.detail.Item>,
    modifier: Modifier = Modifier,
    onItemClick: (com.dicoding.mozart.ui.model.detail.Item) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listMenu, key = { it.id!! }) { place ->
            CardBarang(modifier, place, onItemClick)
        }
    }
}


