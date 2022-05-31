package kz.ablazim.itbookstore.data.cache

import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.core.BooksInfo

interface BooksCacheMapper : Abstract.Mapper {

    fun map(booksDb: List<BookDb>): BooksInfo

    class Base(private val bookDbMapper: BookDbMapper) : BooksCacheMapper {
        override fun map(booksDb: List<BookDb>): BooksInfo {
            val bookList = booksDb.map { bookDb ->
                bookDbMapper.map(bookDb)
            }
            return BooksInfo(total = "0", books = bookList)
        }
    }
}