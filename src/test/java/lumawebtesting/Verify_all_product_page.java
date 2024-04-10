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

public class Verify_all_product_page
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
	public void verify_all_product()
	{
		// select all product from page ex: women page
		
		dr.findElement(By.xpath("//span[text()='Women']")).click();
		
		// verify page navigated to women's page
		
		boolean women_page=dr.findElement(By.xpath("//*[@id=\"page-title-heading\"]/span")).isDisplayed();
		assertTrue(women_page,"Women");
		
		JavascriptExecutor js_dowm = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm.executeScript("window.scrollBy(0,2000)", "");
		
		// select all product from page ex: Men's page
		
		dr.findElement(By.xpath("(//span[text()='Men'])[1]")).click();
		
		// verify page navigated to men's page
		
		boolean men_page=dr.findElement(By.xpath("(//span[text()='Men'])[2]")).isDisplayed();
		assertTrue(men_page,"Men");
		
		
		JavascriptExecutor js_dowm1 = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm1.executeScript("window.scrollBy(0,2000)", "");
		
		// select all product from page ex: Gear page
		
		dr.findElement(By.xpath("//span[text()='Gear']")).click();
		
		// verify page navigated to Gears page
		
		boolean Gear_page=dr.findElement(By.xpath("//*[@id=\"page-title-heading\"]/span")).isDisplayed();
		assertTrue(Gear_page,"Gear");
				
		JavascriptExecutor js_dowm2 = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm2.executeScript("window.scrollBy(0,2000)", "");
		
		
	}
	
	@Test(priority=3)
	public void close()
	{
		dr.close();
	}
	
	
}
