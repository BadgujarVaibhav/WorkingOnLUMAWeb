package lumawebtesting;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Verify_Sales_Page
{
public WebDriver dr;
	
	@BeforeClass
	public void setup()
	{
		WebDriverManager.chromedriver().setup();
		dr= new ChromeDriver(); // 1. Launch browser

		dr.manage().window().maximize();
		dr.get("https://magento.softwaretestingboard.com/"); // Navigate to url 'https://magento.softwaretestingboard.com/'

	}
	
	@Test(priority=1)
	public void HomePagetitleVerification()
	{
		String title= dr.getTitle(); // Verify that home page is visible successfully
		System.out.println(title);
		assertEquals(title,"Home Page","Incorrect Title");
		
	}
	
	@Test(priority=2)
	public void Sales_Page()
	{
		//click on sale page
		dr.findElement(By.xpath("//span[text()='Sale']")).click();
		
		//verify user navigated to sales page
		
		boolean verify_sales_page=dr.findElement(By.xpath("(//span[text()='Sale'])[2]")).isDisplayed();
		assertTrue(verify_sales_page,"Sale");
		
		JavascriptExecutor js_dowm2 = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm2.executeScript("window.scrollBy(0,500)", "");
		
		//click on any sales option
		
		dr.findElement(By.xpath("//span[text()='Shop Women’s Deals']")).click();
		
		//verify user navigated to Offers sections

		JavascriptExecutor js_dowm = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm.executeScript("window.scrollBy(0,500)", "");
			
		
	}
	
	@Test(priority=3)
	
	public void close()
	{
		dr.close();
		
	}

}
