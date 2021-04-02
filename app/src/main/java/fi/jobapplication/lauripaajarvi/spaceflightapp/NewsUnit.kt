package fi.jobapplication.lauripaajarvi.spaceflightapp

import com.fasterxml.jackson.annotation.*;
import java.text.SimpleDateFormat
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
/**
 * NewsUnit as object. Can be parsed from Json using Jackson library in ExampleNetworkModule.
 *
 * @param title title of news entry
 * @param url url of news article
 * @param imageUrl if one would like to use image from the news article
 * @param newsSite publisher
 * @param summary summary
 * @param publishedAt Date of news as String in a format of 2021-03-15T10:25:18.000Z
 */
data class NewsUnit(var title: String? = null, var url:String? = null, var imageUrl:String? = null,
                    var newsSite:String? = null, var summary:String? = null,
                    var publishedAt:String? = null) {
    /**
     * Static variables of this class in old Java way
     */
    companion object {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val newDateFormat = SimpleDateFormat("dd.MM.")
    }
    /**
     * Parses the date in publishedAt as Java Date Object. Assumes that publishedAt uses the
     * same default format as defined in companion objects constant sdf.
     */
    var publishedDate : Date? = null
        get() = if (this.publishedAt != null) sdf.parse(this.publishedAt!!) else null

    /**
     * @return newsUnit in a String format similar to Html for the use of classes like TextField.
     */
    fun toTextViewString() : String {
        var date: String = "??.??"
        if (publishedDate != null) {
            date = newDateFormat.format(this.publishedDate!!)
        }
        return "<resources><p>"+date+
                " <a href='"+this.url+"'>"+ this.title + "</a> "+
                " ("+this.newsSite+")"+
                "</p></resources>"
    }
}
