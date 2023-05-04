WorkManager Codelab
===================================

This repository contains the code for the [WorkManager Codelab](https://developer.android.com/codelabs/basic-android-kotlin-compose-workmanager).

Introduction
------------

At I/O 2018, Google announced [Android Jetpack](https://developer.android.com/jetpack/), a collection of libraries, tools, and architectural guidance to accelerate and simplify the development of great Android apps. One of those libraries is the [WorkManager library](https://developer.android.com/topic/libraries/architecture/workmanager/). The WorkManager library provides a unified API for deferrable one-off or recurring background tasks that need guaranteed execution. You can learn more by reading the [WorkManager Guide](https://developer.android.com/topic/libraries/architecture/workmanager/), the [WorkManager Reference](https://developer.android.com/reference/androidx/work/package-summary) or doing the [WorkManager Codelab](https://developer.android.com/codelabs/basic-android-kotlin-compose-workmanager).

Pre-requisites
--------------

* Familiarity with how to open, build, and run apps with Android Studio.

* Make sure Android Studio is updated, as well as your SDK and Gradle. Otherwise, you may have to wait for a while until all the updates are done.

* A device or emulator that runs API level 21+

You need to be solidly familiar with the Kotlin programming language, object-oriented design concepts, and Android Development Fundamentals.

In particular:

* Basics of [Jetpack Compose](https://developer.android.com/courses/pathways/compose)
* Some familiarity with URIs and File I/O
* Familiarity with [Kotlin Flow](https://developer.android.com/kotlin/flow) and [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)

Getting Started
---------------

1. [Install Android Studio](https://developer.android.com/studio/install.html), if you don't already have it.
2. Download the sample.
3. Import the sample into Android Studio.
4. Build and run the sample.

Notes:
- The application code contains a battery not low constraint. If the device/emulator has a low battery, the application will appear to hang until this constraint is met.

- The app requires notifications to be enabled. To enable notifications, navigate to the Android Settings menu > Apps > Blur-O-Matic > Notifications > Enable "All Blur-O-Matic notifications".
