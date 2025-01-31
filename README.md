# Mobile Cucumber Test Automation Framework

## Table of Contents
- [Features](#features)
- [Installation](#installation)
- [Frameworks Included](#frameworks-included)
- [How To Work With It](#How-To-Work-With-It)
- [Project Structure](#project-structure)
- [How To Run](#how-to-run)

## Features
- Support BDD
- Support Running Testing on Mobile Devices
- Support Cross-Platform Testing (Android & iOS)
- Support Running on BrowserStack
- Generate Allure Report automatically after Test Execution with screenshots
- Support being Dockerized
- Support Logs using Logback
- Support running on Jenkins Pipelines

## Installation
- Java(JDK) 23
- IntelliJ IDEA / Eclipse
- Maven
- Allure
- GIT
- LogExpert

## Frameworks Included
- Page Object Model (POM) design pattern
- Factory Design Pattern
- SOLID Principles

## How To Work With It
### Adding new screen:

1. In `src/test/java/Screens` package:
    1. Add screen elements to `elements` package as `List<Locator>`, each `Locator` object represents a method of
       locating an element.
    2. Add required actions to `actions` package, those should be reusable and uncoupled.
    3. If the screen is just a screen fragment/component add both `elements` and `actions` to the same file and add it
       to the `screens` package root.

2. In `screens.elements` package:
    1. Elements class should be named `*Elements` e.g. `SignInElements`.
    2. Locators could be assigned as a Property with the signature of `public List<Locator> element = List.of();`, which
       would produce a static list.
    3. Locators could be assigned as a Function with the signature of
       `public List<Locator> element(Object object){ return List.of() }`, so you can pass any object like `String` or
       `Integer` to use it with dynamic locators.
    4. Locator object has `enum` of `Strategy` which is platform oriented, proper strategy has to be picked up.

3. In `screens.actions` package:
    1. Actions class should be named `*Screen`, `*Player` or `*Layover` e.g. `SignInScreen`.
    2. It should handle any action that would be passed to `definitions` to be used as is, it should contain the logic.

### Adding new cases

1. In `features` folder under `resources`:
    1. Create a `.feature` file for each feature that would be tested.
    2. In this file add test cases flow using already-existing/new steps.
    3. Add test name as per TestRail, add testcase ids as tags, testcase id from TestRail starts with `C` e.g. `C123456`
       it should be `@C123456` as a testcase tag.
    4. Add feature name per TestRail folder name as a tag e.g. `@SignIn`
    5. Add testing type e.g. `@Regression` or `@Sanity`
2. In `definitions` package:
    1. Add the corresponding new steps that need implementation.

## Project Structure
### `src/test/java`
- **configurations/** → Contains base classes for setting up configurations for driver and platform configurations.
- **context/** → Manages test execution context, including data sharing between steps and maintaining session information.
- **Data/** → Stores user-related test data.
- **Driver/** → Manages WebDriver initialization, configuration, and handling across test cases.
- **Runner/** → Contains test runners for executing test suites using Cucumber.
- **Screens/** → Implements the Page Object Model (POM) by defining screen elements and actions for mobile UI interactions.
- **StepDefinition/** → Implements Cucumber step definitions, linking Gherkin steps to actual test execution logic.
- **Utils/** → Provides helper classes for logging, reporting, and common utility functions.

### `src/test/resources`
- **features/** → Contains feature files written in Gherkin syntax for behavior-driven testing.
- **TestData/** → Stores environment-specific test data and configuration files for parameterized test execution.

### `pom.xml`
- Manages dependencies for Appium, Cucumber, TestNG, Logback, Browserstack, and Allure.

### `logback.xml`
- Defines logging configurations.

### `Configuration_example.json`
- Holds environment-specific configurations such as general driver configs, android, and iOS specific configs.

## How To Run
### Run Locally


### Run on BrowserStack
