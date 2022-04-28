package kz.ablazim.itbookstore.data.api

interface BooksCloudDataSource {

    suspend fun fetchBooks(): BookServerModel

    class Base(private val service: BookService) : BooksCloudDataSource {
        override suspend fun fetchBooks(): BookServerModel = service.fetchBooks()
    }
}