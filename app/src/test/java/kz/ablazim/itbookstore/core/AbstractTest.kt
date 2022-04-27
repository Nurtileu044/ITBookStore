package kz.ablazim.itbookstore.core

import junit.framework.Assert.assertTrue
import org.junit.Test
import java.io.IOException

class AbstractTest {

    @Test
    fun test_success() {
        val dataObject = TestDataObject.Success("one", "two")
        val domainObject = dataObject.map(DataToDomainMapper.Base())
        val uiObject = domainObject.map(DomainToUiMapper.Base())
        assertTrue(uiObject is TestUiObject.Success)
    }

    @Test
    fun test_fail() {
        val dataObject = TestDataObject.Fail(IOException())
        val domainObject = dataObject.map(DataToDomainMapper.Base())
        val uiObject = domainObject.map(DomainToUiMapper.Base())
        assertTrue(uiObject is TestUiObject.Fail)
    }

    private sealed class TestDataObject : Abstract.Object<TestDomainObject, DataToDomainMapper>() {

        class Success(private val textOne: String, private val textTwo: String) : TestDataObject() {
            override fun map(mapper: DataToDomainMapper): TestDomainObject {
                return mapper.map(textOne, textTwo)
            }
        }

        class Fail(private val exception: Exception) : TestDataObject() {
            override fun map(mapper: DataToDomainMapper): TestDomainObject {
                return mapper.map(exception)
            }
        }
    }

    private interface DataToDomainMapper : Abstract.Mapper {

        fun map(textOne: String, textTwo: String): TestDomainObject

        fun map(exception: Exception): TestDomainObject

        class Base : DataToDomainMapper {
            override fun map(textOne: String, textTwo: String): TestDomainObject {
                return TestDomainObject.Success("$textOne $textTwo")
            }

            override fun map(exception: Exception): TestDomainObject {
                return TestDomainObject.Fail(exception)
            }
        }
    }

    private sealed class TestDomainObject : Abstract.Object<TestUiObject, DomainToUiMapper>() {

        class Success(private val textCombined: String) : TestDomainObject() {
            override fun map(mapper: DomainToUiMapper): TestUiObject = mapper.map(textCombined)
        }

        class Fail(private val exception: Exception) : TestDomainObject() {
            override fun map(mapper: DomainToUiMapper): TestUiObject = mapper.map(exception)
        }
    }

    private interface DomainToUiMapper : Abstract.Mapper {
        fun map(textCombined: String): TestUiObject

        fun map(exception: Exception): TestUiObject

        class Base : DomainToUiMapper {
            override fun map(textCombined: String): TestUiObject {
                return TestUiObject.Success(textCombined)
            }

            override fun map(exception: Exception): TestUiObject {
                return TestUiObject.Fail(exception)
            }
        }
    }

    private sealed class TestUiObject : Abstract.Object<Unit, Abstract.Mapper.Empty>() {

        class Success(private val combinedText: String) : TestUiObject() {
            override fun map(mapper: Abstract.Mapper.Empty) {
                TODO("Not yet implemented")
            }
        }

        class Fail(private val exception: Exception) : TestUiObject() {
            override fun map(mapper: Abstract.Mapper.Empty) {
                TODO("Not yet implemented")
            }
        }
    }
}