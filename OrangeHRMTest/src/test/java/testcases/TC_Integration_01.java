package testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.BaseTest;
import pages.Addemployeepage;
import pages.Dashboardpage;
import pages.Personaldetailspage;
import pages.UserManagementpage;
import utils.ReadExcelFile;

public class TC_Integration_01 extends BaseTest {
	TC_UnitTest_01 obj = new TC_UnitTest_01();

	@SuppressWarnings("deprecation")
	@Test(dataProviderClass = ReadExcelFile.class, dataProvider = "testdata", groups = { "Integration" })
	public void addemployee(String username, String password, String firstname, String middlename, String lastname,
			String empusername, String emppwd, String nationality, String maritalstatus, String dob, String gender)
			throws Exception {
		
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// login
		obj.loginPositive(username, password);

		Thread.sleep(3000);

		// assert title
		String actualTitle = driver.findElement(By.xpath("(//h6[text()='Dashboard'])")).getText();
		String expectedTitle = "Dashboard";
		assertEquals(actualTitle, expectedTitle, "Page title does not match with the expected value, User not logged in");

		Thread.sleep(3000);

		// add employee
		Addemployeepage empadd = new Addemployeepage(driver);
		empadd.clickonPIMlink();
		empadd.clickonAddemployeeButton();
		empadd.enterDetailsInAddemployeepage(firstname, middlename, lastname, empusername, emppwd);
		Thread.sleep(5000);
		empadd.clickOnSave();

		// enter personal details
		Personaldetailspage emppersonal = new Personaldetailspage(driver);
		Actions actions = new Actions(driver);

		Thread.sleep(5000);

		// nationality
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(emppersonal.getNationalityfield()));
		emppersonal.selectNationality(nationality);

		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(emppersonal.curr, nationality);
		softAssert.assertNotEquals(emppersonal.curr, emppersonal.prev, "Expected Nationality unavailable in the list ");

		actions.keyDown(Keys.TAB).perform();
		Thread.sleep(3000);

		emppersonal.selectmaritalstatus(maritalstatus);

		// dob
		emppersonal.enterDOB(dob);

		// gender
		emppersonal.selectgender(gender);

		
		actions.keyDown(Keys.TAB).perform();
		Thread.sleep(3000);

		emppersonal.personaldetailssave();

		// scroll to top
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0)");

		// profile pic update
		emppersonal.updateprofilepic();

		// take screenshot of profile
		TakesScreenshot ts = (TakesScreenshot) driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		FileUtils.copyFile(file, new File("./ScreenShot_Folder/Test1_Login" + timestamp + ".png"));

		Thread.sleep(1000);
		js.executeScript("window.scrollTo(0, 200)");

		// save updates

		softAssert.assertAll();
		Thread.sleep(2000);

		obj.logout();
				
		obj.loginPositive(empusername, emppwd);
		assertEquals(actualTitle, expectedTitle, "Page title does not match with the expected value, User not logged in");

		Thread.sleep(3000);
		obj.logout();
		
	}

	@Test(dataProviderClass = ReadExcelFile.class, dataProvider = "testdata", groups = { "Integration" })
	public void assignUserRole(String username, String password, String empuser, String emppwd,String userrole)
			throws InterruptedException {
		
		//login as admin
		obj.loginPositive(username, password);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//Assign userrole as per testdata
		UserManagementpage roleassign = new UserManagementpage(driver);
		roleassign.clickonadminlink();
		roleassign.searchuser(empuser);
		roleassign.editemployee(empuser);
		roleassign.edituserrole(userrole);
		Thread.sleep(5000);

		// assert if the userrole is changed as expected
		Assert.assertEquals(roleassign.verifyuserrole(empuser, userrole), userrole,
				"User role not matching as expected");
		//logout
		
		obj.logout();
		
		obj.loginPositive(empuser, emppwd);
		
		//assert dashboard for user role
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Dashboardpage chkdash = new Dashboardpage(driver);
		Thread.sleep(3000);
		
		
		if(userrole.equalsIgnoreCase("Admin"))
		{
			assertEquals((chkdash.sidepanetopitemforUserrole(empuser, emppwd)), "Admin","wrong link displayed");
			// scroll down
			System.out.println("Admin link displayed as expected for Admin role");
			js.executeScript("window.scrollTo(0, 900)");

			assertTrue(chkdash.empdistbyLocation()>0, "item expected not displayed");
			System.out.println("Employee Distribution by Location - pane is displayed as expected for Admin role");
			assertTrue(chkdash.empdistbyunit()>0, "item expected not displayed");
			System.out.println("Employee Distribution by Subunit - pane is displayed as expected for Admin role");
		}
		else if(userrole.equalsIgnoreCase("ESS"))
		{
			assertEquals((chkdash.sidepanetopitemforUserrole(empuser, emppwd)), "Leave","wrong link displayed");
			js.executeScript("window.scrollTo(0, 900)");
			System.out.println("Admin link not displayed as expected for ESS role");
			assertTrue(chkdash.empdistbyLocation()==0, "Wrong item displayed");
			System.out.println("Employee Distribution by Location - pane is not displayed as expected for ESS role");
			assertTrue(chkdash.empdistbyunit()==0, "Wrong item displayed");
			System.out.println("Employee Distribution by Subunit - pane is not displayed as expected for ESS role");
		}
		Thread.sleep(3000);
		obj.logout();
		
	}
	


	
}
