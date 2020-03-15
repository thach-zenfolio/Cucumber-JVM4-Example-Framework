# Sample Cucumber Automation Project
This document can be found at :  TBD

## I. FW Overview
- Ability to execute the tests in Test, Stage and Production Environment.  
- Ability to execute the tests in parallel with Scenario level.  
- Ability to execute the tests on different browsers.  
- Ability to execute all or selective tests (based on cucumber tag) through the command line.  
- Ability to share the data between the test steps.

### Test Data:
TBD

### The test automation framework is comprised of following tools and libraries
- Cucumber-JVM + TestNG: BDD Framework  
- Step definitions with lambda expressions (cucumber-java8)  
- Page Object Pattern  
- Selenium: Browser automation tool   
- JAVA: Programming language  
- Maven: Build and dependencies tool  
- Spring: Dependency Injection  
- Git-Github: Version Control, Git repository hosted server  
- Intellij: Integrated Development Environment
- AssertJ: Matcher's
- cluecumber-report-plugin: Reporting
- SLF4J Simple: Logger

## II. How to run Test
- **Run by maven command line:** 
  - run all regression tests on **Stage**: 
    > mvn clean verify  
  - run all smoke tests on **Production** :
    > mvn clean -D cucumber.options='--tags @production --tags @smoke-test' -D testEvn=production verify
  - run test using **specified browser**: 
    > mvn clean -Dbrowser="firefox" verify (default is Chrome)  
  - Run tests in **parallel** and generate report:
    > mvn clean verify -Ddataproviderthreadcount=3  
  - **Rerun all failed tests**: Changing the feature in @CucumberOptions to "features = {"@target/failed_scenarios.txt"}" then run:
    > mvn verify  (make sure **not** running with **clean** argument).
  - Run tests and **take screenshot** on failures and attach screenshots to report:
    > mvn -DSCREENSHOT_ON_FAILURE="true" verify
- **Run by Test runner in IDE**