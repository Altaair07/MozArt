package com.dicoding.mozart.ui.components

import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.dicoding.mozart.model.domain.ArtClassifier
import com.dicoding.mozart.model.domain.Classification

class ArtImageAnalyzer(
    private val classifier: ArtClassifier,
    private val onResults: (List<Classification>) -> Unit
): ImageAnalysis.Analyzer {
    private var frameSkipCounter = 0


    override fun analyze(image: ImageProxy) {
        if(frameSkipCounter % 60 == 0){
            val rotationDegrees = image.imageInfo.rotationDegrees
            val bitmap = image
                .toBitmap()
                .centerCrop(224, 224)

            val results = classifier.classify(bitmap, rotationDegrees)
            onResults(results)
        }
        frameSkipCounter++

        image.close()
    }

}