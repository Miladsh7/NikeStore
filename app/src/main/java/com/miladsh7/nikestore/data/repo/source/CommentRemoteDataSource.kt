package com.miladsh7.nikestore.data.repo.source

import com.miladsh7.nikestore.data.Comment
import com.miladsh7.nikestore.services.http.ApiService

import io.reactivex.Single

class CommentRemoteDataSource(val apiService: ApiService):CommentDataSource {
    override fun getAll(productId:Int): Single<List<Comment>> = apiService.getComments(productId)

    override fun insert(): Single<Comment> {
        TODO("Not yet implemented")
    }
}