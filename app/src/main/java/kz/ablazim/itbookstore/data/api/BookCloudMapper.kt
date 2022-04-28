package kz.ablazim.itbookstore.data.api

import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.core.Book
import kz.ablazim.itbookstore.core.BooksInfo
import java.util.*

interface BookCloudMapper : Abstract.Mapper {

    fun map(total: String, books: List<BookServerDetailModel>): BooksInfo

    class Base : BookCloudMapper {
        override fun map(total: String, books: List<BookServerDetailModel>): BooksInfo {
            val bookList = books.map {
                Book(
                    id = UUID.randomUUID().toString(),
                    title = it.title,
                    isbn = it.isbn,
                    price = it.price,
                    image = it.image
                )
            }
            return BooksInfo(total = total, books = bookList)
        }
    }
}