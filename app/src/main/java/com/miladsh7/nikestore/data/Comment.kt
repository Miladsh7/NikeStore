package com.miladsh7.nikestore.data

import com.miladsh7.nikestore.data.Author

data class Comment(
    val author: Author,
    val content: String,
    val date: String,
    val id: Int,
    val title: String
)