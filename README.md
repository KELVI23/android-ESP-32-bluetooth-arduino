The purpose of the Android app is to establish a two-way communication system with Bluetooth-Low-Energy for Critical Environment Technologies Inc. It is able to control for example an LED on/off remotely and we'll also be able to see some arbitrary values that are sent from the ESP32 to the Android app. Depending on the chosen application, the IoT device the possibilities range from facial recognition door locks to IoT Based Smart Water Irrigation System and door state sensor readings for a home security system.

The application is coherent and made up of 6 Java classes in well-commented code with the ESP32 (acting as the server) "notifies" the client via the TX characteristic UUID and data is sent to the ESP32 and received via the RX characteristic UUID. However, since there is sending and receiving, TX on the ESP32 is actually RX on the Android app.

Made use of the google sign-In API but it is currently disabled and can be enabled by signing up for a free client Server ID with google. The main focus was on functionality and the back-end of the app.

*Requirements:*
Minimum Android SDK version 26.

Google Sign-In Quickstart
=========================

The Google Sign-In Android quickstart demonstrates how to authenticate a user with GoogleSignInClient.

Introduction
------------

- [Read more about Google Sign-In](https://developers.google.com/identity/sign-in/)

Getting Started
---------------

- Follow the [quickstart guide](https://developers.google.com/identity/sign-in/android/start) to set up your project in Android Studio.
- Run the sample on your Android device or emulator.
- The running sample allows Google accounts on the device to sign-in.

Screenshots
-----------
![Screenshot](app/src/main/sign-in-sample.png)

Support
-------

- Stack Overflow: http://stackoverflow.com/questions/tagged/google-identity

If you've found an error in this sample, please file an issue:
https://github.com/googlesamples/google-services/issues

Patches are encouraged, and may be submitted by forking this project and
submitting a pull request through GitHub.

License
-------

Copyright 2015 Google, Inc.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.
