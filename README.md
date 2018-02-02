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
This project is licensed under the GNU GPL, see the LICENSE file for the entire
license.
