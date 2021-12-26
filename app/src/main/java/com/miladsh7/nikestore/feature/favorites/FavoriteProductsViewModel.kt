package com.miladsh7.nikestore.feature.favorites

import androidx.lifecycle.MutableLiveData
import com.miladsh7.nikestore.common.NikeSingleObserver
import com.miladsh7.nikestore.common.NikeViewModel
import com.miladsh7.nikestore.data.Product
import com.miladsh7.nikestore.common.NikeCompletableObserver


import com.miladsh7.nikestore.data.repo.ProductRepository
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class FavoriteProductsViewModel(private val productRepository: ProductRepository) :
    NikeViewModel() {

    val productsLiveData = MutableLiveData<List<Product>>()

    init {
        productRepository.getFavoriteProducts()
            .subscribeOn(Schedulers.io())
            .subscribe(object : NikeSingleObserver<List<Product>>(compositeDisposable) {
                override fun onSuccess(t: List<Product>) {
                    productsLiveData.postValue(t)
                }
            })
    }

    fun removeFromFavorites(product: Product) {
        productRepository.deleteFromFavorites(product)
            .subscribeOn(Schedulers.io())
            .subscribe(object : NikeCompletableObserver(compositeDisposable) {
                override fun onComplete() {
                    Timber.i("removeFromFavorites compeleted")
                }
            })
    }
}