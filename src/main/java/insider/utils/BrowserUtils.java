package insider.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class BrowserUtils {

	/**
	 * Captures a screenshot of the current browser window and saves it with a unique
	 * name. Returns the relative path for ExtentReports.
	 * @param name the name of the screenshot
	 * @return the relative file path of the captured screenshot (for ExtentReports)
	 */
	public static String takeScreenshot(String name) {
		name = new Date().toString().replace(" ", "_").replace(":", "-") + "_" + name;
		String relativePath = "testReports/screenshots/" + name + ".png";
		String absolutePath = System.getProperty("user.dir") + "\\" + relativePath;
		TakesScreenshot screenshot = (TakesScreenshot) DriverManager.getDriver();
		String base64Format = screenshot.getScreenshotAs(OutputType.BASE64);
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File(absolutePath);
		try {
			FileUtils.copyFile(sourceFile, destination);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return base64Format;
	}

	/**
	 * Moves the mouse pointer to the specified web element.
	 * @param element the web element to move the mouse pointer to
	 */
	public static void moveToElement(WebElement element) {
		Actions actions = new Actions(DriverManager.getDriver());
		actions.moveToElement(element).build().perform();
	}

	/**
	 * Navigates to a browser window with the specified title.
	 * @param targetTitle the title of the target window
	 */
	public static void navigateToWindow(String targetTitle) {
		String currentWindow = DriverManager.getDriver().getWindowHandle();
		for (String handle : DriverManager.getDriver().getWindowHandles()) {
			DriverManager.getDriver().switchTo().window(handle);
			System.out.println("Current Window Title: " + DriverManager.getDriver().getTitle());
			if (!DriverManager.getDriver().getTitle().equals(targetTitle)) {
				return;
			}
		}
		DriverManager.getDriver().switchTo().window(currentWindow);
	}

	public static void navigateToNewOpenedWindow() {
		String currentWindow = DriverManager.getDriver().getWindowHandle();
		for (String handle : DriverManager.getDriver().getWindowHandles()) {
			if (!handle.equals(currentWindow)) {
				DriverManager.getDriver().switchTo().window(handle);
				return;
			}
		}
		DriverManager.getDriver().switchTo().window(currentWindow);
	}

	/**
	 * Switches to a grandchild window.
	 */
	public static void switchToGrandChildWindow() {
		Set<String> windows = DriverManager.getDriver().getWindowHandles();
		Iterator<String> iterations = windows.iterator();
		String parentWindow = iterations.next();
		String childWindow = iterations.next();
		String grandChildwindow = iterations.next();
		DriverManager.getDriver().switchTo().window(grandChildwindow);
	}

	/**
	 * Switches to a popup window.
	 */
	public static void switchToPopUpWindow() {
		Set<String> windows = DriverManager.getDriver().getWindowHandles();
		Iterator<String> iterations = windows.iterator();
		String parentWindow = iterations.next();
		String childWindow = iterations.next();
		DriverManager.getDriver().switchTo().window(childWindow);
	}

	/**
	 * Pauses the execution for the specified number of seconds.
	 * @param secs the number of seconds to wait
	 */
	public static void wait(double secs) {
		try {
			Thread.sleep(1000 * (long) secs);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void wait(int secs) {
		try {
			Thread.sleep(1000 * (long) secs);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void scrollDownWithPageDownButton(int times) {
		Actions actions = new Actions(DriverManager.getDriver());
		for (int i = 0; i < times; i++) {
			actions.keyDown(Keys.PAGE_DOWN).build().perform();
			BrowserUtils.wait(1);
		}
	}

	public static void scrollUpWithPageUpButton(int times) {
		Actions actions = new Actions(DriverManager.getDriver());
		for (int i = 0; i < times; i++) {
			actions.keyDown(Keys.PAGE_UP).build().perform();
			BrowserUtils.wait(1);
		}
	}

	public static void scrollDownWithJavaScript(int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript("window.scrollBy(" + x + "," + y + ");");
		wait(2);
	}

	public static void clickOnElement(WebElement element) {
		Actions actions = new Actions(DriverManager.getDriver());
		actions.moveToElement(element).click().perform();
	}

	public static void executeJavaScript(String script, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
		js.executeScript(script, element);
	}

	public static void scrollToElement(WebElement element) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getDriver();
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		BrowserUtils.wait(2);
	}

	public static void setElementValueByLocator(String locator, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) DriverManager.getDriver();
		jsExecutor.executeScript("document.querySelector('" + locator + "').value='" + value + "'");
	}

}
