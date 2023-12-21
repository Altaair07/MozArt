package com.dicoding.mozart.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoritePlaceDao {
    @Insert
    suspend fun insert(place: FavoritePlace)

    @Query("SELECT * FROM favorite_places")
    suspend fun getAllFavoritePlaces(): List<FavoritePlace>

    @Query("DELETE FROM favorite_places WHERE id = :placeId")
    suspend fun deleteById(placeId: Long)
}