package kz.ablazim.itbookstore.data

import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.domain.BookDomain

sealed class BookData : Abstract.Object<BookDomain, Abstract.Mapper.Empty>() {
}