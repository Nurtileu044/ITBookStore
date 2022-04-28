package kz.ablazim.itbookstore.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase

private const val  VERSION = 1

@Database(entities = [BookDbEntity::class], version = VERSION)
abstract class ITBookStoreDatabase : RoomDatabase() {
    abstract fun booksDao(): BooksDao
}