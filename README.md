**The UI Automation for OrangeHRM Demo was implemented using the following approach and architecture**

Platform: OrangeHRM Demo (https://opensource-demo.orangehrmlive.com/)

OrangeHRM is a widely used, stable HR management demo app. It features a robust login flow, role-based access, and critical transaction-like operations (creating and managing employees/admin users) that are ideal for end-to-end functional and security testing.

This framework automates the **OrangeHRM Open Source Demo** application using:
- **Java + Selenium WebDriver**
- **TestNG** for test orchestration
- **ExtentReports** for HTML reporting
- **Log4j** for logging
- **POM (Page Object Model)** design pattern
- **External JSON Object Repository** for locators
- **Config-driven execution** (browser, headless mode, etc.)

**UI Automation Flow**-----------------------------------------------------------------------

The following customer journey was automated:

1. Login: Navigate to the OrangeHRM login page and authenticate as Admin.

2. User Creation: Add a new Admin user via the Admin module, using POM for maintainability.

3. Validation: Assert that the user appears in the User Management grid (UI check).

4. Logout: Sign out and verify logout success (presence of login page).

5. Screenshots are captured on failure, and explicit waits ensure robust element handling.


**Project Structure**----------------------------------------------------------------------------

src/
├─ main/java/framework/

│  ├─ api/                # Driver and webelement activities

│  ├─ extentReport/       # ExtentReports setup (ExtentManager, ExtentConfig, TestReporter)

│  ├─ launcher/           # LaunchDriver for browser setup

│  ├─ listener/           # TestNG listener (screenshots, reporting)

│  ├─ or/                 # Object Repository loader and injector classes 

│  ├─ BasePage.java       # Common page initialization actions

│  ├─ BaseTest.java       # Common test setup/teardown

│  └─ utility/            # ConfigManager, Constants, LogUtil

├─ main/resources/

│  ├─ config.properties   # Browser, URL

│  └─ log4j.properties    # Logging configuration

├─ test/java/

│  ├─ page/               # Page Objects (LoginPage, AdminPage, DashboardPage, Navigation)

│  └─ script/             # Test classes (LoginTest, CreateUserTest)

├─ test/resources/

│  └─ objectRepository/   # JSON locator files (LoginPage.json, AdminPage.json, ...)

logs/                     # Execution logs

pom.xml                   # Maven dependencies & Surefire (suite binding)


**Prerequisites**--------------------------------------------------------------------------------
- Java 11+ (project supports Java 21)
- Maven 
- Chrome / Edge installed
- Internet access (for WebDriverManager to resolve drivers)


**Configuration**---------------------------------------------------------------------------------

Update `src/main/resources/config.properties`:
```properties
browser=chrome        # Options: chrome, edge
headless=false        # true = headless, false = headed
baseUrl=https://opensource-demo.orangehrmlive.com/
username=Admin
password=admin123
```

**How to Run**
### From IDE
- Open `testng.xml` → **Run as TestNG Suite**
- Or run individual classes under `src/test/java/script/`

### From CLI (Maven)
**Default run (uses `testng.xml`)**
```bash
mvn clean test
```

**Headless mode**
```bash
mvn clean test -Dheadless=true
```

**Headed mode**
```bash
mvn clean test -Dheadless=false
```


**Features Implemented**------------------------------------------------------------------------

1. Tools: Selenium, TestNG, log4j, ExtentReports

2. POM Design: Page Object Model separates concerns for maintainable, scalable code.

3. Selectors: All locators are maintained in an external JSON object repository file.

4. Config: User data and credentials parameterized for easy updates from config.properties.

5. Explicit Waits: Only explicit waits are used for stable execution.

6. Failure Handling: Screenshots are auto-captured for failed steps.

6. Reporting: Tests generate HTML reports summarizing execution and detailed logs.

6. Execution: Test suite is fully CLI-executable

7. Bonus: Test run supports headless mode and cross-browser execution; set via config or CLI.



