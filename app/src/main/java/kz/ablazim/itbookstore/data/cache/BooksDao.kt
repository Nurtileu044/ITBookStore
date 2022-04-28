package kz.ablazim.itbookstore.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BooksDao {
    @Query("SELECT * FROM Book")
    fun getBooks(): List<BookDbEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveBookToDatabase(bookDbEntity: BookDbEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveBookListToDatabase(books: List<BookDbEntity>)
}