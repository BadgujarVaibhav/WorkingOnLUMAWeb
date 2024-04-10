package lumawebtesting;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Search_Product
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
	
	@Test(priority=3)
	public void Search_Function()
	{
		// enter the product to be search in input field
		
		dr.findElement(By.name("q")).sendKeys("Tops",Keys.ENTER);
		
		// verify searched products are visible.
		
		boolean searched_products=dr.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span")).isDisplayed();
		assertTrue(searched_products,"Search results for: 'tops'");
		
		// verify all the products related to search are visible.
		
		JavascriptExecutor js_dowm1 = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm1.executeScript("window.scrollBy(0,500)", "");
		
		
	}
	
	@Test(priority=3)
	public void close()
	{
		dr.close();
	}
	
}
