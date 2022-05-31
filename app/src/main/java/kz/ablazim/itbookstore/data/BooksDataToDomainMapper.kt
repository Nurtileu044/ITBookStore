package kz.ablazim.itbookstore.data

import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.core.BooksInfo
import kz.ablazim.itbookstore.domain.BooksDomain

interface BooksDataToDomainMapper : Abstract.Mapper {

    fun map(booksInfo: BooksInfo): BooksDomain

    fun map(exception: Exception): BooksDomain
}