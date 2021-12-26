package com.miladsh7.nikestore.data.repo

import com.miladsh7.nikestore.data.Banner

import io.reactivex.Single

interface BannerRepository {
    fun getBanners():Single<List<Banner>>
}