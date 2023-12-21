package com.dicoding.mozart.navigation

import com.dicoding.mozart.model.Place

interface Navigator {
    fun navigateToDetailsScreen(place: Place)
}