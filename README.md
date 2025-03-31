# Android ESP32 Bluetooth Communication App (Open Source)

This open source Android application provides a foundation for establishing a two-way Bluetooth Low Energy (BLE) communication system with an ESP32 module. The app can control hardware (for example, turning an LED on/off remotely), is easly tailored to your intended use case and display sensor data sent from the ESP32. Depending on the use case, the IoT device can support applications ranging from facial recognition door locks to smart water irrigation systems and home security sensors.

## Features

- **Two-Way BLE Communication**
  - **Control:** Send commands (e.g., LED on/off) from the Android app.
  - **Monitoring:** Receive data from the ESP32 via BLE.
- **Scalable Architecture**
  - Built using six well-commented Java classes.
  - Uses BLE characteristics: the ESP32 notifies via its TX UUID while the Android app receives on its RX UUID (and vice versa).
- **Optional Google Sign-In Integration**
  - Integrated with the Google Sign-In API (currently disabled). Enable it by signing up for a free client Server-ID with Google if needed.

## How It Works

- **ESP32 as BLE Server:** The ESP32 broadcasts data via the TX characteristic UUID.
- **Android App as BLE Client:** The app receives data on its RX characteristic (corresponding to the ESP32's TX) and sends commands back via its TX (received by the ESP32’s RX).
- The code is designed to be easily scalable, making it adaptable for different IoT scenarios.

## Requirements

- **Android SDK:** Minimum version 26.
- **Hardware:** An Android device with BLE support and an ESP32 module.
- **Development:** Android Studio.

## Getting Started

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/KELVI23/android-ESP-32-bluetooth-arduino.git
   cd android-ESP-32-bluetooth-arduino
2. **Open in Android Studio:**
   - Import the project.
   - Ensure your project targets Android SDK 26 or higher.
3. **Configure BLE:**
   - Enable BLE on your Android device.
   - Pair the device with your ESP32.
4. **(Optional) Enable Google Sign-In:**
   - Follow the [Google Sign-In Quickstart Guide](https://developers.google.com/identity/sign-in/android/start) to obtain a client Server-ID.
   - Configure your project settings to enable Google Sign-In.
5. **Run the App:**
   - Build and deploy the app on your device or emulator.
   - Test by sending commands (e.g., toggling an LED) and verifying that data is received from the ESP32.


## Support

For issues or feature requests, please open an issue on the [GitHub Issues](https://github.com/KELVI23/android-ESP-32-bluetooth-arduino/issues) page.

## Contributing

Contributions are welcome! Please fork this repository and submit a pull request with your improvements.

## License

This project is licensed under the Apache License, Version 2.0. See the [LICENSE](LICENSE) file for details.
