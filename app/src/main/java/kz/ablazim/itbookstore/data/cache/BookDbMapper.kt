package kz.ablazim.itbookstore.data.cache

import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.core.Book

interface BookDbMapper : Abstract.Mapper {

    fun map(bookDb: BookDb): Book

    class Base : BookDbMapper {
        override fun map(bookDb: BookDb): Book {
            return Book(
                id = bookDb.id,
                title = bookDb.title,
                isbn = bookDb.isbn,
                image = bookDb.image,
                price = bookDb.price
            )
        }
    }
}