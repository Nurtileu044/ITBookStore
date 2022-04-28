package kz.ablazim.itbookstore.data.api

import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.data.BookDetail

interface BookServerDetailToBookDetailMapper : Abstract.Mapper {

    fun map(title: String, isbn: String, price: String, image: String): BookDetail

    class Base() : BookServerDetailToBookDetailMapper {
        override fun map(title: String, isbn: String, price: String, image: String): BookDetail {
            return BookDetail(title = title, isbn = isbn, price = price, image = image)
        }
    }
}