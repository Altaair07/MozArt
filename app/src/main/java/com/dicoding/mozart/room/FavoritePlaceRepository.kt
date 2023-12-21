package com.dicoding.mozart.room

class FavoritePlaceRepository(private val dao: FavoritePlaceDao) {
    suspend fun insert(place: FavoritePlace) {
        dao.insert(place)
    }

    suspend fun getAllFavoritePlaces(): List<FavoritePlace> {
        return dao.getAllFavoritePlaces()
    }
    suspend fun deleteById(placeId: Long) {
        dao.deleteById(placeId)
    }
}