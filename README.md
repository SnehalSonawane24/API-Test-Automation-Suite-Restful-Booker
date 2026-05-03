# Restful Booker — API Test Automation Suite

A end-to-end API test automation framework built with **RestAssured**, **Java**, **TestNG**, and **Maven**, integrated with **Jenkins** for CI/CD. This framework validates all RESTful endpoints of the [Restful Booker](https://restful-booker.herokuapp.com) hotel booking application.


### About the Project

This framework automates **25+ test cases** covering the complete CRUD lifecycle of the Restful Booker API:

- **Authentication** — generate and validate auth tokens
- **Create Booking** — POST with full payload validation
- **Get Booking** — GET single and all bookings
- **Update Booking** — full update via PUT
- **Partial Update** — partial update via PATCH
- **Delete Booking** — DELETE and verify 404
- **Data-Driven Tests** — multiple input sets via TestNG DataProvider

All tests validate HTTP status codes, response body values, JSON payload integrity, and response headers.



### Tech Stack
Java | Maven | RestAssured | TestNG | Jackson | Jenkins | GitHub



### Project Structure

```
api-automation/
│
├── src/
│   └── test/
│       └── java/
│           └── com/restfulbooker/api_automation/
│               │
│               ├── base/
│               │   └── BaseTest.java            # RestAssured global config, logging setup
│               │
│               ├── endpoints/
│               │   └── Endpoints.java           # All API URL constants
│               │
│               ├── payload/
│               │   └── BookingPayload.java      # Request body builders
│               │
│               ├── tests/
│               │   ├── AuthTest.java            # POST /auth — token generation
│               │   ├── CreateBookingTest.java   # POST /booking
│               │   ├── GetBookingTest.java      # GET /booking, GET /booking/{id}
│               │   ├── UpdateBookingTest.java   # PUT & PATCH /booking/{id}
│               │   ├── DeleteBookingTest.java   # DELETE /booking/{id}
│               │   └── DataDrivenBookingTest.java  # Data-driven POST /booking
│               │
│               └── utils/
│                   └── BookingDataProvider.java # TestNG DataProvider
│
├── logs/
│   └── api-log.txt                              # Auto-generated request/response logs
│
├── target/
│   └── surefire-reports/                        # Auto-generated HTML test reports
│       ├── index.html
│       └── emailable-report.html
│
├── testng.xml                                   # TestNG suite configuration
├── pom.xml                                      # Maven dependencies & build config
└── README.md
```


### How to Run Tests

**Run the full test suite via Maven:**
```bash
mvn clean test
```

**Run a specific test class:**
```bash
mvn clean test -Dtest=AuthTest
mvn clean test -Dtest=CreateBookingTest
```

**Run via TestNG XML directly in IntelliJ:**

Right-click `testng.xml` → Run




### Framework Architecture

```
BaseTest.java
│
│  Sets up RequestSpecification once (@BeforeSuite)
│  — Base URI, Content-Type, Request/Response logging
│
└── All test classes extend BaseTest
        │
        ├── Endpoints.java       → centralised URL constants
        ├── BookingPayload.java  → reusable JSON request bodies
        └── BookingDataProvider  → DataProvider feeds multiple datasets
```

**Test execution order (controlled by TestNG priority):**

```
Priority 1 → AuthTest            → stores token in static variable
Priority 2 → CreateBookingTest   → stores bookingId in static variable
Priority 3 → GetBookingTest      → reads bookingId
Priority 4 → UpdateBookingTest   → reads token + bookingId
Priority 5 → UpdateBookingTest   → reads token + bookingId (PATCH)
Priority 6 → DeleteBookingTest   → reads token + bookingId
Priority 7 → DeleteBookingTest   → verifies 404
Priority 8 → DataDrivenBookingTest → runs 4× with different data
```



### Test Reports

After running `mvn clean test`, TestNG automatically generates HTML reports at:

```
target/surefire-reports/index.html           ← full test report
target/surefire-reports/emailable-report.html ← summary report
```

All request and response details are also logged to:
```
logs/api-log.txt
```