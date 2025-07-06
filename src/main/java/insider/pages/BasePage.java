package insider.pages;

import insider.utils.BrowserUtils;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Log4j2
public abstract class BasePage {

	protected WebDriver driver;

	private static final int TIMEOUT = 10;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	protected WebElement waitForVisibility(WebElement element) {
		new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(ExpectedConditions.visibilityOf(element));
		return element;
	}

	protected void click(WebElement element) {
		waitForVisibility(element).click();
	}

	protected void sendKeys(WebElement element, String text) {
		waitForVisibility(element).sendKeys(text);
	}

	protected boolean isDisplayed(WebElement element) {
		try {
			return waitForVisibility(element).isDisplayed();
		}
		catch (Exception e) {
			return false;
		}
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

}