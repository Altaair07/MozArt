package com.dicoding.mozart.ui.model.detail

data class DataDetail(
    val alamat: String,
    val description: String,
    val id: Int,
    val image: String,
    val items: List<Item>,
    val museumName: String
)