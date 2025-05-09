package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import utils.ReadExcelFile;

public class BaseTest {
	
	public static WebDriver driver;
	public static FileReader fr;
	public static Properties p = new Properties();
	
	@BeforeMethod(groups= {"Unit","Integration"})

	public void setUp() throws IOException
	{
		if (driver==null)
		{
		 fr = new FileReader(System.getProperty("user.dir")+ "\\src\\test\\resources\\configfiles\\config.properties");
			p.load(fr);
		}
		
		if(p.getProperty("browser").equals("chrome"))
		{
		 driver=  new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get(p.getProperty("testurl"));
		}
		else if(p.getProperty("browser").equals("firefox"))
		{
		 driver=  new FirefoxDriver();
		 driver.manage().window().maximize();
		 driver.get(p.getProperty("testurl"));
		}
	
	}
	@AfterMethod(groups= {"Unit","Integration"})

	public void tearDown()
	{
		
		if(driver!=null)
		driver.quit();
	}
}
