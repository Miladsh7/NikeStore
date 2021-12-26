package com.miladsh7.nikestore.data.repo

import com.miladsh7.nikestore.data.AddToCartResponse
import com.miladsh7.nikestore.data.CartItemCount
import com.miladsh7.nikestore.data.CartResponse
import com.miladsh7.nikestore.data.MessageResponse
import com.miladsh7.nikestore.data.repo.source.CartDataSource

import io.reactivex.Single

class CartRepositoryImpl(val remoteDataSource: CartDataSource) : CartRepository {
    override fun addToCart(productId: Int): Single<AddToCartResponse> =
        remoteDataSource.addToCart(productId)

    override fun get(): Single<CartResponse> =remoteDataSource.get()

    override fun remove(cartItemId: Int): Single<MessageResponse> =remoteDataSource.remove(cartItemId)

    override fun changeCount(cartItemId: Int, count: Int): Single<AddToCartResponse> = remoteDataSource.changeCount(cartItemId,count)

    override fun getCartItemsCount(): Single<CartItemCount> =remoteDataSource.getCartItemsCount()
}