package kz.ablazim.itbookstore.presentation

import kz.ablazim.itbookstore.core.BooksInfo
import kz.ablazim.itbookstore.domain.BooksDomainToUiMapper
import kz.ablazim.itbookstore.domain.ErrorType

class BaseBooksDomainToUiMapper(
    private val communication: BooksCommunication,
    private val resourceProvider: ResourceProvider
) : BooksDomainToUiMapper {
    override fun map(booksInfo: BooksInfo): BooksUi {
        return BooksUi.Success(communication, booksInfo)
    }

    override fun map(errorType: ErrorType): BooksUi {
        return BooksUi.Fail(communication, errorType, resourceProvider)
    }
}