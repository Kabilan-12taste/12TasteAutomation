package org.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;

	// **1️ Setup WebDriver**
	public static void setupBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	// **2️ Navigate to a URL**
	public static void openUrl(String url) {
		driver.get(url);
	}

	// **3️⃣ Close Browser**
	public static void closeBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

	// **4️⃣ Click Element**
	public static void clickElement(WebElement element) {
		element.click();
	}

	// **5️⃣ Enter Text in Input Field**
	public static void enterText(WebElement element, String text) {
		element.clear();
		element.sendKeys(text);
	}

	// **6️⃣ Get Text from an Element**
	public static String getElementText(WebElement element) {
		return element.getText();
	}

	// **7️⃣ Get Attribute Value**
	public static String getAttributeValue(WebElement element, String attribute) {
		return element.getAttribute(attribute);
	}

	// **8️⃣ Check if Element is Displayed**
	public static boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	// **9️⃣ Check if Element is Enabled**
	public static boolean isElementEnabled(WebElement element) {
		return element.isEnabled();
	}

	// **🔟 Check if Element is Selected**
	public static boolean isElementSelected(WebElement element) {
		return element.isSelected();
	}

	// **1️⃣1️⃣ JavaScript Click**
	public static void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	// **1️⃣2️⃣ Scroll to Element**
	public static void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	// **1️⃣3️⃣ Scroll Down by Pixels**
	public static void scrollByPixels(int pixels) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + pixels + ")");
	}

	// **1️⃣4️⃣ Select Value from Dropdown by Visible Text**
	public static void selectByVisibleText(WebElement element, String text) {
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(text);
	}

	// **1️⃣5️⃣ Select Value from Dropdown by Index**
	public static void selectByIndex(WebElement element, int index) {
		Select dropdown = new Select(element);
		dropdown.selectByIndex(index);
	}

	// **1️⃣6️⃣ Select Value from Dropdown by Value**
	public static void selectByValue(WebElement element, String value) {
		Select dropdown = new Select(element);
		dropdown.selectByValue(value);
	}

	// **1️⃣7️⃣ Get All Dropdown Options**
	public static List<WebElement> getAllDropdownOptions(WebElement element) {
		Select dropdown = new Select(element);
		return dropdown.getOptions();
	}

	// **1️⃣8️⃣ Wait Until Element is Visible**
	public static void waitForElementToBeVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	// **1️⃣9️⃣ Wait Until Element is Clickable**
	public static void waitForElementToBeClickable(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	// **2️⃣0️⃣ Accept Alert**
	public static void acceptAlert() {
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}

	// **2️⃣1️⃣ Dismiss Alert**
	public static void dismissAlert() {
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
	}

	// **2️⃣2️⃣ Get Alert Text**
	public static String getAlertText() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	// **2️⃣3️⃣ Switch to Frame by Index**
	public static void switchToFrame(int index) {
		driver.switchTo().frame(index);
	}

	// **2️⃣4️⃣ Switch to Frame by Name or ID**
	public static void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}

	// **2️⃣5️⃣ Switch to Default Content**
	public static void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}

	// **2️⃣6️⃣ Handle Keyboard Actions using Robot Class**
	public static void pressEnter() throws AWTException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	// **2️⃣7️⃣ Drag and Drop**
	public static void dragAndDrop(WebElement source, WebElement target) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(source, target).perform();
	}

	// **2️⃣8️⃣ Mouse Hover**
	public static void mouseHover(WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	// **2️⃣9️⃣ Double Click**
	public static void doubleClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}

	// **3️⃣0️⃣ Right Click (Context Click)**
	public static void rightClick(WebElement element) {
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();
	}

	// **3️⃣1️⃣ Take Screenshot**
	public static void takeScreenshot(String filePath) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(filePath);
		source.renameTo(destination);
	}
	
	public static String getCurrentURL() {
        return driver.getCurrentUrl();
    }

}
