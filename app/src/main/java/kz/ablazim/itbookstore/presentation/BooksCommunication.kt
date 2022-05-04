package kz.ablazim.itbookstore.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kz.ablazim.itbookstore.core.BooksInfo

interface BooksCommunication {

    fun show(booksInfo: BooksInfo)
    fun show(errorMessage: String)

    fun observeSuccess(owner: LifecycleOwner, observer: Observer<BooksInfo>)
    fun observeFail(owner: LifecycleOwner, observer: Observer<String>)

    class Base : BooksCommunication {
        private val successLiveData = MutableLiveData<BooksInfo>()
        private val failLiveData = MutableLiveData<String>()
        override fun show(booksInfo: BooksInfo) {
            successLiveData.value = booksInfo
        }

        override fun show(errorMessage: String) {
            failLiveData.value = errorMessage
        }

        override fun observeSuccess(owner: LifecycleOwner, observer: Observer<BooksInfo>) {
            successLiveData.observe(owner, observer)
        }

        override fun observeFail(owner: LifecycleOwner, observer: Observer<String>) {
            failLiveData.observe(owner, observer)
        }
    }
}