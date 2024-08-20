# SOS

## Table of Contents
- [Project Description](#project-description)
- [IDE](#ide)
- [Installation](#installation)
- [Contributors](#contributors)

## Project Description
SOS is an Android application designed to provide a quick and efficient way to alert your chosen contacts when you feel unsafe. The app allows users to send an SMS with a chosen text, including their live location, to selected contacts. It also supports sending this information via Telegram using a bot. The app provides three threat levels—high, medium, and low—allowing users to communicate the severity of the situation.



## Branch Information
The code for this project is available on the `bug-fix` branch. Please switch to that branch to see the latest updates.
[View the `bug-fix` branch](https://github.com/MM-Nazari/Compiler-Final-Project/tree/bug-fix)

## Dependencies
This project uses the following libraries:

- [antlr](https://www.antlr.org/)

## IDE
This project is developed using Android Studio.

## Installation
Step-by-step instructions on how to get the development environment running:

### Steps
1. Clone repository to your local system.
2. Launch Android Studio.
3. Click on File > Open.
4. Navigate to the folder where you cloned the repository and select the project folder.
5. Click the Run button ![Run Image](./Pycharm_Run.PNG) to start the project.

## Contributors
- [Arash Asghari](https://github.com/Ali-Noroozbeigy)
- [Mojtaba Bizarar](https://github.com/Ali-Noroozbeigy)

### 2. Features
- **Send Emergency SMS**: Quickly send a custom message with your live location to your chosen contacts.
- **Threat Levels**: Choose from three threat levels (High, Medium, Low) to indicate the severity of the situation.
- **Telegram Integration**: Send emergency alerts through a Telegram bot in addition to SMS.
- **Contact Management**: Access your phone's contacts to easily select who should receive your SOS messages.
- **Location Tracking**: Automatically attach your live location to the alert message.
- **User Authentication**: Secure your app with login and sign-up functionalities, using a database for user management.

### 3. Installation and Setup
**Prerequisites:**
- Android Studio
- Kotlin configured in Android Studio
- Python installed for the Telegram bot
- Firebase (or any other service) for user authentication and database management

**Steps:**
1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/sos.git
   cd sos
   ```

2. **Open in Android Studio:**
   - Open Android Studio.
   - Select "Open an existing Android Studio project."
   - Navigate to the `sos` directory and select it.

3. **Set Up Firebase:**
   - Set up Firebase for user authentication and database. Follow the official [Firebase documentation](https://firebase.google.com/docs/android/setup) for Android.

4. **Configure Telegram Bot:**
   - Create a Telegram bot by following the official [Telegram Bot documentation](https://core.telegram.org/bots#6-botfather).
   - Replace the bot token in the Python script with your bot's token.

5. **Build and Run:**
   - Connect your Android device or start an emulator.
   - Click "Run" to build and run the app on your device.

### 4. Usage
1. **Login or Sign Up:**
   - Open the app and either log in or sign up to create an account.
   
2. **Set Up Emergency Contacts:**
   - Navigate to the contacts page, select your emergency contacts, and save them.

3. **Set Up Default Message:**
   - Customize the base text of the SOS message in the settings.

4. **Trigger SOS:**
   - In case of an emergency, open the app, choose the threat level, and click the SOS button to send the alert.

### 5. Screenshots
Include screenshots of your UI to give users a visual idea of your app. You can display them like this:

**Login Screen**
![Login Screen](path_to_screenshot/login.png)

**Main Dashboard**
![Dashboard](path_to_screenshot/dashboard.png)

To display the images, upload them to your repository (if not already done) and provide the correct path.

### 6. Branches and Code Citation
Your main development is in the `master` branch. You can cite the code like this:

**Main Branch:** `master`

You can also link directly to the repository:

```markdown
[View the repository](https://github.com/yourusername/sos/tree/master)
```

