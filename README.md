# Field Map
## A Wi-Fi based positioning tool

This program was created during the ABSA Aliens Internship program between the
29th of January and the 2nd of February 2018. It attempts to use fingerprints of
Wi-Fi signals in a building to locate which room a user is in without needing
access to a GPS system.

## How it works
This proof of concept requires a premade map of the building. Each floor is
split into rooms. A few dozen samples of the wifi signals in each room are taken
and those signals are transformed into simple fingerprints, noting the mac
address, maximum and minimum signal strength for each AP visible.

This data was uploaded to an online database.

When in operation, the navigation app uses simple linear interpolation and a few
bias values to score each room fingerprint on how closely it matches the current
readings. Whichever room scores highest according to the Classifier is considered
to be the room the user is in. A simple map with collisions was used to allow
the user to navigate the building.

## Abilities and Limitations
The Wi-Fi positioning system excels at locating users in individual rooms and
corridors, thanks to the unique signal profile numerous walls creates. In open
spaces, larger areas are needed to see meaningful results and adjacent spaces
sometimes become confused. More advanced classification functions or more
granular fingerprints could improve these readings.

## Contributors
[@KaliumPuceon](https://github.com/KaliumPuceon), [@ForeverANoob](https://github.com/ForeverANoob) and [@nyashabryan](https://github.com/nyashabryan) were responsible for this project
during the internship.

## Tools
This was built in Android Studio, for Android KitKat and higher. The fingerprint
data is stored in a free FireBase database. A small number of custom tools were
used to get fingerprints and make them suitable for upload, which will be added
to the repository soon.

## License
This project is licensed under Apache 2.0, see the LICENSE file for the entire
license.

## Some notes on internal operation
This app is a slightly convoluted thing because the authors are all novices. In
this regard, here's a primer on the internals.

On creation, the LoadUpActivity calls on a Firebase database and produces a Floor
object, which is then passed on to the rest of the program. The Floor is loaded
into a Classifier, which can be called on to find out which Room the user is in.
The CustomView calls the Classifier every few seconds, and takes the Room object
it produces. This Room object is used by the Map and Graph to find out where the
user is in space. That information is used by the Map to draw the user in the
layout of the office. The Graph plots a path from the user position to their
target, and the Map draws this onto the layout of the office. There are several
helper classes that assist this.

If you have questions about any single section, these people probably know them
best:

* Database: @NyashaBryan
* Classifier: @KaliumPuceon
* Drawing System: @ForeverANoob
