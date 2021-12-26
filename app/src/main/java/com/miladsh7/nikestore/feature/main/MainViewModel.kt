package com.miladsh7.nikestore.feature.main

import com.miladsh7.nikestore.common.NikeSingleObserver
import com.miladsh7.nikestore.common.NikeViewModel
import com.miladsh7.nikestore.data.CartItemCount
import com.miladsh7.nikestore.data.TokenContainer
import com.miladsh7.nikestore.data.repo.CartRepository


import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus

class MainViewModel(private val cartRepository: CartRepository) : NikeViewModel() {
    fun getCartItemsCount(){
        if (!TokenContainer.token.isNullOrEmpty()){
            cartRepository.getCartItemsCount()
                .subscribeOn(Schedulers.io())
                .subscribe(object : NikeSingleObserver<CartItemCount>(compositeDisposable){
                    override fun onSuccess(t: CartItemCount) {
                        EventBus.getDefault().postSticky(t)
                    }
                })
        }
    }
}