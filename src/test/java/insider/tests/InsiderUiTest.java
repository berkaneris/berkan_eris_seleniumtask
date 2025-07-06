package insider.tests;

import insider.pages.*;
import insider.driver.DriverManager;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

@Listeners(insider.listeners.ExtentTestNGITestListener.class)
public class InsiderUiTest extends BaseTest {

	@Test
	public void testInsiderHomePage() {
		// Visit https://useinsider.com/ and check Insider home page is opened or not
		InsiderHomePage insiderHomePage = new InsiderHomePage(DriverManager.getDriver());
		insiderHomePage.clickOnDeclineCookieButton();
		assertTrue(insiderHomePage.isInsiderLogoDisplayed(), "Insider logo is not displayed on the home page.");

		/*
		 * Select the “Company” menu in the navigation bar, select “Careers” and check
		 * Careerpage, its Locations, Teams, and Life at Insider blocks are open or not
		 */
		insiderHomePage.clickOnNavBarItem("Company");
		insiderHomePage.clickOnCompanyDropdownMenuItem("Careers");
		CareersPage careersPage = new CareersPage(DriverManager.getDriver());
		assertTrue(careersPage.isHeaderTextDisplayed(), "Careers header text is not displayed on the careers page.");
		assertTrue(careersPage.isTeamsSectionHeaderDisplayed(),
				"Teams section header is not displayed on the careers page.");
		assertTrue(careersPage.isTeamsItemsPartDisplayed(), "Teams items part is not displayed on the careers page.");
		assertTrue(careersPage.isSeeAllTeamsButtonDisplayed(),
				"See All Teams button is not displayed on the careers page.");
		assertTrue(careersPage.isOurLocationsHeaderDisplayed(),
				"Our Locations header is not displayed on the careers page.");
		assertTrue(careersPage.isOurLocationsItemsPartDisplayed(),
				"Our Locations items part is not displayed on the careers page.");
		assertTrue(careersPage.isLifeAtInsiderHeaderDisplayed(),
				"Life at Insider header text is not displayed on the careers page.");
		assertTrue(careersPage.isLifeAtInsiderSectionDisplayed(),
				"Life at Insider items part is not displayed on the careers page.");

		/*
		 * Go to https://useinsider.com/careers/quality-assurance/, click “See all QA
		 * jobs”, filter jobs by Location: “Istanbul, Turkey”, and Department: “Quality
		 * Assurance”, check the presence of the job list
		 */
		DriverManager.getDriver().get("https://useinsider.com/careers/quality-assurance/");
		QualityAssurancePage qualityAssurancePage = new QualityAssurancePage(DriverManager.getDriver());
		qualityAssurancePage.clickOnSeeAllQaJobsButton();
		OpenPositionsPage openPositionsPage = new OpenPositionsPage(DriverManager.getDriver());
		openPositionsPage.waitForUntilDepartmentElementTextVisible("Quality Assurance");
		openPositionsPage.selectFilterByLocation("Istanbul, Turkiye");
		openPositionsPage.waitForUntilJobListFilteredByDepartmentAndLocation();
		assertTrue(openPositionsPage.isJobListDisplayed(), "Job list is not displayed on the open positions page.");

		/*
		 * Check that all jobs’ Position contains “Quality Assurance”, Department contains
		 * “Quality Assurance”, and Location contains “Istanbul, Turkey”
		 */
		assertTrue(openPositionsPage.positionNamesContainsQualityAssurance(),
				"Position names do not contain 'Quality Assurance' after filtering by department.");
		assertTrue(openPositionsPage.positionDepartmentsContainsQualityAssurance(),
				"Position departments do not contain 'Quality Assurance' after filtering by location.");
		assertTrue(openPositionsPage.positionLocationsContainsIstanbulTurkey(),
				"Position locations do not contain 'Istanbul, Turkiye' after filtering by location.");
		String positionName = openPositionsPage.getJobPositionName(1);

		/*
		 * Click the “View Role” button and check that this action redirects us to the
		 * Lever Application form page
		 */
		openPositionsPage.clickOnViewRoleButton(1);
		openPositionsPage.navigateToNewOpenedTab();
		JobsLeverPage jobsLeverPage = new JobsLeverPage(DriverManager.getDriver());

		assertTrue(jobsLeverPage.isApplyForThisJobButtonDisplayed(),
				"Apply for this job button is not displayed on the job details page.");
		assertEquals(jobsLeverPage.getJobPositionName(), positionName,
				"Job position name does not match on the open position page.");

		String currentUrl = jobsLeverPage.getCurrentUrl();
		assertTrue(currentUrl.contains("https://jobs.lever.co/useinsider"));

	}

}
