package com.kelm.booklist

data class ApiData(
    val status: String,
    val code: Int,
    val total: Int,
    val data: List<Book>
)

data class Book(
    val title: String,
    val author: String,
    val genre: String,
    val content: String
)
