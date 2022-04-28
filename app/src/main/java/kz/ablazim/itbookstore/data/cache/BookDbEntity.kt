package kz.ablazim.itbookstore.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.core.Book

@Entity(tableName = "Book")
data class BookDbEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "isbn") val isbn: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "image") val image: String,
) : Abstract.Object<Book, BookDbEntityMapper>() {
    override fun map(mapper: BookDbEntityMapper): Book {
        return mapper.map(this)
    }
}