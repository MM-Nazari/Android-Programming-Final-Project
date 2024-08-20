# SOS

## Table of Contents
- [Project Description](#project-description)
- [Features](#features)
- [Usage](#usage)
- [Screenshots](#screenshots)
- [IDE](#ide)
- [Installation](#installation)
- [Contributors](#contributors)

## Project Description
SOS is an Android application designed to provide a quick and efficient way to alert your chosen contacts when you feel unsafe. The app allows users to send an SMS with a chosen text, including their live location, to selected contacts. It also supports sending this information via Telegram using a bot. The app provides three threat levels—high, medium, and low—allowing users to communicate the severity of the situation. <br />
The Proposal is in [Proposal](./Proposal.pdf) <br />
The Final Report description is in [Final Report Description](./FinalReport.pdf) <br />
The implemented Report is in [Final Report](./FinalReport_9931005_9931007_9931061.pdf)

### Branch Information
The code for this project including telegram bot is available on the `master` branch. Please switch to that branch to see the latest updates.
[View the `master` branch](https://github.com/MM-Nazari/Android-programming-Final-Project/tree/master) <br />
The code for this project including authentication is available on the `pishNoskhe` branch. Please switch to that branch to see the latest updates.
[View the `pishNoskhe` branch](https://github.com/MM-Nazari/Android-programming-Final-Project/tree/pishNoskhe) <br />

### Dependencies
This project uses the following libraries:
- [Figma](https://www.figma.com/)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [ServerAPI](https://pymongo.readthedocs.io/en/stable/api/pymongo/server_api.html)
- [Room](https://developer.android.com/training/data-storage/room/)
- [SMSManager](https://developer.android.com/reference/android/telephony/SmsManager)
- [LocationManager](https://developer.android.com/reference/android/location/Location)

## Features
- **Send Emergency SMS**: Quickly send a custom message with your live location to your chosen contacts.
- **Threat Levels**: Choose from three threat levels (High, Medium, Low) to indicate the severity of the situation.
- **Telegram Integration**: Send emergency alerts through a Telegram bot in addition to SMS.
- **Contact Management**: Access your phone's contacts to easily select who should receive your SOS messages.
- **Location Tracking**: Automatically attach your live location to the alert message.
- **User Authentication**: Secure your app with login and sign-up functionalities, using a database for user management.

## Usage
1. **Login or Sign Up:**
   - Open the app and either log in or sign up to create an account.
   
2. **Set Up Emergency Contacts:**
   - Navigate to the contacts page, select your emergency contacts, and save them.

3. **Set Up Default Message:**
   - Customize the base text of the SOS message in the settings.

4. **Trigger SOS:**
   - In case of an emergency, open the app, choose the threat level, and click the SOS button to send the alert.

## Screenshots
Include screenshots of your UI to give users a visual idea of your app. You can display them like this:

**Login Screen**
![Login Screen](path_to_screenshot/login.png)

**Main Dashboard**
![Dashboard](path_to_screenshot/dashboard.png)

To display the images, upload them to your repository (if not already done) and provide the correct path.

## IDE
This project is developed using Android Studio.

## Installation
Step-by-step instructions on how to get the development environment running:

### Steps
1. Clone repository to your local system.
2. Launch Android Studio.
3. Click on File > Open.
4. Navigate to the folder where you cloned the repository and select the project folder.
5. Sync the project with Gradle Files.
6. Click the Run button ![Run Image](./Visual-Studio_Run.PNG) to start the project.

## Contributors
- [Arash Asghari](https://github.com/arashari44)
- [Mojtaba Bizarar](https://github.com/mojtababizarar)


