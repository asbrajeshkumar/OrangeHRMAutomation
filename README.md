The UI Automation for OrangeHRM Demo was implemented using the following approach and architecture, meeting all assignment requirements:



Platform: OrangeHRM Demo (https://opensource-demo.orangehrmlive.com/)



Reasoning: OrangeHRM is a widely used, stable HR management demo app. It features a robust login flow, role-based access, and critical transaction-like operations (creating and managing employees/admin users) that are ideal for end-to-end functional and security testing.





UI Automation Flow

The following customer journey was automated:

Login: Navigate to the OrangeHRM login page and authenticate as Admin.

User Creation: Add a new Admin user via the Admin module, using POM for maintainability.

Validation: Assert that the user appears in the User Management grid (UI check).

Logout: Sign out and verify logout success (presence of login page).

Screenshots are captured on failure, and explicit waits ensure robust element handling.



Features Implemented

Tools: Selenium, TestNG, log4j, ExtentReports

POM Design: Page Object Model separates concerns for maintainable, scalable code.

Selectors: All locators are maintained in an external JSON object repository file.

Config: User data and credentials parameterized for easy updates from config.properties.

Explicit Waits: Only explicit waits are used for stable execution.

Failure Handling: Screenshots are auto-captured for failed steps.

Reporting: Tests generate HTML reports summarizing execution.

Execution: Test suite is fully CLI-executable

Bonus: Test run supports headless mode and cross-browser execution; set via config or CLI.



