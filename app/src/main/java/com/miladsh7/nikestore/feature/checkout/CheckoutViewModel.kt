package com.miladsh7.nikestore.feature.checkout

import androidx.lifecycle.MutableLiveData
import com.miladsh7.nikestore.common.NikeSingleObserver
import com.miladsh7.nikestore.common.NikeViewModel
import com.miladsh7.nikestore.common.asyncNetworkRequest
import com.miladsh7.nikestore.data.Checkout
import com.miladsh7.nikestore.data.repo.order.OrderRepository


class CheckoutViewModel(orderId: Int, orderRepository: OrderRepository) :
    NikeViewModel() {
    val checkoutLiveData = MutableLiveData<Checkout>()

    init {
        orderRepository.checkout(orderId)
            .asyncNetworkRequest()
            .subscribe(object : NikeSingleObserver<Checkout>(compositeDisposable) {
                override fun onSuccess(t: Checkout) {
                    checkoutLiveData.value = t
                }
            })
    }
}