package kz.ablazim.itbookstore.domain

import kz.ablazim.itbookstore.core.BooksInfo
import kz.ablazim.itbookstore.data.BooksDataToDomainMapper

class BaseBooksDataToDomainMapper : BooksDataToDomainMapper {
    override fun map(booksInfo: BooksInfo): BookDomain {
        return BookDomain.Success(booksInfo)
    }

    override fun map(exception: Exception): BookDomain {
        return BookDomain.Fail(exception)
    }
}