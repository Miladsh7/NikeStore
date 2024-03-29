package com.miladsh7.nikestore.feature

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.miladsh7.nikestore.common.EXTRA_KEY_DATA
import com.miladsh7.nikestore.common.NikeSingleObserver
import com.miladsh7.nikestore.common.NikeViewModel
import com.miladsh7.nikestore.common.asyncNetworkRequest
import com.miladsh7.nikestore.data.Comment
import com.miladsh7.nikestore.data.Product
import com.miladsh7.nikestore.data.repo.CartRepository
import com.miladsh7.nikestore.data.repo.CommentRepository



import io.reactivex.Completable


class ProductDetailViewModel(bundle: Bundle, commentRepository: CommentRepository, val cartRepository: CartRepository) :
    NikeViewModel() {
    val productLiveData = MutableLiveData<Product>()
    val commentsLiveData = MutableLiveData<List<Comment>>()

    init {
        productLiveData.value = bundle.getParcelable(EXTRA_KEY_DATA)
        progressBarLiveData.value = true
        commentRepository.getAll(productLiveData.value!!.id)
            .asyncNetworkRequest()
            .doFinally { progressBarLiveData.value = false }
            .subscribe(object : NikeSingleObserver<List<Comment>>(compositeDisposable) {
                override fun onSuccess(t: List<Comment>) {
                    commentsLiveData.value = t
                }
            })
    }

    fun onAddToCartBtn():Completable = cartRepository.addToCart(productLiveData.value!!.id).ignoreElement()
}