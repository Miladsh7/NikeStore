package com.miladsh7.nikestore.data.repo.order

import com.miladsh7.nikestore.data.Checkout
import com.miladsh7.nikestore.data.OrderHistoryItem
import com.miladsh7.nikestore.data.SubmitOrderResult

import io.reactivex.Single

interface OrderDataSource {

    fun submit(
        firstName: String,
        lastName: String,
        postalCode: String,
        phoneNumber: String,
        address: String,
        paymentMethod: String
    ): Single<SubmitOrderResult>

    fun checkout(orderId: Int): Single<Checkout>

    fun list():Single<List<OrderHistoryItem>>
}