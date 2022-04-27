package kz.ablazim.itbookstore.domain

import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.presentation.BookUi

sealed class BookDomain : Abstract.Object<BookUi, Abstract.Mapper.Empty>() {
}