package insider.tests;

import insider.utils.DriverManager;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

@Log4j2
public abstract class BaseTest {

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void setUp(@Optional("chrome") String browser, Method method) {
		log.info("Setting up the test with browser: {}", browser);
		DriverManager.createDriver(browser);
	}

	@AfterMethod
	public void tearDown() {
		DriverManager.closeDriver();
	}

}
