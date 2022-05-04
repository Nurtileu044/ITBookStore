package kz.ablazim.itbookstore.core

import android.app.Application
import kz.ablazim.itbookstore.domain.BooksInteractor
import kz.ablazim.itbookstore.presentation.BaseBooksDomainToUiMapper
import kz.ablazim.itbookstore.presentation.BooksCommunication
import kz.ablazim.itbookstore.presentation.MainViewModel
import kz.ablazim.itbookstore.presentation.ResourceProvider

class ITBookStoreApp : Application() {

    lateinit var mainViewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()

        val booksInteractor: BooksInteractor = TODO()
        mainViewModel = MainViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(BooksCommunication.Base(), ResourceProvider.Base(this))
        )
    }
}