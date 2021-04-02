package fi.jobapplication.lauripaajarvi.spaceflightapp

import android.text.Editable
import android.widget.EditText
import org.junit.Assert
import org.junit.Test

/**
 * Tests if validate(...) method works in NumberOfNewsValidator class.
 *
 * I have to admit I'm learning that I might have implemented the convert() method and the whole
 * logic behind taking and Editable, and returning an integer in a bit clumsy way, since it would
 * be hard to write a test for it. I suppose TDD way of doing the tests first would really force
 * to write better code.
 */
class NumbersOfNewsValidatorTest {
    var validator = NumberOfNewsValidator()
    @Test
    fun `min returns min`() {
        val result = validator.validate(Constants.MIN_NEWS_ALLOWED)
        Assert.assertEquals(result, Constants.MIN_NEWS_ALLOWED) // MIN_ALLOWED should return the same amount
    }
    @Test
    fun `min-1 returns min`() {
        val result = validator.validate(Constants.MIN_NEWS_ALLOWED - 1)
        Assert.assertEquals(result, Constants.MIN_NEWS_ALLOWED) // MIN_ALLOWED-1 should return MIN_ALLOWED
    }
    @Test
    fun `max returns max`() {
        val result = validator.validate(Constants.MAX_NEWS_ALLOWED)
        Assert.assertEquals(result, Constants.MAX_NEWS_ALLOWED)
    }
    @Test
    fun `max+1 returns max`() {
        val result = validator.validate(Constants.MAX_NEWS_ALLOWED+1)
        Assert.assertEquals(result, Constants.MAX_NEWS_ALLOWED)
    }
}