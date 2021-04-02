package fi.jobapplication.lauripaajarvi.spaceflightapp

import android.text.Editable
import fi.jobapplication.lauripaajarvi.spaceflightapp.Constants.Companion.MAX_NEWS_ALLOWED
import fi.jobapplication.lauripaajarvi.spaceflightapp.Constants.Companion.MIN_NEWS_ALLOWED

class NumberOfNewsValidator() {

    /**
     * Designed for use with EditText. Guarantees that valid number will be derived no matter what
     * end user types.
     *
     * @param s text field which may or may not have text in it
     * @return Int in the range of (MIN_NEWS_ALLOWED .. MAX_NEWS_ALLOWED), weill return MIN if
     * for some reason coversion fails or the field is empty
     */
    fun convert(s : Editable?) : Int {
        var convertedNumber = MIN_NEWS_ALLOWED

        if (s.toString() != "") {
            try {
                convertedNumber = validate(s.toString().toInt())

            /**
             * If for some reason converting to int doesn't work, then the error is caught and
             * the app won't crash. Textfield should not allow non-numbers here though if the
             * activity_main.xml is defined accordingly (android:inputType="number" ), so this is
             * just a precaution in case it would be used elsewhere or xml is modified
             */

            } catch (e: Exception) {

            }
        }
        return convertedNumber
    }
    /**
     * Makes sure that number given will be returned in range of MIN and MAX defined in
     * Constants.Companion.
     *
     * @param number number of validation
     * @return number as it is or MIN if its < MIN and MAX if it's > MAX
     */
    fun validate(number : Int) : Int = when {
        number < MIN_NEWS_ALLOWED -> {
            MIN_NEWS_ALLOWED
        }
        number > MAX_NEWS_ALLOWED -> {
            MAX_NEWS_ALLOWED
        }
        else -> {
            number
        }
    }

}