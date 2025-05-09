package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Addemployeepage {
	
private WebDriver driver;
    
    private By PIMlink = By.xpath("//a[@href='/web/index.php/pim/viewPimModule']");
    private By addemployeebutton =By.xpath("//a[contains (text(),'Add Employee')]");
    private By firstnamefield = By.name("firstName");
    private By middlenamefield = By.name("middleName");
    private By lastnamefield = By.name("lastName");
    private By createLoginDetailsbutton=    By.xpath("//span[@class='oxd-switch-input oxd-switch-input--active --label-right']");
    private By usernamefield=   By.xpath("(//input[@class='oxd-input oxd-input--active'])[3]");
    private By passwordfield=  By.xpath("(//input[@type='password'])[1]");
    private By confirmpasswordfield=   By.xpath("(//input[@type='password'])[2]");
    private By savebutton=   By.xpath("//button[@type='submit']");
    
    
    
    public Addemployeepage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void clickonPIMlink() {
    	
    
    driver.findElement(PIMlink).click();

    }
    
    public void clickonAddemployeeButton() {
    	
        
    	driver.findElement(addemployeebutton).click();
    	
    	
    	
        }
	
	public void enterDetailsInAddemployeepage(String firstname,String middlename, String lastname, String empusername, String emppwd) throws InterruptedException {
	
	//Enter First name
	driver.findElement(firstnamefield).sendKeys(firstname);
	//Enter middle name
	driver.findElement(middlenamefield).sendKeys(middlename);
	//Enter last name
	driver.findElement(lastnamefield).sendKeys(lastname);
	driver.findElement(createLoginDetailsbutton).click();

	driver.findElement(usernamefield).sendKeys(empusername);
	driver.findElement(passwordfield).sendKeys(emppwd);
	driver.findElement(confirmpasswordfield).sendKeys(emppwd);

	
}
	public void clickOnSave() {
	driver.findElement(savebutton).click();
}
}
	