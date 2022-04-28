package kz.ablazim.itbookstore.data.cache

import kz.ablazim.itbookstore.core.BooksInfo

interface BooksCacheDataSource {

    fun fetchBooks(): List<BookDbEntity>

    suspend fun saveBooks(booksInfo: BooksInfo)

    class Base(private val roomProvider: RoomProvider) : BooksCacheDataSource {

        override fun fetchBooks(): List<BookDbEntity> {
            return roomProvider.provide().booksDao().getBooks()
        }

        override suspend fun saveBooks(booksInfo: BooksInfo) {
            val dbBooks = booksInfo.books.map { book ->
                BookDbEntity(
                    id = book.id,
                    title = book.title,
                    isbn = book.isbn,
                    price = book.price,
                    image = book.image
                )
            }
            roomProvider.provide().booksDao().saveBookListToDatabase(dbBooks)
        }
    }
}