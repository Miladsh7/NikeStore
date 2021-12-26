package com.miladsh7.nikestore.data.repo.source

import com.miladsh7.nikestore.data.Comment

import io.reactivex.Single

interface CommentDataSource {

    fun getAll(productId:Int): Single<List<Comment>>

    fun insert(): Single<Comment>
}