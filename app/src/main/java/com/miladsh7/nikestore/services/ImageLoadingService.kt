package com.miladsh7.nikestore.services

import com.miladsh7.nikestore.view.NikeImageView

interface ImageLoadingService {
    fun load(imageView: NikeImageView, imageUrl: String)
}