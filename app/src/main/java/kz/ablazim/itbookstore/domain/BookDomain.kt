package kz.ablazim.itbookstore.domain

import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.core.BooksInfo
import kz.ablazim.itbookstore.presentation.BooksUi
import retrofit2.HttpException
import java.net.UnknownHostException

// TODO rename to BooksDomain by lead
sealed class BookDomain : Abstract.Object<BooksUi, BooksDomainToUiMapper>() {
    class Success(private val booksInfo: BooksInfo) : BookDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi {
            return mapper.map(booksInfo)
        }
    }

    class Fail(private val exception: Exception) : BookDomain() {
        override fun map(mapper: BooksDomainToUiMapper): BooksUi {
            val errorType = when (exception) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
            return mapper.map(errorType)
        }
    }
}