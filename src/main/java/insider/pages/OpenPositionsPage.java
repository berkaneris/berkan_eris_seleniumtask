package insider.pages;

import insider.utils.BrowserUtils;
import insider.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OpenPositionsPage extends BasePage {

	public OpenPositionsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "#select2-filter-by-location-container")
	private WebElement filterByLocationDropdown;

	@FindBy(css = "#select2-filter-by-location-results > li")
	private List<WebElement> filterByLocationOptions;

	@FindBy(css = "#select2-filter-by-department-container")
	private WebElement filterByDepartmentDropdown;

	@FindBy(css = "#select2-filter-by-department-results > li")
	private List<WebElement> filterByDepartmentOptions;

	@FindBy(css = "#jobs-list")
	private WebElement jobsListElement;

	public void selectFilterByLocation(String location) {
		BrowserUtils.scrollToElement(filterByLocationDropdown);
		click(filterByLocationDropdown);
		for (WebElement option : filterByLocationOptions) {
			if (option.getText().equalsIgnoreCase(location)) {
				BrowserUtils.moveToElement(option);
				click(option);
				return;
			}
		}
	}

	public void selectFilterByDepartment(String department) {
		BrowserUtils.scrollToElement(filterByDepartmentDropdown);
		click(filterByDepartmentDropdown);
		for (WebElement option : filterByDepartmentOptions) {
			if (option.getText().equalsIgnoreCase(department)) {
				BrowserUtils.moveToElement(option);
				click(option);
				return;
			}
		}
	}

	public boolean isJobListDisplayed() {
		BrowserUtils.scrollToElement(jobsListElement);
		return isDisplayed(jobsListElement);
	}

	public void waitForUntilDepartmentElementTextVisible(String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					String actualText = filterByDepartmentDropdown.getText();
					return actualText.contains(text);
				}
				catch (Exception e) {
					return false;
				}
			}
		});
	}

	public void waitForUntilJobListFilteredByDepartmentAndLocation() {
		BrowserUtils.wait(5);
	}

	public boolean positionNamesContainsQualityAssurance() {
		BrowserUtils.scrollToElement(jobsListElement);
		List<WebElement> positionNames = jobsListElement.findElements(By.cssSelector(" div > div > p"));
		for (WebElement position : positionNames) {
			if (!(position.getText().contains("Quality Assurance") || position.getText().contains("QA"))) {
				return false;
			}
		}
		return true;
	}

	public boolean positionDepartmentsContainsQualityAssurance() {
		List<WebElement> positionDepartments = jobsListElement.findElements(By.cssSelector(" div > div > span"));
		for (WebElement position : positionDepartments) {
			if (!position.getText().contains("Quality Assurance")) {
				return false;
			}
		}
		return true;
	}

	public boolean positionLocationsContainsIstanbulTurkey() {
		List<WebElement> positionLocations = jobsListElement.findElements(By.cssSelector(" div > div > div"));
		for (WebElement position : positionLocations) {
			if (!position.getText().contains("Istanbul, Turkiye")) {
				return false;
			}
		}
		return true;
	}

	public void clickOnViewRoleButton(int index) {
		List<WebElement> jobs = jobsListElement.findElements(By.xpath("child::div"));
		WebElement jobElement = jobs.get(index - 1);
		WebElement viewRoleButton = jobElement.findElement(By.cssSelector(" div > a"));
		BrowserUtils.moveToElement(jobElement);
		click(viewRoleButton);
	}

	public String getJobPositionName(int index) {
		List<WebElement> jobs = jobsListElement.findElements(By.xpath("child::div"));
		WebElement jobElement = jobs.get(index - 1);
		return jobElement.findElement(By.cssSelector(" div > p")).getText();
	}

	public void navigateToNewOpenedTab() {
		new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10))
			.until(driver -> driver.getWindowHandles().size() > 1);
		BrowserUtils.navigateToNewOpenedWindow();
	}

}
