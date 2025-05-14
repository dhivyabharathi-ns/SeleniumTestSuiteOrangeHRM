package testcases;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.Addemployeepage;
import pages.Dashboardpage;
import pages.EmployeeListpage;
import pages.Personaldetailspage;
import pages.UserManagementpage;
import utils.ReadExcelFile;

public class TC_E2E_01 extends BaseTest {
	
	TC_UnitTest_01 e2e = new TC_UnitTest_01();
	@SuppressWarnings("deprecation")
	@Test (dataProviderClass = ReadExcelFile.class, dataProvider = "testdata", groups = { "E2E","Payroll" })
	public void configurePayroll(String username, String password,String empfirstname, String empmiddlename, String empuser, String emppwd,String salarycomp, String paygrade, String payfreq, String currency, String amount)
			throws InterruptedException {
		
		//login as admin
		e2e.loginPositive(username, password);
		
		
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		EmployeeListpage search = new EmployeeListpage(driver);
		search.clickonPIMlink();
		search.searchemployee(empfirstname, empmiddlename);
		search.editemployee(empfirstname, empmiddlename);
		
		
		Personaldetailspage pay=new Personaldetailspage(driver);
		pay.enterPayroll(salarycomp, paygrade, payfreq, currency, amount);
		
		
		e2e.logout();
		
		e2e.loginPositive(empuser, emppwd);
		pay.verifyPayroll(salarycomp, paygrade, payfreq, currency, amount);
		Thread.sleep(5000);
		e2e.logout();
	

}
	
	@Test(dataProviderClass = ReadExcelFile.class, dataProvider = "testdata", groups = { "E2E","DeleteUser" })
	public void deleteemployee(String username, String password, String firstname, String middlename) throws Exception {
		
		//Login as Admin
		e2e.loginPositive(username, password);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		//Delete employee as per testdata
		
		EmployeeListpage delemp = new EmployeeListpage(driver);
		delemp.clickonPIMlink();
		delemp.searchemployee(firstname, middlename);
		delemp.selectanddelete(firstname, middlename);
	
		

		// assert result after deletion
		String actualresult = driver
				.findElement(By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/span"))
				.getText();
		String expectedresult = "No Records Found";
		Assert.assertEquals(actualresult, expectedresult);
		System.out.println("user deleted");
		Thread.sleep(2000);
		
		//logout
		e2e.logout();
	}
}
