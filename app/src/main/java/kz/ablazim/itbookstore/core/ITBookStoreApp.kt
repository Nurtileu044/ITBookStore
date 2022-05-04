package kz.ablazim.itbookstore.core

import android.app.Application
import kz.ablazim.itbookstore.data.BooksRepository
import kz.ablazim.itbookstore.data.api.BookCloudMapper
import kz.ablazim.itbookstore.data.api.BookService
import kz.ablazim.itbookstore.data.api.BooksCloudDataSource
import kz.ablazim.itbookstore.data.cache.BookDbEntityMapper
import kz.ablazim.itbookstore.data.cache.BooksCacheDataSource
import kz.ablazim.itbookstore.data.cache.BooksCacheMapper
import kz.ablazim.itbookstore.data.cache.RoomProvider
import retrofit2.Retrofit
import kz.ablazim.itbookstore.domain.BaseBooksDataToDomainMapper
import kz.ablazim.itbookstore.domain.BooksInteractor
import kz.ablazim.itbookstore.presentation.BaseBooksDomainToUiMapper
import kz.ablazim.itbookstore.presentation.BooksCommunication
import kz.ablazim.itbookstore.presentation.MainViewModel
import kz.ablazim.itbookstore.presentation.ResourceProvider

class ITBookStoreApp : Application() {

    private companion object {
        const val BASE_URL = "https://api.itbook.store/1.0/"
    }

    lateinit var mainViewModel: MainViewModel
    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .build()
        val service = retrofit.create(BookService::class.java)

        val booksCloudDataSource = BooksCloudDataSource.Base(service)
        val booksCacheDataSource = BooksCacheDataSource.Base(RoomProvider.Base())
        val booksCloudMapper = BookCloudMapper.Base()
        val booksCacheMapper = BooksCacheMapper.Base(BookDbEntityMapper.Base())
        val booksRepository = BooksRepository.Base(
            booksCloudDataSource,
            booksCacheDataSource,
            booksCloudMapper,
            booksCacheMapper
        )
        val booksInteractor = BooksInteractor.Base(booksRepository, BaseBooksDataToDomainMapper())
        mainViewModel = MainViewModel(
            booksInteractor,
            BaseBooksDomainToUiMapper(BooksCommunication.Base(), ResourceProvider.Base(this))
        )
    }
}