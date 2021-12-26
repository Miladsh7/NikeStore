package com.miladsh7.nikestore.data.repo.source

import com.miladsh7.nikestore.data.Banner

import io.reactivex.Single

interface BannerDataSource {
    fun getBanners():Single<List<Banner>>
}