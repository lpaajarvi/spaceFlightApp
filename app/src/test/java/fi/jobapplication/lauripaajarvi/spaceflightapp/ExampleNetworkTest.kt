package fi.jobapplication.lauripaajarvi.spaceflightapp

import org.junit.Test
import org.junit.Assert.*

/**
 * This unit test will test that right amount of NewsUnits, that are ordered in a List, is fetched
 * from the remote URL which is defined in used ExampleNetWorkModule class using FetchAndParse(...)
 * method.
 *
 * At least some of the tests should fail if the API is down.
 *
 * Another reason a test could fail would be if API would have wrong entries added to their json
 * but then again I'm learning unit testing should be about testing functions, not the API.
 *
 * I'm not sure if ideally the test should be performed in local "mockery" server but then again
 * it might be a bit far-fetched, since it was said in the assignment that one should need the API
 * documentation which had the response codes etc. I haven't taught myself to do the mockery
 * server thing yet but I found some info and tutorials performing tests like that.
 *
 */
class ExampleNetworkTest {

    val nwModule = ExampleNetworkModule()
    val mockNewsUnit = NewsUnit(
            "Sample",
            "https://www.mock.com",
            "https://mockImageurl",
            "mockNewsSite",
            "Mocking Summary",
            "2021-03-15T10:25:18.000Z")
    @Test
    fun zero_returns_emptyList() {
        var mockEmptyList : List<NewsUnit?> = listOf()
        nwModule.fetchAndParse(0) {
            assertEquals(it, mockEmptyList)
        }
    }
    @Test
    fun minus_one_returns_emptyList() {
        var mockEmptyList: List<NewsUnit?> = listOf()
        nwModule.fetchAndParse(-1) {
            assertEquals(it, mockEmptyList)
        }
    }
    @Test
    fun one_returns_list_of_one() {
        var mockListOfOne: List<NewsUnit?> = listOf(mockNewsUnit)
        nwModule.fetchAndParse(1) {
            assertEquals(it.size, mockListOfOne.size)
        }
    }
    @Test
    fun three_returns_list_of_three() {
        var mockListOfThree: List<NewsUnit?> = listOf(mockNewsUnit, mockNewsUnit, mockNewsUnit)
        nwModule.fetchAndParse(3) {
            assertEquals(it.size, mockListOfThree.size)
        }
    }

}