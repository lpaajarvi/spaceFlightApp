package fi.jobapplication.lauripaajarvi.spaceflightapp

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

import java.net.URL

/**
 * uses Jackson library to convert the json directly into objects of class (NewsUnit)
 */
class ExampleNetworkModule : NetworkModuleInterface {
    /**
     * returns List of NewsUnit objects, which are mapped from json that is fetched from the url
     * https://test.spaceflightnewsapi.net/api/v2/articles?_limit= [ param $howMany ]
     * if howMany <= 0 returns an empty list in a callback.
     *
     * @param howMany number of entries to fetch
     * @param callback returns the Json fetched, parsed into List of NewsUnit objects
     */
    override fun fetchAndParse(howMany: Int, callback: (List<NewsUnit?>) -> Unit) {

        val mapper = ObjectMapper()
        val url: URL = URL("https://test.spaceflightnewsapi.net/api/v2/articles?_limit=$howMany")

        val connection = url.openConnection()
        connection.setRequestProperty("Accept", "application/json")

        var newsUnits: List<NewsUnit> = listOf()

        if (howMany > 0) {
            newsUnits =
                    mapper.readValue(connection.getInputStream(), object : TypeReference<List<NewsUnit>>() {})
           }
        callback(newsUnits)
    }
}