import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class InvalidLogInTestCases extends DriverSetup {
	String warningMessage;

	public WebElement elementVisible(By locator) {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return element;
	}

	private void logIn(String username, String password) {
		driver.get("https://qa-sandbox.apps.htec.rs/");

		driver.findElement(By.cssSelector("a.btn-secondary")).click();

		elementVisible(By.cssSelector("input[name=\"email\"]")).sendKeys(username);
		driver.findElement(By.cssSelector("input[name=\"password\"]")).sendKeys(password);
		driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();
		warningMessage = elementVisible(By.cssSelector("div.invalid-feedback")).getText();
	}

	public String getPropertyValue(String key) throws IOException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		InputStream resourceStream = loader.getResourceAsStream("propertyFile1.properties");
		Properties prop = new Properties();
		prop.load(resourceStream);
		return prop.getProperty(key);
	}

	@Test(dataProviderClass = DataProviderForLogIn.class, dataProvider = "LoginData")
	public void wrongEmail(String username, String password) throws Exception {
		logIn(username, password);
		Assert.assertEquals(warningMessage, getPropertyValue("WRONG_EMAIL"));
	}

	@Test(dataProviderClass = DataProviderForLogIn.class, dataProvider = "LoginData")
	public void invalidEmail(String username, String password) throws Exception {
		logIn(username, password);
		Assert.assertEquals(warningMessage, getPropertyValue("INVALID_EMAIL"));
	}

	@Test(dataProviderClass = DataProviderForLogIn.class, dataProvider = "LoginData")
	public void wrongPassword(String username, String password) throws Exception {
		logIn(username, password);
		Assert.assertEquals(warningMessage, getPropertyValue("WRONG_PASSWORD"));
	}

	@Test(dataProviderClass = DataProviderForLogIn.class, dataProvider = "LoginData")
	public void invalidPassword(String username, String password) throws Exception {
		logIn(username, password);
		Assert.assertEquals(warningMessage, getPropertyValue("INVALID_PASSWORD"));
	}

	@Test(dataProviderClass = DataProviderForLogIn.class, dataProvider = "LoginData")
	public void emptyEmail(String username, String password) throws Exception {
		logIn(username, password);
		Assert.assertEquals(warningMessage, getPropertyValue("EMPTY_EMAIL"));
	}

	@Test(dataProviderClass = DataProviderForLogIn.class, dataProvider = "LoginData")
	public void emptyPassword(String username, String password) throws Exception {
		logIn(username, password);
		Assert.assertEquals(warningMessage, getPropertyValue("EMPTY_PASSWORD"));
	}

	@Test(dataProviderClass = DataProviderForLogIn.class, dataProvider = "LoginData")
	public void emptyEmailAndPassword(String username, String password) throws Exception {
		logIn(username, password);
		Assert.assertEquals(driver.findElement(By.cssSelector("div.form-group:nth-child(1) > div")).getText(),
				getPropertyValue("EMPTY_EMAIL"));
		Assert.assertEquals(driver.findElement(By.cssSelector("div.form-group:nth-child(2) > div")).getText(),
				getPropertyValue("EMPTY_PASSWORD"));
	}

}
