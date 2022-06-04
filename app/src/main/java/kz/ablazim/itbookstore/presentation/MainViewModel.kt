package kz.ablazim.itbookstore.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.core.BooksInfo
import kz.ablazim.itbookstore.domain.BooksDomainToUiMapper
import kz.ablazim.itbookstore.domain.BooksInteractor

class MainViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper,
    private val communication: BooksCommunication
) : ViewModel() { // TODO: interface

    fun fetchBooks() = viewModelScope.launch(Dispatchers.IO) {
        val resultDomain = booksInteractor.fetchBooks()

        withContext(Dispatchers.Main) {
            val resultUi = resultDomain.map(mapper)
            resultUi.map(Abstract.Mapper.Empty())
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<BooksInfo>) {
        communication.observeSuccess(owner, observer)
    }
}