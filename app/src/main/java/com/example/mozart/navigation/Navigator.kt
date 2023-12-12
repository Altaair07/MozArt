package com.example.mozart.navigation

import com.example.mozart.model.Place

interface Navigator {
    fun navigateToDetailsScreen(place: Place)
}