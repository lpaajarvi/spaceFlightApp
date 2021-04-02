package fi.jobapplication.lauripaajarvi.spaceflightapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.*
import androidx.core.text.HtmlCompat
import fi.jobapplication.lauripaajarvi.spaceflightapp.Constants.Companion.MAX_NEWS_ALLOWED

class MainActivity : AppCompatActivity() {

    lateinit var networkModule : NetworkModuleInterface
    lateinit var newsUnitList: List<NewsUnit?>

    lateinit var question : TextView
    lateinit var data : TextView
    lateinit var fetchButton : Button

    lateinit var editNumber : EditText

    var numberOfNews = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        networkModule = ExampleNetworkModule()

        question = findViewById(R.id.Question)
        fetchButton = findViewById(R.id.FetchAndParseButton)
        data = findViewById(R.id.DataView)
        editNumber = findViewById(R.id.EditNumber)

        question.setText(question.text.toString() + " (max "+MAX_NEWS_ALLOWED.toString()+")")
        editNumber.setText(""+numberOfNews.toString())
        editNumber.setHint("(1-"+MAX_NEWS_ALLOWED.toString()+")")


        editNumber.addTextChangedListener(object: TextWatcher {

            /**
             * Validates the input on EditField right after there's any change in it, and puts.
             * the value in the numberOfNews variable for the use of "clickedFetchAndParseButton()"
             *
             * Later part of the code is about UI;
             *  - If numberOfNews is set to 0, fetchButton is disabled
             *  - If EditField has bigger number than what is actually allowed, it is changed
             *    to value that is actually set to be fetched
             */
            override fun afterTextChanged(s: Editable?) {

                var validator = NumberOfNewsValidator()

                numberOfNews = validator.convert(s)

                // This part is about UI and could be replaced with something else
                // Watch out for infinite loop making the same change though with
                // editNumber.setText part.
                runOnUiThread() {
                    if (numberOfNews <= 0) {
                        fetchButton.setEnabled(false)
                    } else {
                        fetchButton.setEnabled(true)

                        if (editNumber.text.toString().toInt() > numberOfNews) {
                            editNumber.setText(numberOfNews.toString())
                        }
                    }
                }
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
        })
    }

    /**
     * Will use networkModule that has been initialized onCreate, for fetching and parsing
     * a List of NewsUnits, and then making a string with HTML tags with it.
     *
     * In UI thread the data/DataView is updated.
     */
    fun clickedFetchAndParseButton(button: View) {

        Thread() {
            networkModule.fetchAndParse(numberOfNews) {
                this.newsUnitList = it
                var newsString = ""
                for (element in newsUnitList) {
                    newsString += element?.toTextViewString()+"\n"
                }
                runOnUiThread() {
                    data.setText(HtmlCompat.fromHtml(newsString, HtmlCompat.FROM_HTML_MODE_LEGACY))
                    data.setClickable(true)
                    data.setMovementMethod(LinkMovementMethod.getInstance())
                }
            }
        }.start()
    }
}




