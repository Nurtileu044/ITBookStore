package kz.ablazim.itbookstore.data.api

import retrofit2.http.GET

interface BookService {

    @GET("/new")
    suspend fun fetchBooks() : BookServerModel
}