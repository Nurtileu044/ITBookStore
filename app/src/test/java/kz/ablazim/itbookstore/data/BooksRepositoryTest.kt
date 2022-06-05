package kz.ablazim.itbookstore.data

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import kz.ablazim.itbookstore.core.Book
import kz.ablazim.itbookstore.core.BooksInfo
import kz.ablazim.itbookstore.data.api.BookCloudMapper
import kz.ablazim.itbookstore.data.api.BookServerDetailModel
import kz.ablazim.itbookstore.data.api.BookServerModel
import kz.ablazim.itbookstore.data.api.BooksCloudDataSource
import kz.ablazim.itbookstore.data.cache.BookDb
import kz.ablazim.itbookstore.data.cache.BookDbMapper
import kz.ablazim.itbookstore.data.cache.BooksCacheDataSource
import kz.ablazim.itbookstore.data.cache.BooksCacheMapper
import org.junit.Test
import java.net.UnknownHostException

class BooksRepositoryTest {

    private val unknownHostException = UnknownHostException()

    @Test
    fun test_no_connection_no_cache() = runBlocking {
        val testBooksCloudDataSource = TestBooksCloudDataSource(returnSuccess = false)
        val testBooksCacheDataSource = TestBooksCacheDataSource(returnSuccess = false)
        val repository = BooksRepository.Base(
            testBooksCloudDataSource,
            testBooksCacheDataSource,
            BookCloudMapper.Base(),
            BooksCacheMapper.Base(TestBookDbMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Fail(unknownHostException)

        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_no_cache() = runBlocking {
        val testBooksCloudDataSource = TestBooksCloudDataSource(returnSuccess = true)
        val testBooksCacheDataSource = TestBooksCacheDataSource(returnSuccess = false)
        val repository = BooksRepository.Base(
            testBooksCloudDataSource,
            testBooksCacheDataSource,
            BookCloudMapper.Base(),
            BooksCacheMapper.Base(TestBookDbMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            BooksInfo(
                total = "0",
                books = listOf(Book(id = "0", title = "title", isbn = "isbn", price = "price", image = "image"))
            )
        )

        assertEquals(expected, actual)
    }

    @Test
    fun test_no_connection_with_cache() = runBlocking {
        val testBooksCloudDataSource = TestBooksCloudDataSource(returnSuccess = false)
        val testBooksCacheDataSource = TestBooksCacheDataSource(returnSuccess = true)
        val repository = BooksRepository.Base(
            testBooksCloudDataSource,
            testBooksCacheDataSource,
            BookCloudMapper.Base(),
            BooksCacheMapper.Base(TestBookDbMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            BooksInfo(
                total = "0",
                books = listOf(Book(id = "1", title = "title", isbn = "isbn", price = "price", image = "image"))
            )
        )

        assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_with_cache() = runBlocking {
        val testBooksCloudDataSource = TestBooksCloudDataSource(returnSuccess = true)
        val testBooksCacheDataSource = TestBooksCacheDataSource(returnSuccess = true)
        val repository = BooksRepository.Base(
            testBooksCloudDataSource,
            testBooksCacheDataSource,
            BookCloudMapper.Base(),
            BooksCacheMapper.Base(TestBookDbMapper())
        )

        val actual = repository.fetchBooks()
        val expected = BooksData.Success(
            BooksInfo(
                total = "0",
                books = listOf(Book(id = "1", title = "title", isbn = "isbn", price = "price", image = "image"))
            )
        )

        assertEquals(expected, actual)
    }

    private inner class TestBooksCloudDataSource(
        private val returnSuccess: Boolean
    ) : BooksCloudDataSource {
        override suspend fun fetchBooks(): BookServerModel {
            if (returnSuccess) {
                return BookServerModel(
                    error = "error",
                    total = "0",
                    books = listOf(
                        BookServerDetailModel(
                            title = "title",
                            subtitle = "subtitle",
                            isbn = "isbn",
                            price = "price",
                            image = "image",
                            url = "url"
                        )
                    )
                )
            } else {
                throw unknownHostException
            }
        }
    }

    private inner class TestBooksCacheDataSource(
        private val returnSuccess: Boolean
    ) : BooksCacheDataSource {
        override suspend fun fetchBooks(): List<BookDb> {
            return if (returnSuccess) {
                listOf(BookDb().apply {
                    id = "1"
                    title = "title"
                    isbn = "isbn"
                    price = "price"
                    image = "image"
                })
            } else {
                emptyList()
            }
        }

        override suspend fun saveBooks(booksInfo: BooksInfo) {
            // not used here
        }
    }

    private inner class TestBookDbMapper : BookDbMapper {
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