package insider.pages;

import insider.utils.BrowserUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QualityAssurancePage extends BasePage {

	public QualityAssurancePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = ".btn.btn-outline-secondary.rounded.text-medium.mt-2.py-3.px-lg-5.w-100.w-md-50")
	private WebElement seeAllQaJobsButton;

	public void clickOnSeeAllQaJobsButton() {
		BrowserUtils.moveToElement(seeAllQaJobsButton);
		click(seeAllQaJobsButton);
	}

}
