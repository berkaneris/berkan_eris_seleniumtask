package insider.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JobsLeverPage extends BasePage {

	public JobsLeverPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "div[class='posting-headline'] h2")
	private WebElement jobPositionName;

	@FindBy(css = "div[class='postings-btn-wrapper'] a[class='postings-btn template-btn-submit shamrock']")
	private WebElement applyForThisJobButton;

	public String getJobPositionName() {
		return jobPositionName.getText();
	}

	public boolean isApplyForThisJobButtonDisplayed() {
		return isDisplayed(applyForThisJobButton);
	}

}
