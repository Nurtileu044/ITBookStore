package kz.ablazim.itbookstore.domain

import kz.ablazim.itbookstore.core.BooksInfo
import kz.ablazim.itbookstore.data.BooksDataToDomainMapper

class BaseBooksDataToDomainMapper : BooksDataToDomainMapper {
    override fun map(booksInfo: BooksInfo): BooksDomain {
        return BooksDomain.Success(booksInfo)
    }

    override fun map(exception: Exception): BooksDomain {
        return BooksDomain.Fail(exception)
    }
}