package com.miladsh7.nikestore.data.repo.source

import com.miladsh7.nikestore.data.Banner
import com.miladsh7.nikestore.services.http.ApiService

import io.reactivex.Single

class BannerRemoteDataSource(val apiService: ApiService) : BannerDataSource {
    override fun getBanners(): Single<List<Banner>> = apiService.getBanners()
}