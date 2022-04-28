package kz.ablazim.itbookstore.data.cache

import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.core.BooksInfo

interface BooksCacheMapper : Abstract.Mapper {

    fun map(booksDbEntity: List<BookDbEntity>): BooksInfo

    class Base(private val bookDbEntityMapper: BookDbEntityMapper) : BooksCacheMapper {
        override fun map(booksDbEntity: List<BookDbEntity>): BooksInfo {
            val bookList = booksDbEntity.map { bookDb ->
                bookDbEntityMapper.map(bookDb)
            }
            return BooksInfo(total = "0", books = bookList)
        }
    }
}