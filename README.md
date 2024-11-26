# API Testing Studies with Rest-Assured & JUnit

- This project demonstrates how to perform API testing using Java, Maven, and the Rest-Assured library.
- It includes test cases for common HTTP methods (GET, POST, PUT, DELETE) to validate API functionality. Additionally, it uses JUnit 5 for test management and Jackson Databind for serialization and deserialization of JSON objects.
- Tests were developed during the course https://www.linkedin.com/learning/java-automated-api-testing-with-rest-assured/gain-fast-feedback-with-automated-api-testing

## Project Overview

- **Technologies used**:

  - Java 15
  - Maven
  - Rest-Assured (for API testing)
  - JUnit 5 (for test management)
  - Jackson Databind (for JSON serialization/deserialization)

- **Functionalities tested**:
  - Retrieving categories and products
  - Validating response headers and body
  - Creating, updating, and deleting products
  - Using JSON objects and serialized Java objects for API requests and responses

## Requirements

1. **Java**: Ensure Java 15 or later is installed
2. **Maven**: Install Maven for dependency management
3. **MAMP**: Install and configure MAMP to serve the API

## Setup Instructions

### Step 1: Clone the Repository

```bash
git clone <your-repository-url>
cd <your-repository-folder>
```

### Step 2: Configure MAMP

- Move the api_testing folder to the htdocs directory of MAMP.

```bash
mv api_testing /Applications/MAMP/htdocs/
```

- Adjust the path if your MAMP directory is different.
- Start MAMP and ensure the web server is running.

### Step 3: Install Dependencies

Run the following Maven command to install the required dependencies:

```bash
mvn clean install
```

## Project structure

```bash
src/
├── main/
│   ├── java/
│   │   └── models/
│   │       └── Product.java    # Model class for product entity
├── test/
│   ├── java/
│   │   └── trainingxyz/
│   │       └── ApiTests.java   # Test cases for API
pom.xml                        # Maven configuration file
```

## Test Cases

GET Requests:

- Retrieve categories.
- Retrieve a single product with specific details.
- Validate a complex response for products.

POST Requests:

- Create a new product using raw JSON.
- Create a new product using a serialized Java object.

- PUT Requests:

- Update a product partially (e.g., only price).
- Update a product with full details.

DELETE Requests:

- Delete a specific product by ID.

Headers Validation:

- Ensure the Content-Type header is correct.

Serialization/Deserialization:

- Serialize a Java object to JSON for API requests.
- Deserialize API responses back into Java objects and compare with expected values.

### Dependencies

The following dependencies are included in the pom.xml:

- Rest-Assured: For API testing.
- JUnit 5: For test cases.
- Jackson Databind: For JSON serialization and deserialization.

### Notes

- The API being tested must be running locally and accessible at http://localhost:8888/api_testing.
- Adjust the test endpoints in ApiTests.java if the API is hosted on a different base URL or port.
- All test cases are written under the _trainingxyz_ package.
