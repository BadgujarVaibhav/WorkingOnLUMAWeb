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

public class Remove_Product_from_Cart
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
	public void Remove_Product_cart() throws InterruptedException
	{
		// click on any category from page
		
		dr.findElement(By.xpath("//span[text()='Women']")).click();
				
		// verify page navigated to women's page
				
		boolean women_page=dr.findElement(By.xpath("//*[@id=\"page-title-heading\"]/span")).isDisplayed();
		assertTrue(women_page,"Women");
				
		JavascriptExecutor js_dowm = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm.executeScript("window.scrollBy(0,2000)", "");
				
		// Hover on any product and click on it.
				
		dr.findElement(By.xpath("//*[@id=\"maincontent\"]/div[4]/div[1]/div[2]/div[3]/div/div/ol/li[1]/div/a/span/span/img")).click();
		
		JavascriptExecutor js_dowm1 = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm1.executeScript("window.scrollBy(0,500)", "");
		
		// select and size, color and quantity and click "Add to cart"
		
		dr.findElement(By.id("option-label-size-143-item-167")).click();
		dr.findElement(By.id("option-label-color-93-item-50")).click();
		dr.findElement(By.xpath("//*[@id=\"product-addtocart-button\"]")).click();
		Thread.sleep(3000);
		
		//click on cart button
		
		dr.findElement(By.xpath("(//span[@class='counter qty'])[1]")).click();
		dr.findElement(By.xpath("//span[text()='View and Edit Cart']")).click();
		
		//verify user navigated to cart page and remove product
		
		
		boolean verify_cart=dr.findElement(By.xpath("//span[text()='Shopping Cart']")).isDisplayed();
		assertTrue(verify_cart,"Shopping Cart");
				
		JavascriptExecutor js_dowm2 = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm2.executeScript("window.scrollBy(0,500)", "");
		
		dr.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr[2]/td/div/a[2]")).click();
		
		
				
	}
	
	@Test(priority=3)
	
	public void close()
	{
		dr.close();
		
	}
	
}
