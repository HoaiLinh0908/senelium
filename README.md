# Senelium 👨‍🔬
### Selenium framework for UI web application testing.

## Introduction

This project is a Selenium framework with the following implementations:
- Auto-wait for element actions.
- Auto-retry for element assertions.
- Handle parallel execution.
- Cross-browser: support Chrome, Firefox, and Edge.
- Screenshot on failed tests and support Allure report.


## Prerequisites 🛠️
- Java 11
- Maven
- Selenium WebDriver 4

## Write tests 📝 
```java
//Init driver configuration
DriverConfig driverConfig = DriverConfig.getInstance();
Senelium.createDriver(driverConfig);

Senelium.open("url");

Element button = Element.byCssSelector("button");

//Auto wait until the button is Clickable
button.click();

Element message = Element.byId("#message");

//Auto retry until the element is visible or timeout
SeAssert.expect(message).toBeVisible();
```
