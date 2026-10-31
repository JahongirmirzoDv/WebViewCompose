# Accompanist WebView with Jetpack Compose

A compact Android browser UI sample built with Kotlin, Jetpack Compose, and Accompanist WebView.

## What it demonstrates

- A WebView initialized with a sample web URL.
- Back and forward navigation through Accompanist's navigator.
- Loading progress and an error indicator.
- An editable field displaying the current page URL.

## Source guide

- [app/src/main/java/com/mobiledv/webviewcompose/MainActivity.kt](app/src/main/java/com/mobiledv/webviewcompose/MainActivity.kt) — Compose app entry point
- [app/src/main/java/com/mobiledv/webviewcompose/ui/MainScreen.kt](app/src/main/java/com/mobiledv/webviewcompose/ui/MainScreen.kt) — browser UI and WebView integration
- [app/build.gradle](app/build.gradle) — Compose and Accompanist dependencies

## Open the project

The checked-in build uses Android Gradle Plugin 7.3.0-beta05, Kotlin 1.6.10, Gradle 7.4, Compose 1.1.1, Accompanist WebView 0.25.0, compile/target SDK 32, and minimum SDK 21.

Open the repository in Android Studio and let it use the checked-in Gradle configuration. Select the `app` configuration and an Android device or emulator meeting the minimum SDK requirement.

## Sample limits

The URL field updates local text state but is not wired to load a newly typed address. JavaScript is enabled. This historical dependency snapshot needs compatibility and WebView behavior review before reuse.

## Project status

This repository is a learning sample. Build and device behavior have not been verified as part of this documentation update.

