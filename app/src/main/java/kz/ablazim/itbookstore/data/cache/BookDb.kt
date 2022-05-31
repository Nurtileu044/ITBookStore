package kz.ablazim.itbookstore.data.cache

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.core.Book

open class BookDb : RealmObject(), Abstract.Mappable<Book, BookDbMapper> {
    @PrimaryKey
    var id: String = ""
    var title: String = ""
    var isbn: String = ""
    var price: String = ""
    var image: String = ""

    override fun map(mapper: BookDbMapper): Book {
        return mapper.map(this)
    }
}