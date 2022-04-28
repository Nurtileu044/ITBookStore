package kz.ablazim.itbookstore.data

import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.domain.BookDomain

sealed class BooksData : Abstract.Object<BookDomain, BooksDataToDomainMapper>() {
}