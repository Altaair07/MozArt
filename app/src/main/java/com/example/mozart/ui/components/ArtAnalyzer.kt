package com.example.mozart.ui.components

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.example.mozart.model.domain.ArtClassifier
import com.example.mozart.model.domain.Classification

class ArtAnalyzer (
    private val classifier: ArtClassifier,
    private val onResult: (List<Classification>) -> Unit
): ImageAnalysis.Analyzer {

    private var frameSkipCounter = 0

    override fun analyze(image: ImageProxy) {
        if (frameSkipCounter % 60 == 0){
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image
                .toBitmap()
            val results = classifier.classify(bitmap, rotationDegrees)
            onResult(results)
        }
        frameSkipCounter++

        image.close()


    }
}