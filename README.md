# AssureTest

Assure Test is Rest-Assured based Acceptance Test For Rest API .

## Description

AssureTest is a collection of three acceptance test verifying the Rest API Response. The Language used here is Java and for provisioning all API related functionality Rest-Assured framework has been utilized.

The unit test framework used here is TestNG, used the TestNG dataprovider concept for handling test data. Test Data can be found in Resources Resources\TestData folder. Excel file has been utilized for containing the test data.

The project is build using Maven. For reporting ExtentReport is used utilizing the Listener concept of TestNG.

All Reports Including the Extent Report can be found at

```bash
target\surefire-reports
```

## Usage Running Test

```maven

mvn clean test
```
# pet
