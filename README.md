# Spaceflight App

Android App coded in Kotlin that uses API
https://test.spaceflightnewsapi.net/api/v2/articles?_limit=7
to show Spaceflight related news info to user.

Includes some unit testing in directory
https://github.com/lpaajarvi/spaceFlightApp/tree/main/app/src/test/java/fi/jobapplication/lauripaajarvi/spaceflightapp

and a NetworkModule interface which should allow replacement of
Network Module.
ExampleNetworkModule uses Jackson library to parse results in a List of NewsUnit class instances.

Most of the code can be found in the directory
https://github.com/lpaajarvi/spaceFlightApp/tree/main/app/src/main/java/fi/jobapplication/lauripaajarvi/spaceflightapp

