package com.example.mozart.model.domain

import android.graphics.Bitmap

interface ArtClassifier {
    fun classify(bitmap: Bitmap, rotation:Int): List<Classification>

}