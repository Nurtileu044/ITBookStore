package kz.ablazim.itbookstore.data.cache

import kz.ablazim.itbookstore.core.BooksInfo

interface BooksCacheDataSource {

    suspend fun fetchBooks(): List<BookDb>

    suspend fun saveBooks(booksInfo: BooksInfo)

    class Base(private val realmProvider: RealmProvider) : BooksCacheDataSource {

        override suspend fun fetchBooks(): List<BookDb> {
            return realmProvider.provide().use { realm ->
                val booksDb = realm.where(BookDb::class.java).findAll() ?: emptyList()
                realm.copyFromRealm(booksDb)
            }
        }

        override suspend fun saveBooks(booksInfo: BooksInfo) = realmProvider.provide().use { realm ->
            realm.executeTransaction {
                booksInfo.books.forEach { book ->
                    val bookDb = it.createObject(BookDb::class.java, book.id)
                    bookDb.title = book.title
                    bookDb.image = book.image
                    bookDb.isbn = book.isbn
                    bookDb.price = book.price
                }
            }
        }
    }
}