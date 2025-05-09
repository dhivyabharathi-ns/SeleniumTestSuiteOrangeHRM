package testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.Loginpage;
import pages.Logoutpage;
import utils.ReadExcelFile;


public class TC_UnitTest_01 extends BaseTest {

	public void login(String username, String password) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Loginpage loginPage = new Loginpage(driver);
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLogin();

	}

	@Test(dataProviderClass = ReadExcelFile.class, dataProvider = "testdata", groups = { "Unit", "Positive" })
	public void loginPositive(String username, String password) throws InterruptedException {

		TC_UnitTest_01 obj = new TC_UnitTest_01();
		obj.login(username, password);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		String actualTitle = driver.findElement(By.xpath("(//h6[text()='Dashboard'])")).getText();
		System.out.println(actualTitle);
		String expectedTitle = "Dashboard";

		// assert title
		Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match the expected value");

		System.out.println("Valid-Login attempt successful as expected");
	}

	
	@Test(dataProviderClass = ReadExcelFile.class, dataProvider = "testdata", groups = { "Unit", "Negative" })
	public void loginNegative(String username, String password) throws InterruptedException {

		TC_UnitTest_01 obj = new TC_UnitTest_01();
		obj.login(username, password);

		// assert title
		String actualTitle = driver.findElement(By.xpath("//h5[text()='Login']")).getText();

		String expectedTitle = "Login";

		Assert.assertEquals(actualTitle, expectedTitle,
				"Invalid Login attempt is allowed - vulnerable to security threats");

		System.out.println("Invalid-Login attempt Not allowed as expected");

	}

	public void logout() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		if (driver != null) {
			Logoutpage logout = new Logoutpage(driver);
			logout.logout();
			System.out.println("Logged out successfully");
		}
	}

}
