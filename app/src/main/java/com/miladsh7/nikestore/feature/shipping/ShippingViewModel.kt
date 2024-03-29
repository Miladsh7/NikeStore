package com.miladsh7.nikestore.feature.shipping

import com.miladsh7.nikestore.common.NikeViewModel
import com.miladsh7.nikestore.data.SubmitOrderResult
import com.miladsh7.nikestore.data.repo.order.OrderRepository


import io.reactivex.Single

const val PAYMENT_METHOD_COD = "cash_on_delivery"
const val PAYMENT_METHOD_ONLINE = "online"

class ShippingViewModel(private val orderRepository: OrderRepository) : NikeViewModel() {
    fun submitOrder(
        firstName: String,
        lastName: String,
        postalCode: String,
        phoneNumber: String,
        address: String,
        paymentMethod: String
    ): Single<SubmitOrderResult> {
        return orderRepository.submit(
            firstName,
            lastName,
            postalCode,
            phoneNumber,
            address,
            paymentMethod
        )
    }
}