package com.dicoding.mozart.ui.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dicoding.mozart.ui.model.Item
import com.dicoding.mozart.ui.components.HomeSection
import com.dicoding.mozart.ui.retrofit.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import com.dicoding.mozart.ui.components.CardBarang
import com.dicoding.mozart.ui.model.detail.DataDetail

@Composable
fun DetailScreen(
    id: Int = 0,
    nama: String = "",
    category: String = "",
    description: String? = null,
    image: String? = "",
    alamat: String = "",
    item: List<Item>,
    modifier: Modifier = Modifier,
    navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, top = 40.dp, bottom = 120.dp)
            .verticalScroll(rememberScrollState())
    ) {
            AsyncImage(
                model = image!!,
                contentDescription = nama,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(390.dp)
                    .clip(RoundedCornerShape(30.dp)),
            )
            Text(
                text = nama,
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 5.dp, top = 20.dp)
            )

        if (description != null) {
            Text(
                text = description,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 5.dp, top = 20.dp)
            )
        }
        Text(
                text = alamat,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 5.dp, top = 20.dp)
        )
        PostList(navHostController, nama, category);

    }
}


@Composable
fun PostList(navHostController: NavHostController, nama: String, category: String) {
    var detailList by remember {
        mutableStateOf(listOf<DataDetail>())
    }
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true){
        Log.d("logg", "mulai")
        scope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getMuseumDetail(nama, category)
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
        HomeSection(title = "", Modifier) {
            MenuRowDetail(detailList[0].items) { selectedPlace ->
                Log.d("PLACEEE", "Place $selectedPlace")
//                navHostController.navigate("")
            }
        }
    } else {
        // Handle the case when the list is empty
        Text(text = "Barang Tidak Tersedia")
    }

}
@Composable
fun MenuRowDetail(
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


