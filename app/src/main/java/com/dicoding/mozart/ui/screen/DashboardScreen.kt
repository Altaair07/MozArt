package com.dicoding.mozart.ui.screen

import android.content.Context
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.dicoding.mozart.ui.components.Cart
import com.dicoding.mozart.ui.components.HomeSection
import com.dicoding.mozart.ui.model.Data
import com.dicoding.mozart.ui.model.MuseumItem
import com.google.gson.Gson
import kotlinx.coroutines.delay
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun DashboardScreen(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    memesList: List<MuseumItem>
) {
        val context = LocalContext.current
        val onBack = { displayToast(context) }
    var backPressedCount by remember { mutableStateOf(0) }

    val onBackPressed: () -> Unit = {
        if (backPressedCount == 1) {
//            Toast.makeText(context, "Exiting the application", Toast.LENGTH_SHORT).show()
            System.exit(0)
        } else {
            backPressedCount++
            Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
        }
    }

    BackPressHandler(onBackPressed = onBackPressed)
        Column(modifier = modifier
        .fillMaxSize()
        .padding(start = 15.dp, top = 40.dp, bottom = 50.dp)) {

        val textState = remember {
            mutableStateOf(TextFieldValue(""))
        }
        Text(text = "Explore", style = MaterialTheme.typography.bodyMedium, fontSize = 14.sp)
        Text(text = "MozArt", style = MaterialTheme.typography.headlineMedium, fontSize = 32.sp)
        SearchView(state = textState, placeholder = "Search here ...", modifier = modifier)
        val searchedText = textState.value.text
        LazyColumn(modifier = modifier
        .fillMaxSize()
        .padding(bottom = 50.dp)) {
            items(items = memesList.filter {
                it.id.contains(searchedText, ignoreCase = true)
            }, key = { it.id }) { item ->
                var namadaerah = item.id;
                namadaerah = namadaerah.split("-")
                    .joinToString(" ") { it.capitalize() }
                HomeSection(title = namadaerah, Modifier) {
                    MenuRow(item.data, item.id) { selectedPlace ->
                        val gson = Gson()
                        var myObjectString = gson.toJson(selectedPlace, Data::class.java)
                        var encode = URLEncoder.encode(myObjectString, StandardCharsets.UTF_8.toString())
                        var encodeimage = URLEncoder.encode(selectedPlace.image, StandardCharsets.UTF_8.toString())
                        navHostController.navigate("detail/$encode/$encodeimage/${item.id}")
                    }
                }
            }
        }
    }
}
fun displayToast(context: Context) {
    Toast.makeText(context, "Back press intercepted", Toast.LENGTH_SHORT).show()
}
@Composable
fun BackPressHandler(
    backPressedDispatcher: OnBackPressedDispatcher? =
        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
    onBackPressed: () -> Unit
) {
    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)

    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }

    DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher?.addCallback(backCallback)

        onDispose {
            backCallback.remove()
        }
    }
}
@Composable
fun MenuRow(
    listMenu: List<Data>,
    category: String,
    modifier: Modifier = Modifier,
    onItemClick: (Data) -> Unit,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ) {
        items(listMenu, key = { it.id!! }) { place ->
            Cart(category, modifier, place, onItemClick)
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    state: MutableState<TextFieldValue>,
    placeholder: String,
    modifier: Modifier) {
    TextField(value = state.value, onValueChange = {value->
        state.value = value
    },
        modifier
            .fillMaxWidth()
            .padding(top = 20.dp, end = 10.dp, start = 10.dp)
            .clip(RoundedCornerShape(15.dp)),
        placeholder = {
            Text(text = "Search From The Area")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color(0xffebeaf4)
        ),
        maxLines = 1,
        singleLine = true,
        textStyle = TextStyle(color = Color.Black)
    )

}