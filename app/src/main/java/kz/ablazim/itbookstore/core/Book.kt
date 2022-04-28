package kz.ablazim.itbookstore.core

data class Book(
    val id: String,
    val title: String,
    val isbn: String,
    val price: String,
    val image: String
)