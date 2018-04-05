# senior-design-Beacon-App
Description:
This app uses Estimote beacons in development.

Proximity is about your apps detecting they’re near areas of interest. First, tag the area with a Proximity Beacon (Location Beacons work too), and call it something—e.g., “my desk.” From now on, Bluetooth-enabled devices such as iOS and Android smartphones can detect that it’s in proximity of your desk.

All beacons are managed using an estimote account where you can add, personalize your beacons and know the activites and states of your beacon.

This app is a sample app that demos notification pushing of three proximity beacons

Usage:
To Use this app, you need to have three proximity beacons, a valid estimote account and android studio.

1. Look for the three according beaconids in your estimote account.
    A sample beaconid is a string like this:"d0995e7d651deea96ee42cbd5d09ca3d"

2. Fork the entire project and mirgrate it to your android studio.

3. Open directory app/src/main/java/com/estimote/notification/Activity_home.java
    in line 40-42, we can see three Strings of IDs, replace those with your own beacons' IDs. Connect the android studio with
    an android phone and run the app.



