package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Loginpage {
private WebDriver driver;
    
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    
    
    public Loginpage(WebDriver driver) {
        this.driver = driver;
    }
    public void enterUsername(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickLogin() {
      
        driver.findElement(loginButton).click();
    }
}
