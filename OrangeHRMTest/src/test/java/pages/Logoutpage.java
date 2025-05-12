package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Logoutpage {
private WebDriver driver;
    
    private By logoutdropdown = By.xpath("//p[@class='oxd-userdropdown-name']");
    private By logoutbutton =By.xpath("//a[normalize-space()='Logout']");
   
    
    public Logoutpage(WebDriver driver) {
        this.driver = driver;
    }
    public void logout() {
    	driver.findElement(logoutdropdown).click();
		driver.findElement(logoutbutton).click();

    }
   
}
