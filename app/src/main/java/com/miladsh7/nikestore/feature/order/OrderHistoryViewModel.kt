package com.miladsh7.nikestore.feature.order

import androidx.lifecycle.MutableLiveData
import com.miladsh7.nikestore.common.NikeSingleObserver
import com.miladsh7.nikestore.common.NikeViewModel
import com.miladsh7.nikestore.common.asyncNetworkRequest
import com.miladsh7.nikestore.data.OrderHistoryItem
import com.miladsh7.nikestore.data.repo.order.OrderRepository


class OrderHistoryViewModel(orderRepository: OrderRepository) : NikeViewModel() {
    val orders = MutableLiveData<List<OrderHistoryItem>>()

    init {
        progressBarLiveData.value = true
        orderRepository.list().asyncNetworkRequest().doFinally { progressBarLiveData.value = false }
            .subscribe(object : NikeSingleObserver<List<OrderHistoryItem>>(compositeDisposable){
                override fun onSuccess(t: List<OrderHistoryItem>) {
                    orders.value=t
                }
            })
    }
}