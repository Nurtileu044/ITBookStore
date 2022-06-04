package kz.ablazim.itbookstore.data.api

import com.google.gson.annotations.SerializedName

data class BookServerDetailModel(
    @SerializedName("title")
    val title: String,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("isbn13")
    val isbn: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("url")
    val url: String
)