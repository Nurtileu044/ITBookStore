package kz.ablazim.itbookstore.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kz.ablazim.itbookstore.core.BooksInfo
import kz.ablazim.itbookstore.domain.BooksDomainToUiMapper
import kz.ablazim.itbookstore.domain.BooksInteractor

class MainViewModel(
    private val booksInteractor: BooksInteractor,
    private val mapper: BooksDomainToUiMapper,
    private val communication: BooksCommunication
) : ViewModel() { // TODO: interface

    fun fetchBooks() = viewModelScope.launch(Dispatchers.IO) {
        val result = booksInteractor.fetchBooks()

        Dispatchers.Main.run {
            result.map(mapper)
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<BooksInfo>) {
        communication.observeSuccess(owner, observer)
    }
}