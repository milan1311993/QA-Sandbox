import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ValidLogInTestCase extends DriverSetup {

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
	}

	@Test(dataProviderClass = DataProviderForLogIn.class, dataProvider = "LoginData")
	public void validLogIn(String username, String password) {
		logIn(username, password);
		Assert.assertTrue(elementVisible(By.cssSelector("div.col-sm-3:nth-child(1)")).isDisplayed());
	}
}
