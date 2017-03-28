# Sealed Classes

Kotlin supports sealed classes. But to use them with Parceler, a custom
converter needs to be supported. Can we do better?

## Instructions

1. Install the application.
2. Turn on the `Don't keep activities` option in the Developer settings.
3. Toggle the recent applications menu and navigate back: app should crash.
4. Specify a custom converter (included) for the Data class: no crash.
