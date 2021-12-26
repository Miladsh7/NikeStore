package com.miladsh7.nikestore.data.repo

import com.miladsh7.nikestore.data.AddToCartResponse
import com.miladsh7.nikestore.data.CartItemCount
import com.miladsh7.nikestore.data.CartResponse
import com.miladsh7.nikestore.data.MessageResponse

import io.reactivex.Single

interface CartRepository {

    fun addToCart(productId: Int): Single<AddToCartResponse>
    fun get(): Single<CartResponse>
    fun remove(cartItemId: Int): Single<MessageResponse>
    fun changeCount(cartItemId: Int, count: Int): Single<AddToCartResponse>
    fun getCartItemsCount(): Single<CartItemCount>
}