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

Content:
This app consists of activities(views) which are called home, main and setting. 

The home activity appears when a user first login. It shows the working status of three beacons and also provides users turn on/off the beacons.

In the home activity, a user is able to navigate to the setting activity where he/she can change the settings of each beacon. Settings include a beacon's name, notification message as well as a desired time the user prefers the respective beacon to notify him/her. 

To successfully set the time, a user needs to go to the main activity by clicking on the "set time" button in the setting so that he will be navigated to the main activity. In the main activity, a user will see a clock and can set a time there. The desired working time of the beacon will then be (time set) ± 5 minutes. This design is made to prevent users from getting annoyed by the frequent notifications.

About settings:
All the settings will not change when restarting the app and is saved using the SharedPreference.

Ways to improve:
1. Provide users with more options for changing the time.

2. There is 15s delay between a notification is pushed and a beacon is ready to communicate with the app. This is partly software issue and partly hardware issue. Reduing this delay can greatly improve user experiences.

3. The app does not provide user the ability to change the distance(range) of the beacon. 

Build:
it is build using android sdk version 23.

Download:
To download this app,
1. turn on your phone's developer mode.
2. download android studio and clone this entire project and put it under android studio.
3. open it with android studio and connect your android phone with the computer using a usb.
4. click on run and select your phone.



