package kz.ablazim.itbookstore.data

import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.core.BooksInfo
import kz.ablazim.itbookstore.domain.BookDomain

interface BooksDataToDomainMapper : Abstract.Mapper {

    fun map(booksInfo: BooksInfo): BookDomain

    fun map(exception: Exception): BookDomain
}