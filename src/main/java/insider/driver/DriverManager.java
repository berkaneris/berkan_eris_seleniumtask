package insider.driver;

import insider.config.ConfigurationManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

@Log4j2
public class DriverManager {

	private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

	private DriverManager() {
		throw new UnsupportedOperationException("Cannot instantiate utility class");
	}

	public synchronized static WebDriver getDriver() {
		return DRIVER_THREAD_LOCAL.get();
	}

	public synchronized static WebDriver createDriver(String browserType) {
		if (DRIVER_THREAD_LOCAL.get() == null) {
			WebDriver driver;
			driver = switch (browserType.toLowerCase()) {
				case "firefox" -> new FirefoxDriver();
				case "edge" -> new EdgeDriver();
				default -> setupChromeDriver();
			};

			driver.manage().window().maximize();
			driver.get(ConfigurationManager.getProperty("baseURL"));
			DRIVER_THREAD_LOCAL.set(driver);
		}
		return DRIVER_THREAD_LOCAL.get();
	}

	public static void closeDriver() {
		if (DRIVER_THREAD_LOCAL.get() != null) {
			DRIVER_THREAD_LOCAL.get().quit();
			DRIVER_THREAD_LOCAL.remove();
		}
	}

	private static ChromeDriver setupChromeDriver() {
		ChromeOptions options = new ChromeOptions();

		Map<String, Object> prefs = new HashMap<>();
		prefs.put("profile.default_content_setting_values.notifications", 2);

		options.setExperimentalOption("prefs", prefs);
		return new ChromeDriver(options);
	}

}