package com.miladsh7.nikestore.data.repo

import com.miladsh7.nikestore.data.Comment
import com.miladsh7.nikestore.data.repo.source.CommentDataSource

import io.reactivex.Single

class CommentRepositoryImpl(val commentDataSource: CommentDataSource) : CommentRepository {
    override fun getAll(productId: Int): Single<List<Comment>> = commentDataSource.getAll(productId)

    override fun insert(): Single<Comment> {
        TODO("Not yet implemented")
    }
}