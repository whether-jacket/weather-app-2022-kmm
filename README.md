<div id="top"></div>

<p align="center">
<img src="https://github.com/whether-jacket/weather-app-2022-kmm/raw/main/docs/android.png?raw=true" alt="" data-canonical-src="" width="340" height="675" />
<img src="https://github.com/whether-jacket/weather-app-2022-kmm/raw/main/docs/ios.png?raw=true" alt="" data-canonical-src="" width="340" height="675" />
</p></br>

<h2 align="center">Weather App 2022 KMM</h2>
<br>

## Built With
* [Kotlin Multiplatform Mobile](https://youtu.be/0xKTM0A8gdI)

## Prerequisites
* [Java JDK Oracle (SE) 11](https://www.oracle.com/java/technologies/downloads/#java11)
* [Ruby v2.6](https://www.ruby-lang.org/en/downloads/)
* [Cocoapods v1.8.4](https://guides.cocoapods.org/using/getting-started.html#installation)
* [JetBrains Toolbox (Optional)](https://www.jetbrains.com/toolbox-app/)
* [Android Studio (Chipmunk or latest)](https://developer.android.com/studio/archive)
* [Android Studio KMM Plugin (Optional)](https://kotlinlang.org/docs/kmm-plugin-releases.html)
* [Xcode](https://apps.apple.com/us/app/xcode/id497799835?mt=12)
* [Xcode Kotlin Plugin (Optional)](https://github.com/touchlab/xcode-kotlin)

## Installation
<!-- Ensure Java Properly Installed -->
<details>
<summary>Java</summary>

<details>
<!-- Ensure Java Home variable is set -->
<summary>1. Java Home variable is set</summary>

```bash
echo $JAVA_HOME
```
Should see
```
/Library/Java/JavaVirtualMachines/jdk-11.0.13.jdk/Contents/Home
```
If you see it, you can skip the two steps below. <br>
</details>


<!-- Ensure Java version is downloaded -->
<details>
<summary>2. Java version is downloaded</summary>

```bash
/usr/libexec/java_home -V
```
Should see installed JDKs:
```
17.0.1 (x86_64) "Oracle Corporation" - "Java SE 17.0.1" /Library/Java/JavaVirtualMachines/jdk-17.0.1.jdk/Contents/Home
16.0.1 (x86_64) "Oracle Corporation" - "OpenJDK 16.0.1" /Users/user/Library/Java/JavaVirtualMachines/openjdk-16.0.1/Contents/Home
11.0.13 (x86_64) "Oracle Corporation" - "Java SE 11.0.13" /Library/Java/JavaVirtualMachines/jdk-11.0.13.jdk/Contents/Home
...
```
If not found, have not downloaded the JDK properly.<br>
</details>

<!-- Ensure correct JDK v11 is showing -->
<details>
<summary>3. Correct JDK v11 is showing</summary>

```bash
/usr/libexec/java_home -v 11
```
Should see
```
/Library/Java/JavaVirtualMachines/jdk-11.0.13.jdk/Contents/Home
```
If you see a non-Oracle JDK, like open JDK, you need to uninstall it first. <br>
</details>

<!-- Set Java Home variable -->
<details>
<summary>4. Set Java Home variable</summary>

Find out what shell version you're using:
```bash
echo $SHELL
```
If you're using [ZSH](https://ohmyz.sh): you ought to be updating `~/.zshrc` else `~/.bash_profile` for the following steps. <br>

Update your profile:
```bash
[emacs/vim/atom] ~/.zshrc
```
Add the following line:
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 11)
```
Update shell profile
```bash
source ~/.zshrc
```
</details>
</details> <!-- end Java -->

<!-- Enable Mac Developer Tools -->
<details>
<summary>Mac Developer Tools</summary>


<details>
<summary>1. Install Mac dev tools</summary>

Install Mac dev tools:
```bash
xcode-select --install
```
Accept dev tools agreement:
```bash
sudo xcodebuild -license
```
</details>

<details>
<summary>2. Enable command line tools</summary>

`Xcode -> Preferences -> Locations -> Command Line Tools -> [Select latest]`
<p align="center">
<img src="https://github.com/whether-jacket/weather-app-2022-kmm/raw/main/docs/xcode-1.png?raw=true" alt="" data-canonical-src="" />
</p>

If you don't do this, on builds you may see the following errors:
```bash
Kotlin/Native: KonanExternalToolFailure "The /usr/bin/xcrun command returned non-zero exit code: 72"
```
```bash
xcrun: error: unable to find utility "xcodebuild", not a developer tool or in PATH
```
</details>
</details> <!-- end enable mac dev tools -->

<!-- Android Studio -->
<details>
<summary>Android Studio</summary>

<details>
<summary>1. Android Studio Install</summary>

When installing Android Studio, choose custom:<br>
<p align="center">
<img src="https://github.com/whether-jacket/weather-app-2022-kmm/raw/main/docs/android-studio-1.png?raw=true" alt="" data-canonical-src=""/>
</p>
Insert custom directory for Android SDK:<br>
<p align="center">
<img src="https://github.com/whether-jacket/weather-app-2022-kmm/raw/main/docs/android-studio-2.png?raw=true" alt="" data-canonical-src=""/>
</p>
</details>

<details>
<summary>2. Set Android shell variables</summary>

After installation flow, set `ANDROID_HOME` & `ADB` variables in shell:
```bash
export ANDROID_HOME="/Applications/AndroidSDK/"
export adb="/Applications/AndroidSDK/platform-tools/adb"
export ADB="/Applications/AndroidSDK/platform-tools/adb"
```
</details>

<details>
<summary>3. Create an Android Emulator</summary>

* Open the Android Virtual Device (AVD) Manager: `Tools -> AVD Manager` <br>
* Select **+ Create New Virtual Device...**. <br>
* From the large devices list, select latest Pixel, i.e. Pixel 3 XL. <br>
* Next you need to select an image: Select newest API level/x86_64/Google Play <br>
* Last step, select `Advanced -> Graphics -> Hardware`
</details>
</details> <!-- end Android -->

<!-- Cocoapods -->
<details>
<summary>Cocoapods</summary>

<details>
<summary>1. Ensure correct Ruby version</summary>

Ensure Ruby v2.6 is installed. Note: does not work on v3+.
```bash
ruby -v
```
</details>

<details>
<summary>2. Install cocoapods</summary>

```bash
sudo gem install cocoapods -v 1.8.4
```
</details>

<details>
<summary>3. Error</summary>

If you have installation issues, check [here](https://stackoverflow.com/questions/60481623/errors-when-installing-cocoapods-with-gem) for solutions.
</details>
</details> <!-- end Cocoapods -->

<!-- Build Android -->
<details>
<summary>Build Android</summary>

<details>
<summary>1. Open project in Android Studio</summary>

Open the project in Android Studio. <br>
Wait for indexing to finish. <br>
Make sure you see the run config for the app:
<p align="center">
<img src="https://github.com/whether-jacket/weather-app-2022-kmm/raw/main/docs/android-studio-3.png?raw=true" alt="" data-canonical-src=""/>
</p>
Run the Android app on either emulator or phone.<br>
</details>

<details>
<summary>2. Error: Android Gradle requires Java 11 to run</summary>

If you see following error:
<p align="center">
<img src="https://github.com/whether-jacket/weather-app-2022-kmm/raw/main/docs/android-studio-4.png?raw=true" alt="" data-canonical-src=""/>
</p>

Go to `Preferences -> Build, Execution, Deployment -> Build Tools -> Gradle -> Select Gradle JDK`
<p align="center">
<img src="https://github.com/whether-jacket/weather-app-2022-kmm/raw/main/docs/android-studio-5.png?raw=true" alt="" data-canonical-src=""/>
</p>  
</details>  

<details>
<summary>3. Error: SDK location not found</summary>

If you see the following error:
```bash
SDK location not found. Define location with an ANDROID_SDK_ROOT environment variable or by setting up the sdk.dir path in your project's local properties file...
```
Project does not know the location of your local Android SDK. <br>
Open `local.properties`<br>
```bash
sdk.dir=[ANDROID_SDK_ROOT location as used during installation]
```
</details>

<details>
<summary>4. Gradle Sync Fail: 'pod install' fail</summary>

If you see the following error:
```bash
Please, check that file "../iosApp/Podfile" contains following lines in header: source 'https://cdn.cocoapods.org'
```
Open `ios` directory. <br>
```bash
sudo gem install cocoapods
```
After successful pod generation close the terminal. <br>
```bash
sudo gem install cocoapods-generate
```
Close and reopen the terminal again <br>
Edit `~/.zshrc`& add the following: <br>
```bash
export LANG=en_US.UTF-8
```
`source ~/.zshrc` or quit & reopen terminal <br>
Source: https://stackoverflow.com/questions/69805554/kmm-project-setup-with-cocoapods-fails-on-first-compile
</details>


</details>
<!-- end Android -->

<!-- Build iOS -->
<details>
<summary>Build iOS</summary>

<details>
<summary>1. Gradle Build</summary>

Go to project folder:
```bash
cd weather-app-2022-kmm
```
Run gralde build:
```bash
./gradlew build
```
Open Xcode **workspace** project in the `ios/` folder: `KaMPKitiOS.xcworkspace`. <br>
Run the iOS app on Simulator. <br>
</details>

<details>
<summary>2. Error: No such module</summary>

If you get the following compilation error:
```bash
no such module: 'shared'
```
Close Xcode. <br>
Delete `Pods/` folder located in the root directory of the iOS project.<br>
Run the command `pod install` in that same iOS root directory (which is `/weather-app-2022-kmm/ios/` to be specific).<br>
This will generate a new `Pods` folder. Reopen the `.xcworkspace` file and try to build again.
</details>

<details>
<summary>3. Error: Framework not found</summary>

If you see the following error:<br>
```bash
Framework not found shared_umbrella
```
You probably opened the `.xcodeproj` file instead of the `.xcworkspace`. <br>
Close out the `.xcodeproj` and open the `.xcworkspace` and run again.
</details>
</details> <!-- end iOS -->
