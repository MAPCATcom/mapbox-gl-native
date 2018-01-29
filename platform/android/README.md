# Mapcat Maps for Android

This project is based on the [Mapbox Maps SDK for Android](https://www.mapbox.com/android-sdk/). We intend to periodically update this fork to be up-to-date to the latest Mapbox features.
The aim of this project is to make visualizing of Mapcat maps possible on Android devices.

## Getting Started

This guide aims to help developers in building and running the forked SDK and the Mapcat Android TestApp demo application.
If your are interested in a pre-built version of our application, you can download it from... **TODO**

#### Getting the source

Clone the git repository
```bash
git clone https://github.com/MAPCATcom/mapbox-gl-native.git && cd mapbox-gl-native
```

#### Installing dependencies

These dependencies are required for all operating systems and all platform targets.

- Latest stable [Android Studio](https://developer.android.com/studio/index.html)
- Update the Mapcat Maps for Android with the latest
  - Android SDK Build-Tools
  - Android Platform-Tools
  - Android SDK Tools
  - CMake
  - NDK
  - LLDB
- Modern C++ compiler that supports `-std=c++14`\*
  - clang++ 3.5 or later or
  - g++-4.9 or later
- [Node.js](https://nodejs.org/)
  - make sure [npm](https://www.npmjs.com) is installed as well
- [ccache](https://ccache.samba.org/) (optional)

**Note**: We partially support C++14 because GCC 4.9 does not fully implement the
final draft of the C++14 standard.

### Opening the project

#### macOS

Execute the following command in this repository's root folder to generate the required build files and open the project with Android Studio:

```
make aproj
```

#### linux

run `make android-configuration` in the root folder of the project and open the Android Studio project in `/platform/android`.

If you are using Arch Linux, install [ncurses5-compat-libs](https://aur.archlinux.org/packages/ncurses5-compat-libs).

### Project configuration

##### Setting Mapcat Access Token

_The test application (used for development purposes) uses Mapcat vector tiles, which require a Mapcat account and API access token. Get a free access token at [pro.mapcat.com/planpricing/](https://pro.mapcat.com/planpricing/)._

The test application asks for your Mapcat access token upon startup.

### Running project

Run the configuration for the `MapcatAndroidTestApp` module and select a device or emulator to deploy on. Based on the selected device, the c++ code will be compiled for the related processor architecture. You can see the project compiling in the `View > Tool Windows > Gradle Console`.
