# Sample Tested App

#### Purpose
This app downloads pictures from reddit, and is well tested. It contains `Unit Tests`, `Espresso Debug UI Tests` and `UiAutomator Release UI Tests`. It is intended to illustrates unit and UI testing practices for a Kotlin App.

Testing Libraries used:
  - Mockito
  - Robolectric
  - Espresso
  - UiAutomator
  - Mockito-Android
  - Mockito-Kotlin
  - Falcon
  - OkHttp3 IdlingResources
  
#### Examples
Example branches

- `examples/mvp-callbacks` Shows how to unit test a MVP style app with minimal additional libraries
- `examples/mvvm-rx [Coming soon]` Shows how to unit test a MVVM style app with RX and retrofit

#### Running the Ui Tests
1. Change the setting in build.gradle to either "debug" or "release"
    ```gradle
      e.g. testBuildType "debug"
    ```
1. Select matching Build variant from Build Variants window
1. Right click on the appropriate Android Test package and select "Run Tests"