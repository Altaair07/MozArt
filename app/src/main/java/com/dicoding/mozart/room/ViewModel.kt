package com.dicoding.mozart.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritePlacesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FavoritePlaceRepository
    private val _favoritePlaces = MutableLiveData<List<FavoritePlace>>()
    val favoritePlaces: LiveData<List<FavoritePlace>> get() = _favoritePlaces

    init {
        val dao = AppDatabase.getDatabase(application).favoritePlaceDao()
        repository = FavoritePlaceRepository(dao)
        loadFavoritePlaces()
    }

    fun saveFavoritePlace(name: String, imageUrl: String, category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val favoritePlace = FavoritePlace(name = name, imageUrl = imageUrl, category = category)
            repository.insert(favoritePlace)
            loadFavoritePlaces()
        }
    }

    private fun loadFavoritePlaces() {
        viewModelScope.launch(Dispatchers.IO) {
            val places = repository.getAllFavoritePlaces()
            _favoritePlaces.postValue(places)
        }
    }
    fun deleteFavoritePlaceById(placeId: Long) {
        viewModelScope.launch {
            repository.deleteById(placeId)
        }
    }
}