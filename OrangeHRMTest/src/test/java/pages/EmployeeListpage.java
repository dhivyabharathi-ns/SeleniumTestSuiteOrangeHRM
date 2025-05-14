package pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmployeeListpage {
	private WebDriver driver;

	private By PIMlink = By.xpath("//a[@href='/web/index.php/pim/viewPimModule']");
	private By employeehintsfield = By.xpath("(//input[@placeholder='Type for hints...'])[1]");
	private By searchbutton = By.xpath("//button[@type='submit']");
	private By searchlist = By
			.xpath("(//div[@class='oxd-table-row oxd-table-row--with-border oxd-table-row--clickable']/div)[3]");
	private By checkbox = By.xpath(
			"(//span[@class='oxd-checkbox-input oxd-checkbox-input--active --label-right oxd-checkbox-input'])[2]");
	private By editiconbutton = By.xpath("//i[@class='oxd-icon bi-pencil-fill']");
	
	private By trashiconbutton = By.xpath("//i[@class='oxd-icon bi-trash']");
	private By yesdeletebuttonpopup = By
			.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--label-danger orangehrm-button-margin']");

	public EmployeeListpage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickonPIMlink() {

		driver.findElement(PIMlink).click();

	}

	public void searchemployee(String firstname, String middlename) throws InterruptedException {

		driver.findElement(employeehintsfield).sendKeys(firstname + " " + middlename);
		Thread.sleep(3000);
		driver.findElement(searchbutton).click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 500)");

	}


		
		public void editemployee(String firstname, String middlename) throws InterruptedException {
			WebElement searchresult = driver.findElement(searchlist);
			System.out.println("user selected: "+searchresult.getText());
		//	System.out.println(firstname + " " + middlename);
			Thread.sleep(3000);

			if (searchresult.getText().equalsIgnoreCase(firstname + " " + middlename)) {

				driver.findElement(editiconbutton).click();
				Thread.sleep(3000);
				
			}

	}
		
		
		public void selectanddelete(String firstname, String middlename) throws InterruptedException {
		try {
		driver.findElement(searchlist);
		}
		catch(Exception e)
		{
			System.out.println("No records found for the given username");	
		}
		
		String searchdisplayname= driver.findElement(searchlist).getText();
		assertEquals((firstname + " " + middlename),searchdisplayname,"No records found for the given username");
		Thread.sleep(3000);
			
			

			if (searchdisplayname.equalsIgnoreCase(firstname + " " + middlename)) {

				driver.findElement(checkbox).click();
				driver.findElement(trashiconbutton).click();
				Thread.sleep(3000);
				driver.findElement(yesdeletebuttonpopup).click();
			}
			}
			
			
}