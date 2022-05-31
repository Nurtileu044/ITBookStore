package kz.ablazim.itbookstore.presentation

import kz.ablazim.itbookstore.R
import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.core.BooksInfo
import kz.ablazim.itbookstore.domain.ErrorType

sealed class BooksUi : Abstract.Object<Unit, Abstract.Mapper.Empty>() {

    class Success(
        private val booksCommunication: BooksCommunication,
        private val booksInfo: BooksInfo
    ) : BooksUi() {
        override fun map(mapper: Abstract.Mapper.Empty) {
            booksCommunication.show(booksInfo)
        }
    }

    class Fail(
        private val booksCommunication: BooksCommunication,
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider
    ) : BooksUi() {
        override fun map(mapper: Abstract.Mapper.Empty) {
            val messageId = when (errorType) { // todo move to another class
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable
                else -> R.string.something_went_wrong
            }
            booksCommunication.show(resourceProvider.getString(messageId))
        }
    }
}
