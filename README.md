# README for Reqres API Automation Testing

## Overview
This project demonstrates automated API testing using the `RestAssured` library to interact with the Reqres API. The tests include creating, updating, and retrieving a user while integrating with Allure for reporting.

### Features:
- **Create User:** Sends a POST request to create a user and verifies the response.
- **Update User:** Updates the previously created user via a PUT request.
- **Retrieve User:** Attempts to retrieve the updated user, expecting a 404 response.

---

## Prerequisites
To run this project, ensure the following tools and dependencies are installed:

1. **Java Development Kit (JDK)**: Version 8 or above.
2. **Maven**: Ensure Maven is installed and added to your PATH.
3. **Allure Framework**: Install Allure for generating and viewing reports.
4. **IDE**: Any IDE like IntelliJ IDEA, Eclipse, or VS Code.

---

## Project Structure
- **`src/test/java/Foodics/test/reqres.java`**: Contains the test cases for the API interactions.
- **`utils/JsonUtils`**: A utility to read and parse JSON files used for request payloads.
- **`src/test/resources/createUser.json`**: Payload for creating a user.
- **`src/test/resources/updateUser.json`**: Payload for updating a user.

---

## How to Run the Tests

### Steps:
1. Clone this repository to your local machine.
2. Navigate to the project directory in your terminal.
3. Run the tests using Maven:
   ```sh
   mvn test
   ```

### Notes:
- Ensure the `createUser.json` and `updateUser.json` files are available in the `src/test/resources` folder.
- The test results will be stored in the `target/allure-results` folder after execution.

---

## Allure Reporting
### Generating the Allure Report:
1. After running the tests, generate the report by executing:
   ```sh
   allure serve target/allure-results
   ```
   This will:
   - Process the results from `target/allure-results`.
   - Open the Allure report in your default web browser.

2. If you encounter issues, ensure Allure is correctly installed by running:
   ```sh
   allure --version
   ```

---

## Test Flow Explanation
### 1. Create a User
- Sends a POST request to `https://reqres.in/api/users`.
- Payload is read from `createUser.json`.
- Verifies the response status code is `201`.
- Extracts and stores the `userID` for subsequent tests.

### 2. Update the User
- Sends a PUT request to `https://reqres.in/api/users/{userID}`.
- Payload is read from `updateUser.json`.
- Verifies the status code is `200` and checks that the name and job in the response match the expected values.

### 3. Retrieve the User
- Sends a GET request to `https://reqres.in/api/users/{userID}`.
- Verifies the response status code is `404` and the status line matches `HTTP/1.1 404 Not Found`.

