import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class DriverSetup {
	static WebDriver driver;
	static WebDriverWait wait;

	@BeforeClass
	public void setupDriver() {
		System.setProperty("webdriver.gecko.driver",
				"D:\\QA obuka\\Automatsko testiranje\\Selenium\\geckodriver-v0.24.0-win64\\geckodriver.exe");

		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 30, 500);
	}

	@AfterClass
	public void closeDriver() {
		driver.close();
	}
}
