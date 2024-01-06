# आचार्य प्रशान्त Book App 📚:
Android app built with `Jetpack Compose` shows books information fetched from Acharya Prashant app Api.

<img src="https://github.com/rishiroid/acharya_prashant/assets/86010153/27f933aa-b9c3-49d6-aa20-95e2a06557d5" alt="splash screen preview" width="500"/>

## 😎 App Overview


| Screen | Preview |
| :----- | :------:|
|  **Spash Screen** _(Default destination)_<br><br> • Spash screen will display first when opening the app.<br> •Displaying logo. | <img src="assets/splash_screen_gif.gif" alt="splash screen preview" width="180" /> |
|        |
|  **Main Screen**<br><br> • Shows list of books in vertical grid layout.<<br> • Custom TopAppBar container with search icon.<br> • Navigates to expandable Search bar clicking search icon.<br> • Navigates to Book screen with bookId clicking any book item.<br> • Shimmering effect will be shown untill data is fetched.<br> • Image fetching with Coil. | <img src="assets/main_screen_gif.gif" alt="main screen preview" width="180" /> |
|        |                                                                              |
| **Search Screen**<br><br> • Shows list of books based on user submitted query.<br> • Implemented the best way of searching .<br> <br> • Customize TextField contained in TopAppBar.<br> • Navigates to Book screen with bookId clicking any book item.<br> • CircularProgressIndicator will loading on search began.<br> • Handles query & value changes correctly to fetch results. | <img src="assets/search_bar_gif.gif" alt="Search bar preview" width="180" /> |
|        |                                                                                  |
| **Book Screen**<br><br> • Shows user selected book from other screens.<br> • CircularProgressIndicator will be shown untill data is fetched.<br> • Image fetching with Coil, manages state error/placeholder. | <img src="assets/book_screen_gif.gif" alt="Detail screen preview" width="180" /> |

## 🛠 Build With
- #Kotlin: First class for Android development.
- #Coroutines: Asynchronous programming library for simplifying background operations.
- #Flow: Reactive stream library for asynchronous and data-driven programming.
- #Retrofit/OkHttp3: Networking libraries for making HTTP requests.
- #Dagger #Hilt: Dependency injection library for Android.
- #Navigation Component: Android Jetpack's library for navigating between screens.
- #ViewModel-ktx: Part of the Android Architecture Components for managing UI-related data.
- #Coil: Image loading library for Android apps.
  
## Prerequisites
Before running this application, ensure that you have:
<br> •	Android Studio: Download and install Android Studio(Recommended to download latest one).
<br> •	Android SDK: Ensure you have the necessary Android SDK and platform tools installed.

## Installation / Run the Application:
### 1.	Clone the repository:
•  Download and extract the zip file of the project.
<br> 	Alternatively,
<br> • 	Open a git bash terminal or command prompt if not have then install git.
<br> •	Run the following command:
```kotlin
git clone https://github.com/rishiroid/acharya_prashant/
```
### 2.	Open the Project in Android Studio:
•	Launch Android Studio.
<br> •	Click on File -> Open.
<br> •	Navigate to the directory where the project was cloned or extracted, and select the project folder.
<br> •	Wait for Android Studio to sync and build the project.

### 3.Run the Application:
 • Connect your Android device or use an emulator.
<br> • In Android Studio, select the target device.
<br> • Click on the Run button (green triangle) in the toolbar, or press Shift + F10.
<br> • If Dont know how to connect device [visit here](https://developer.android.com/studio/run/device) which contains proper steps to connect device by Wi-Fi or via USB.

### 4.Interact with the Application:
•	Once the app is installed on the device/emulator, explore the functionality.



