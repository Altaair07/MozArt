package com.example.mozart.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mozart.model.Place
import com.example.mozart.ui.components.Cart
import com.example.mozart.ui.components.Search
import com.example.mozart.ui.components.Title

@Composable
fun Banner(){
    Column() {
        Title()
        Search()
    }
}
