package insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class InsiderHomePage extends BasePage {

	public InsiderHomePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = "#wt-cli-reject-btn")
	private WebElement declineCookieButton;

	@FindBy(css = "img[alt='insider_logo']")
	private WebElement insiderLogo;

	@FindBy(css = "ul[class='navbar-nav']")
	private WebElement navBar;

	@FindBy(css = ".dropdown-menu.new-menu-dropdown-layout-6.show > div > a")
	private List<WebElement> companyDropdownMenuItems;

	public void clickOnDeclineCookieButton() {
		if (isDisplayed(declineCookieButton)) {
			click(declineCookieButton);
		}
	}

	public boolean isInsiderLogoDisplayed() {
		return isDisplayed(insiderLogo);
	}

	public void clickOnNavBarItem(String itemText) {
		List<WebElement> navItem = navBar.findElements(By.cssSelector(" li > a"));
		for (WebElement item : navItem) {
			if (item.getText().contains(itemText)) {
				click(item);
				return;
			}
		}
	}

	public void clickOnCompanyDropdownMenuItem(String itemText) {
		for (WebElement item : companyDropdownMenuItems) {
			if (item.getText().contains(itemText)) {
				click(item);
				return;
			}
		}
	}

}
