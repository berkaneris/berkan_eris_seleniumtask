package insider.pages;

import insider.utils.BrowserUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CareersPage extends BasePage {

	public CareersPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(css = ".big-title.big-title-media.mt-4.mt-lg-0")
	private WebElement headerText;

	@FindBy(css = "div[class='col-12 mb-xl-5 py-xl-4 py-2 text-center'] h3[class='category-title-media']")
	private WebElement teamsSectionHeader;

	@FindBy(css = ".col-12.d-flex.flex-wrap.p-0.career-load-more")
	private WebElement teamsItemsSection;

	@FindBy(css = ".btn.btn-outline-secondary.rounded.text-medium.mt-5.mx-auto.py-3.loadmore")
	private WebElement seeAllTeamsButton;

	@FindBy(css = ".category-title-media.ml-0")
	private WebElement ourLocationsHeader;

	@FindBy(css = ".glide__slides")
	private WebElement ourLocationsItemsSection;

	@FindBy(css = "div[class='elementor-element elementor-element-21cea83 elementor-widget elementor-widget-heading'] h2[class='elementor-heading-title elementor-size-default']")
	private WebElement lifeAtInsiderHeaderText;

	@FindBy(css = "div[class='elementor-element elementor-element-c06d1ec elementor-skin-carousel elementor-widget elementor-widget-media-carousel e-widget-swiper'] div[class='elementor-widget-container']")
	private WebElement lifeAtInsiderItemsSection;

	public boolean isHeaderTextDisplayed() {
		return isDisplayed(headerText);
	}

	public boolean isTeamsSectionHeaderDisplayed() {
		BrowserUtils.scrollToElement(teamsSectionHeader);
		return isDisplayed(headerText);
	}

	public boolean isTeamsItemsPartDisplayed() {
		BrowserUtils.scrollToElement(teamsItemsSection);
		return isDisplayed(teamsItemsSection);
	}

	public boolean isSeeAllTeamsButtonDisplayed() {
		BrowserUtils.scrollToElement(seeAllTeamsButton);
		return isDisplayed(seeAllTeamsButton);
	}

	public boolean isOurLocationsHeaderDisplayed() {
		BrowserUtils.scrollToElement(ourLocationsHeader);
		return isDisplayed(ourLocationsHeader);
	}

	public boolean isOurLocationsItemsPartDisplayed() {
		BrowserUtils.scrollToElement(ourLocationsItemsSection);
		return isDisplayed(ourLocationsItemsSection);
	}

	public boolean isLifeAtInsiderHeaderDisplayed() {
		BrowserUtils.scrollToElement(lifeAtInsiderHeaderText);
		return isDisplayed(lifeAtInsiderHeaderText);
	}

	public boolean isLifeAtInsiderSectionDisplayed() {
		BrowserUtils.scrollToElement(lifeAtInsiderItemsSection);
		return isDisplayed(lifeAtInsiderItemsSection);
	}

	public void clickOnSeeAllTeamsButton() {
		BrowserUtils.moveToElement(seeAllTeamsButton);
		click(seeAllTeamsButton);
	}

}
