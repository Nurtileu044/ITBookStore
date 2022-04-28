package kz.ablazim.itbookstore.data.cache

import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.core.Book

interface BookDbEntityMapper : Abstract.Mapper {

    fun map(bookDbEntity: BookDbEntity): Book

    class Base : BookDbEntityMapper {
        override fun map(bookDbEntity: BookDbEntity): Book {
            bookDbEntity.also { book ->
                return Book(
                    id = book.id,
                    title = book.title,
                    isbn = book.isbn,
                    price = book.price,
                    image = book.image
                )
            }
        }
    }
}