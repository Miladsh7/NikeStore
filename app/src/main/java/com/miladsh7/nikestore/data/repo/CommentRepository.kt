package com.miladsh7.nikestore.data.repo

import com.miladsh7.nikestore.data.Comment

import io.reactivex.Single

interface CommentRepository {

    fun getAll(productId:Int): Single<List<Comment>>

    fun insert(): Single<Comment>
}