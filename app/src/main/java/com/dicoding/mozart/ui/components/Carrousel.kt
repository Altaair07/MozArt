package com.dicoding.mozart.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.dicoding.mozart.model.Place


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(places: List<Place>, modifier: Modifier = Modifier) {
    val pagerState = rememberPagerState()

    Box(modifier = modifier.fillMaxSize()) {
        HorizontalPager(
            pageCount = places.size,
            state = pagerState,
            key = { places[it].id!!},
            pageSize = PageSize.Fill
        )

        { index ->
            Image(
                painter = painterResource(id = places[index].image!!),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

        }
    }
}

