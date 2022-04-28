package kz.ablazim.itbookstore.data.api

import com.google.gson.annotations.SerializedName
import kz.ablazim.itbookstore.core.Abstract
import kz.ablazim.itbookstore.core.BooksInfo

data class BookServerModel(
    @SerializedName("total")
    val total: String,
    @SerializedName("books")
    val books: List<BookServerDetailModel>
) : Abstract.Object<BooksInfo, BookCloudMapper>() {

    override fun map(mapper: BookCloudMapper): BooksInfo {
        return mapper.map(total = total, books = books)
    }
}