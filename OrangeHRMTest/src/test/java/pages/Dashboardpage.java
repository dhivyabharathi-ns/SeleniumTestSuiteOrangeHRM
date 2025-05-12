package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Dashboardpage {
	private WebDriver driver;

	private By topsidepanelink = By.xpath("(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'])[1]");
	private By empdistributionbyloc = By.xpath("//p[contains(normalize-space(),'Employee Distribution by Location')]");
	private By empdistributionbyunit = By.xpath("//p[contains(normalize-space(),'Employee Distribution by Sub Unit')]");

	public Dashboardpage(WebDriver driver) {
		this.driver=driver;
	}
	
	public int empdistbyLocation() {
		List<WebElement> emploc=driver.findElements(empdistributionbyloc);
		
		return emploc.size();
		}
	
	public int empdistbyunit() {
		List<WebElement> empunit=driver.findElements(empdistributionbyunit);
		
		return empunit.size();
		}
	
	public String sidepanetopitemforUserrole(String empuser, String emppwd) {
		String sidepanetopitem= driver.findElement(topsidepanelink).getText();
		return sidepanetopitem;
		
		}

}
