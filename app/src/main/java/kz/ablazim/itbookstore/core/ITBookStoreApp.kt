package kz.ablazim.itbookstore.core

import android.app.Application
import kz.ablazim.itbookstore.data.BooksRepository
import kz.ablazim.itbookstore.domain.BaseBooksDataToDomainMapper
import kz.ablazim.itbookstore.domain.BooksInteractor

class ITBookStoreApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val booksRepository: BooksRepository = TODO("merge")
        val booksInteractor = BooksInteractor.Base(booksRepository, BaseBooksDataToDomainMapper())
    }
}