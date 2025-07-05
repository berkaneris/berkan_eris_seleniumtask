# Insider UI Test Automation Framework

![Java](https://img.shields.io/badge/Java-17-blue.svg) ![Maven](https://img.shields.io/badge/Maven-3.9+-orange.svg) ![TestNG](https://img.shields.io/badge/TestNG-7.x-green.svg) ![Selenium](https://img.shields.io/badge/Selenium-4.x-brightgreen.svg)

A robust, maintainable and **parallel-ready** UI test automation framework built with Java 17, Selenium WebDriver 4 and TestNG.  It targets the *Insider* web application but can be quickly adapted to any modern web project.

---

## ✨ Key Features

| Area | What & Why |
|------|------------|
| **Modern Tech-Stack** | Java 17, Maven, Selenium 4, TestNG 7, AssertJ, Log4j 2, Lombok |
| **Reporting** | Beautiful, interactive HTML reports via **ExtentReports 5** and console logs via Log4j 2 |
| **Page Object Model** | Clean separation between *page logic* and *test logic* for maximum readability & maintainability |
| **Parallel Execution** | TestNG + Maven Surefire run tests in parallel at *method* level (default 4 threads – configurable) |
| **Cross-Browser** | Run on Chrome, Firefox, Edge… just pass `-Dbrowser=<name>` (defaults to Chrome) |
| **CI/CD Ready** | Zero-config CLI commands make it ideal for Jenkins / GitHub Actions / Azure DevOps pipelines |

---

## 🛠️ Project Structure

```
berkan_eris_seleniumtask/
├─ src
│  ├─ main/java/insider/pages/     # Page Objects & component models
│  └─ test/java/insider/tests/     # Test classes (extend BaseTest)
│      └─ listeners/               # TestNG & Extent listeners
│
├─ src/test/resources/
│  ├─ suites/insider-ui-tests.xml  # Master TestNG suite file
│  └─ spark-config.xml             # ExtentReports theme/config
│
├─ pom.xml                         # Maven dependencies & plugins
└─ README.md                       # You are here 👋
```

> **Tip:** Use your IDE’s *Maven* view to explore lifecycle phases and individual modules.

---

## ⚙️ Technologies & Libraries

| Tool | Purpose |
|------|---------|
| **Maven** | Build lifecycle, dependency management, parallel execution via *Surefire* |
| **Selenium WebDriver 4** | Browser automation engine |
| **TestNG** | Test runner, data-providers, grouping & parallelism |
| **AssertJ** | Fluent, readable assertions (better than JUnit/TestNG native) |
| **ExtentReports 5** | Eye-catching HTML + JSON reports & historical trend graphs |
| **Log4j 2** | Granular logging to console and files |
| **Jackson / Gson** | JSON parsing (test data, API stubs) |
| **Lombok** | Eliminates boilerplate (getters, builders, logs…) |

All versions are locked in `pom.xml` for repeatable builds.

---

## 🚀 Getting Started

### Prerequisites

1. **JDK 17** or higher (`java -version`)
2. **Maven 3.9+** (`mvn -version`)
3. Chrome / Firefox / Edge browsers installed locally (or point WebDriver to remote grid/Selenium Hub).

### Clone & Import

```bash
# SSH or HTTPS – your choice
git clone git@github.com:<your-org>/berkan_eris_seleniumtask.git
cd berkan_eris_seleniumtask
```

Import as Maven project in IntelliJ IDEA / Eclipse – dependencies will resolve automatically.

---

## 🏃 Running Tests

The framework is configured through Maven **system properties**.  The most common is `browser`.

```bash
# Default (Chrome)
mvn clean test

# Firefox
mvn clean test -Dbrowser=firefox

# Edge
mvn clean test -Dbrowser=edge

# Headless Chrome (example of custom capability)
mvn clean test -Dbrowser=chromeHeadless
```

Behind the scenes:

1. `pom.xml` passes the `browser` property to the **Surefire** plugin.
2. `BaseTest` receives it via `@Parameters("browser")`.
3. `DriverManager.createDriver(browser)` spins up the corresponding WebDriver instance.

If an unknown value is supplied, the framework logs an error and falls back to Chrome.

### Selecting Test Suites

By default the suite `src/test/resources/suites/insider-ui-tests.xml` runs.  Override it with:

```bash
mvn test -DsuiteXmlFile=src/test/resources/suites/login-smoke.xml
```

### Controlling Parallelism

Update the following **Surefire** properties in `pom.xml` or override from CLI:

```bash
mvn test -DthreadCount=8 -Dparallel=methods
```

---

## 📊 Reports & Logs

After execution all artefacts are placed under `test-output/`:

* `ExtentReport.html` – interactive dashboard (pass/fail trends, screenshots, logs)
* `logs/` – Log4j rolling-file appender with full debug traces

Open the HTML report in any browser and drill into each step.

---

## 🧩 Extending the Framework

1. **Add a page**: create a new class in `insider.pages` extending `BasePage`.
2. **Add a test**: create a class in `insider.tests` extending `BaseTest` and annotate methods with `@Test`.
3. **Custom utilities** sit under `insider.utils` (e.g., DB helpers, API clients).
4. **Hook into TestNG events** via your own listener implementing `ITestListener` and register it in `testng.xml`.

---

## 🤖 Continuous Integration

The Maven CLI commands make CI trivial:

```bash
mvn clean test -Dbrowser=chromeHeadless -Dsurefire.rerunFailingTestsCount=2
```

Integrate the above into **GitHub Actions**, **Jenkins**, **Azure DevOps** or any CI runner.  Store the generated HTML report as an artefact for each job.

---

## 📚 Useful Commands Cheat-Sheet

| Command | What it does |
|---------|--------------|
| `mvn clean test -Dbrowser=chrome` | Run full suite on Chrome |
| `mvn test -DsuiteXmlFile=login.xml` | Run a specific TestNG suite |
| `mvn test -DskipTests` | Compile only, skip tests |
| `mvn surefire-report:report-only` | Regenerate Surefire HTML reports |

---

## 💡 Contributing

Pull requests are welcome!  Please follow the existing code style (Spring Java Format enforced by the `spring-javaformat-maven-plugin`) and make sure `mvn clean verify` passes before opening a PR.

