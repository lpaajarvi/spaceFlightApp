package fi.jobapplication.lauripaajarvi.spaceflightapp

/**
 * Abstraction of NetworkModule: I figured Interface might be the best way to make sure Network
 * Module can be replaced without making adjustments elsewhere:
 *
 * NetworkModule that implements this interface needs to be able to perform the
 * connection as well as the conversion of
 * json to List of NewsUnits and return the result as a callback
 *
 * I was debating whether it would be good idea to have conversion of NetworkModule in its own
 * method if one would want to use Json instead of mapping them directly to this List of instances
 * of NewsUnit class, but in the end I came to the conclusion that "replaceable" NetWork module
 * should probably do the conversion work as well. Otherwise there should be more code needed
 * in the app to prepare for different ways which might never be used and it would probably be waste
 * of time and bloat the code unnecessarily.
 */

interface NetworkModuleInterface {

    fun fetchAndParse(howMany: Int, callback: (List<NewsUnit?>) -> Unit)
}