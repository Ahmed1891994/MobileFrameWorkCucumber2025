# MobileFrameWorkCucumber2025

## Test Automation Framework

This README provides a step-by-step guide to setting up, configuring, and running the test automation framework for mobile applications on Android and iOS platforms. The framework uses Appium, Cucumber, and TestNG and supports both local execution and cross-platform testing on BrowserStack.

## Table of Contents

- [Quick Start](#quick-start)
- [Prerequisites](#prerequisites)
- [Framework Setup](#framework-setup)
- [Configuration](#configuration)
  - [Android](#android-configuration)
  - [iOS](#ios-configuration)
  - [BrowserStack](#browserstack-configuration)
- [Running Tests](#running-tests)
  - [Local Execution](#local-execution)
  - [BrowserStack Execution](#browserstack-execution)
- [Test Data Management](#test-data-management)
- [Logging and Reporting](#logging-and-reporting)
- [Implementation](#More-Info-for-Implementation)
- [Troubleshooting](#troubleshooting)
- [FAQs](#faqs)

## Quick Start

Clone the repository:

```bash
git clone <repository-url>
cd <repository-folder>
```

Install dependencies:

```bash
mvn clean install
```

Update configurations:

- Modify `src/test/resources/Configuration.json` for local execution.
- Modify `src/test/resources/browserstack_example.yml` for BrowserStack execution.

Run tests locally:

```bash
mvn test
```

Run tests on BrowserStack:

```bash
mvn test -Dbrowserstack=true
```

## Prerequisites

Before you begin, ensure the following are installed:

- **Java Development Kit (JDK):** Version 11 or higher.
- **Maven:** For dependency management.
- **Appium:** Version 2.x or higher.
- **Android SDK:** (for Android testing).
- **Xcode:** (for iOS testing on macOS).
- **Node.js:** Required for Appium and BrowserStack Local.
- **BrowserStack Account:** (optional, for cross-platform testing).

## Framework Setup

Clone the repository:

```bash
git clone <repository-url>
```

Install dependencies:

```bash
mvn clean install
```

Set up Appium:

```bash
npm install -g appium
```

Set up BrowserStack (optional):

```bash
npm install -g browserstack-local
```

Update `browserstack_example.yml` with your BrowserStack credentials.

## Configuration

### Android Configuration

Update `src/test/resources/Configuration.json` with your Android app details:

```json
"androidConfiguration": {
  "appPackage": "com.example.app",
  "appActivity": "com.example.app.MainActivity",
  "adbExecuteTimeoutInMinutes": 5,
  "autoGrantPermissions": true
}
```

### iOS Configuration

Update `src/test/resources/Configuration.json` with your iOS app details:

```json
"iOSConfiguration": {
  "path": "/path/to/your/app.ipa",
  "bundleId": "com.example.app",
  "platformVersion": "17.0",
  "udid": "your-device-udid",
  "wdaConnectionTimeoutInMinutes": 10
}
```

### BrowserStack Configuration

Update `src/test/resources/browserstack_example.yml` with your BrowserStack credentials and desired devices:

```yaml
userName: "your-username"
accessKey: "your-access-key"
platforms:
  - deviceName: "Samsung Galaxy S24 Ultra"
    platformVersion: "14.0"
    platformName: "android"
```

## Running Tests

### Local Execution

```bash
mvn test
```

### BrowserStack Execution

```bash
mvn test -Dbrowserstack=true
```

## Test Data Management

Test data is stored in JSON files under `src/test/resources/TestData`.

Example:

```json
{
  "validuserstandard": {
    "username": "standard_user",
    "password": "secret_sauce"
  }
}
```

## Logging and Reporting

- **Logs:** Stored in the `logs` directory.
- **Reports:** Cucumber and Extent Reports generated in `target/cucumber`.

## More Info for Implementation

### Adding a New Screen

1. Add screen elements to `elements` package.
2. Implement required actions in `actions` package.
3. Add screen fragments/components in `screens` package.
4. Handle popups in `dismissible` package with `dismiss()` method.

### Running Automation Suite

Command Line Arguments:

- `-Denvironment`: `local`, `browserstack` (mandatory).
- `-Dplatform`: `android`, `ios` (mandatory).
- `-Dapp`: BrowserStack app URL.
- `-DtestRunId`: Optional TestRail Run Id.
- `-Dcucumber.filter.tags`: Feature or test type tags.

Example commands:

```bash
mvn clean test -Denvironment=local -Dplatform=android -Dcucumber.filter.tags="@Regression"
```

## Troubleshooting

### Appium Server Not Starting

```bash
lsof -i :4723
kill -9 <pid>
```

### BrowserStack Connection Issues

```bash
browserstack-local --key <your-access-key>
```

### Element Not Found

- Check locators.
- Increase timeout in `driverConfiguration`.

## FAQs

- **How to add a new test scenario?** Add a feature file and implement steps.
- **How to run tests in parallel?** Set `parallelsPerPlatform` in `browserstack_example.yml`.
- **Where are screenshots saved?** In the `ScreenShots` directory.