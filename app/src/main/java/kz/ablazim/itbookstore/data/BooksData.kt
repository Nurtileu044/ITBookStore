package kz.ablazim.itbookstore.data

import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.core.BooksInfo
import kz.ablazim.itbookstore.domain.BookDomain

sealed class BooksData : Abstract.Object<BookDomain, BooksDataToDomainMapper>() {
    class Success(private val booksInfo: BooksInfo) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper): BookDomain {
            return mapper.map(booksInfo)
        }
    }

    class Fail(private val exception: Exception) : BooksData() {
        override fun map(mapper: BooksDataToDomainMapper): BookDomain {
            return mapper.map(exception)
        }
    }
}