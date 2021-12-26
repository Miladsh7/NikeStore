package com.miladsh7.nikestore.data.repo

import com.miladsh7.nikestore.data.Banner
import com.miladsh7.nikestore.data.repo.source.BannerDataSource


import io.reactivex.Single

class BannerRepositoryImpl(val bannerRemoteDataSource: BannerDataSource) : BannerRepository {
    override fun getBanners(): Single<List<Banner>> = bannerRemoteDataSource.getBanners()
}