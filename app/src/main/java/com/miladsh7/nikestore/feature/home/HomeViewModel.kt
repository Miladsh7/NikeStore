package com.miladsh7.nikestore.feature.home

import androidx.lifecycle.MutableLiveData
import com.miladsh7.nikestore.common.NikeCompletableObserver
import com.miladsh7.nikestore.common.NikeSingleObserver
import com.miladsh7.nikestore.common.NikeViewModel
import com.miladsh7.nikestore.data.Banner
import com.miladsh7.nikestore.data.Product
import com.miladsh7.nikestore.data.SORT_LATEST
import com.miladsh7.nikestore.data.repo.BannerRepository
import com.miladsh7.nikestore.data.repo.ProductRepository

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(
    private val productRepository: ProductRepository,
    private val bannerRepository: BannerRepository
) : NikeViewModel() {
    val productsLiveData = MutableLiveData<List<Product>>()
    val bannersLiveData = MutableLiveData<List<Banner>>()

    init {
        progressBarLiveData.value = true
        productRepository.getProducts(SORT_LATEST)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : NikeSingleObserver<List<Product>>(compositeDisposable) {
                override fun onSuccess(t: List<Product>) {
                    productsLiveData.value = t
                }
            })

        bannerRepository.getBanners()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : NikeSingleObserver<List<Banner>>(compositeDisposable) {
                override fun onSuccess(t: List<Banner>) {
                    bannersLiveData.value = t
                }
            })
    }

    fun addProductToFavorites(product: Product) {
        if (product.isFavorite)
            productRepository.deleteFromFavorites(product)
                .subscribeOn(Schedulers.io())
                .subscribe(object : NikeCompletableObserver(compositeDisposable) {
                    override fun onComplete() {
                        product.isFavorite = false
                    }
                })
        else
            productRepository.addToFavorites(product)
                .subscribeOn(Schedulers.io())
                .subscribe(object : NikeCompletableObserver(compositeDisposable) {
                    override fun onComplete() {
                        product.isFavorite = true
                    }
                })
    }
}