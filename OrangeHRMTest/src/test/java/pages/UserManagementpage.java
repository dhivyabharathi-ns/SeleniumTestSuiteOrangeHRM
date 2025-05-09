package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class UserManagementpage {
	private WebDriver driver;

	private By adminlink = By.xpath("(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'])[1]");
	private By usernamefield = By.xpath("//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']");
	private By searchbutton = By.xpath("//button[@type='submit']");
	private By searchresultusername = By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell'])[2]");
	private By searchresultuserrole = By.xpath("(//div[@class='oxd-table-cell oxd-padding-cell'])[3]");
	private By edituserroleicon = By.xpath("(//i[@class='oxd-icon bi-pencil-fill'])[1]");
	private By savebutton = By.xpath("//button[@type='submit']");
	private By userrolefield = By.xpath("(//div[@class='oxd-select-text-input'])[1]");

	public UserManagementpage(WebDriver driver) {
		this.driver = driver;

	}

	public void clickonadminlink() {

		driver.findElement(adminlink).click();
		System.out.println("Admin link clicked");

	}

	public void searchuser(String empuser) throws InterruptedException {

		driver.findElement(usernamefield).sendKeys(empuser);
		Thread.sleep(3000);
		driver.findElement(searchbutton).click();
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 500)");

	}

	public void editemployee(String empuser) throws InterruptedException {
		WebElement searchresult = driver.findElement(searchresultusername);
		System.out.println(searchresult.getText());
		System.out.println(empuser);
		Thread.sleep(3000);

		if (searchresult.getText().equalsIgnoreCase(empuser)) {
			driver.findElement(edituserroleicon).click();
			Thread.sleep(3000);

		}
	}

	public void edituserrole(String userrrole) throws InterruptedException {
		Actions actions = new Actions(driver);

		String currentuserrole = driver.findElement(userrolefield).getText();
		System.out.println(currentuserrole);
		 driver.findElement(userrolefield).click();

		if (!currentuserrole.equalsIgnoreCase(userrrole)) {
			do {
			actions.keyDown(Keys.DOWN).perform();
			currentuserrole=driver.findElement(userrolefield).getText();
			}while(!currentuserrole.equalsIgnoreCase(userrrole));
		actions.keyDown(Keys.ENTER).perform();
		}
		driver.findElement(savebutton).click();
}
	
	public String verifyuserrole(String empuser,String userrrole) throws InterruptedException {
	
		searchuser(empuser);
		String updateduserrole = driver.findElement(searchresultuserrole).getText();
		return updateduserrole;

}
}
