package kz.ablazim.itbookstore.data

import kz.ablazim.itbookstore.data.api.BookCloudMapper
import kz.ablazim.itbookstore.data.api.BooksCloudDataSource
import kz.ablazim.itbookstore.data.cache.BooksCacheDataSource
import kz.ablazim.itbookstore.data.cache.BooksCacheMapper

interface BooksRepository {

    suspend fun fetchBooks(): BooksData

    class Base(
        private val booksCloudDataSource: BooksCloudDataSource,
        private val booksCacheDataSource: BooksCacheDataSource,
        private val bookCloudMapper: BookCloudMapper,
        private val booksCacheMapper: BooksCacheMapper
    ) : BooksRepository {
        override suspend fun fetchBooks() = try {
            val booksCacheList = booksCacheDataSource.fetchBooks()
            if (booksCacheList.isEmpty()) {
                val booksCloud = booksCloudDataSource.fetchBooks()
                val books = booksCloud.map(bookCloudMapper)
                booksCacheDataSource.saveBooks(books)
                BooksData.Success(books)
            } else {
                BooksData.Success(booksCacheMapper.map(booksCacheList))
            }
        } catch (e: Exception) {
            BooksData.Fail(e)
        }
    }
}