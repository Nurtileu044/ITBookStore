package kz.ablazim.itbookstore.domain

import kz.ablazim.itbookstore.data.BooksDataToDomainMapper
import kz.ablazim.itbookstore.data.BooksRepository

interface BooksInteractor {

    suspend fun fetchBooks(): BookDomain

    class Base(
        private val booksRepository: BooksRepository,
        private val booksDataToDomainMapper: BooksDataToDomainMapper
    ) : BooksInteractor {
        override suspend fun fetchBooks(): BookDomain {
            return booksRepository.fetchBooks().map(booksDataToDomainMapper)
        }
    }
}