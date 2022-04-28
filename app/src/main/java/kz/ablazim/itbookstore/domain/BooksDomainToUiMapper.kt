package kz.ablazim.itbookstore.domain

import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.core.BooksInfo
import kz.ablazim.itbookstore.presentation.BooksUi

interface BooksDomainToUiMapper : Abstract.Mapper {

    fun map(booksInfo: BooksInfo): BooksUi

    fun map(errorType: ErrorType): BooksUi
}