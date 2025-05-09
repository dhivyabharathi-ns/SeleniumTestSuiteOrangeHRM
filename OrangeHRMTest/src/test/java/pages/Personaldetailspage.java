package pages;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Personaldetailspage {
	private WebDriver driver;

	private By nationalityfield = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[1]");
	private By maritalstatusfield = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[2]");
		private By dateofbirthfield = By.xpath("(//input[@placeholder='yyyy-dd-mm'])[2]");
	private By profilepicLink = By.xpath("(//div[@class='orangehrm-edit-employee-image'])[1]");
	private By profilepicUpdate = By
			.cssSelector("button[class='oxd-icon-button oxd-icon-button--solid-main employee-image-action']");
	private By gendermaleradiobutton = By.xpath("//label[normalize-space()='Male']");
	private By genderfemaleradiobutton = By.xpath("//label[normalize-space()='Female']");
	
	private By salarymenulink = By.xpath("//a[normalize-space()='Salary']");
	
	private By addsalarycomponenticon = By.xpath("(//i[@class='oxd-icon bi-plus oxd-button-icon'])[1]");
	private By salarycomponentfield = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
	private By paygradeselect = By.xpath("(//div[@class='oxd-select-text-input'])[1]");
	private By payfrequencyselect = By.xpath("(//div[@class='oxd-select-text-input'])[2]");
	private By currencyselect = By.xpath("(//div[@class='oxd-select-text-input'])[3]");
	private By amountfield = By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");			
	private By salarysavebutton = By.xpath("(//button[@type='submit'])[1]");
	
	private By personaldetailssavebutton = By.xpath("(//button[@type='submit'])[1]");
	
	private By profilepicsavebutton = By.xpath("//button[@type='submit']");
	private String imagefilePath = "C:\\Users\\dhivy\\eclipse-workspace\\OrangeHRMTest\\src\\test\\resources\\testdata\\profilepic.png";

	private By myInfolink = By.xpath("//span[normalize-space()='My Info']");
	private By salarylink = By.xpath("//a[normalize-space()='Salary']");
	private By verifysalarycomp = By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell'])[1]");
	private By verifyamount = By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell'])[2]");
	private By verifycurrency = By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell'])[3]");
	private By verifypayfreq = By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell'])[4]");
	public String prev = "";
	public String curr = "";
	
	
	public Personaldetailspage(WebDriver driver) {
		this.driver = driver;
	}
	public By getNationalityfield() {
		return nationalityfield;
	}

	public void setNationalityfield(By nationalityfield) {
		this.nationalityfield = nationalityfield;
	}

	

	public void selectNationality(String nationality) {
		Actions actions = new Actions(driver);
		WebElement nationalitydd = driver.findElement(nationalityfield);
		nationalitydd.click();

		do {
			prev = curr;
			actions.keyDown(Keys.DOWN).perform();
			curr = nationalitydd.getText();
			System.out.println(curr);
		} while (!prev.equalsIgnoreCase(curr) && !curr.equalsIgnoreCase(nationality));
		
	
	}
	public void selectmaritalstatus(String maritalstatus) {
		Actions actions = new Actions(driver);
		WebElement maritalstatusdd = driver.findElement(maritalstatusfield);
		maritalstatusdd.click();

		do {
			actions.keyDown(Keys.DOWN).perform();
			System.out.println(maritalstatusdd.getText());
		} while (!maritalstatusdd.getText().equalsIgnoreCase(maritalstatus));
		
		
	}

	public void enterDOB(String dob) {

		driver.findElement(dateofbirthfield).sendKeys(dob);

	}
	
	public void selectgender(String gender) {
	if(gender.equalsIgnoreCase("Male"))
	{
		driver.findElement(gendermaleradiobutton).click();
	}
	else if(gender.equalsIgnoreCase("Female"))
	{
		driver.findElement(genderfemaleradiobutton).click();
	}
	}
	
	
	public void personaldetailssave() {
		driver.findElement(personaldetailssavebutton).click();
	}

	public void updateprofilepic() throws InterruptedException, AWTException {

		StringSelection stringSelection = new StringSelection(imagefilePath);

		// copy image file path to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		Thread.sleep(5000);
		driver.findElement(profilepicLink).click();
		Thread.sleep(3000);
		driver.findElement(profilepicUpdate).click();

		// Create a Robot instance to simulate CTRL + V in the windows popup
		Robot robot = new Robot();
		robot.delay(10000);
		// Simulate pasting the file path
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(5000);
		// Simulate pressing Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(6000);
		Thread.sleep(2000);
		driver.findElement(profilepicsavebutton).click();
	}
	
	public void enterPayroll(String salarycomponent, String paygrade,String payfreq,String currency,String amount) {
		Actions actions = new Actions(driver);
		driver.findElement(salarymenulink).click();
		driver.findElement(addsalarycomponenticon).click();
		driver.findElement(salarycomponentfield).sendKeys(salarycomponent);
	
		String currentpaygradedd = driver.findElement(paygradeselect).getText();
		System.out.println(currentpaygradedd);
		driver.findElement(paygradeselect).click();
		if (!currentpaygradedd.equalsIgnoreCase(paygrade)) {
		do {
			actions.keyDown(Keys.DOWN).perform();
		
			currentpaygradedd=driver.findElement(paygradeselect).getText();
		} while (!currentpaygradedd.equalsIgnoreCase(paygrade));
		
		}
		
		String currentpayfreqdd = driver.findElement(payfrequencyselect).getText();
		System.out.println(currentpayfreqdd);
		driver.findElement(payfrequencyselect).click();
		
		if (!currentpayfreqdd.equalsIgnoreCase(payfreq)) {
		do {
			actions.keyDown(Keys.DOWN).perform();
			currentpayfreqdd=driver.findElement(payfrequencyselect).getText();
		} while (!currentpayfreqdd.equalsIgnoreCase(payfreq));
		actions.keyDown(Keys.ENTER).perform();
		}
		String currentcurrencydd = driver.findElement(currencyselect).getText();
		System.out.println(currentcurrencydd);
		driver.findElement(currencyselect).click();
		if (!currentcurrencydd.equalsIgnoreCase(currency)) {
		do {
			actions.keyDown(Keys.DOWN).perform();
			currentcurrencydd=driver.findElement(currencyselect).getText();
		} while (!currentcurrencydd.equalsIgnoreCase(currency));
		actions.keyDown(Keys.ENTER).perform();
		
		}
		driver.findElement(amountfield).sendKeys(amount);
		
		driver.findElement(salarysavebutton).click();
	}

	public void verifyPayroll(String salarycomponent, String paygrade,String payfreq,String currency,String amount) {
		
		driver.findElement(myInfolink).click();
		driver.findElement(salarylink).click();
		assertEquals(driver.findElement(verifysalarycomp).getText(),salarycomponent);
		System.out.println(driver.findElement(verifysalarycomp).getText() +"(salarycomponent in the salary page)"+" = "+ salarycomponent +"(given in the test data sheet) : verified");
		assertEquals(driver.findElement(verifyamount).getText(),amount);
		System.out.println(driver.findElement(verifyamount).getText() +"(amount in the salary page)"+" = "+ amount +"(given in the test data sheet) : verified");
		assertEquals(driver.findElement(verifycurrency).getText(),currency);
		System.out.println(driver.findElement(verifycurrency).getText() +"(currency in the salary page)"+" = "+ currency +"(given in the test data sheet) : verified");
		assertEquals(driver.findElement(verifypayfreq).getText(),payfreq);
		System.out.println(driver.findElement(verifypayfreq).getText() +"(pay frequency in the salary page)"+" = "+ payfreq +"(given in the test data sheet) : verified");
		
		
	}
	
}
