package kz.ablazim.itbookstore.domain

import kz.ablazim.itbookstore.data.BooksDataToDomainMapper
import kz.ablazim.itbookstore.data.BooksRepository

interface BooksInteractor {

    suspend fun fetchBooks(): BooksDomain

    class Base(
        private val booksRepository: BooksRepository,
        private val booksDataToDomainMapper: BooksDataToDomainMapper
    ) : BooksInteractor {
        override suspend fun fetchBooks(): BooksDomain {
            return booksRepository.fetchBooks().map(booksDataToDomainMapper)
        }
    }
}