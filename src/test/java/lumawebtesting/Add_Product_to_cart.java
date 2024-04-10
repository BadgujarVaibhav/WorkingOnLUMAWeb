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

public class Add_Product_to_cart 
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
	public void Add_to_cart()
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
		
		// select and size, color and quantity and click "Add to cart"
		
		dr.findElement(By.id("option-label-size-143-item-167")).click();
		dr.findElement(By.id("//*[@id=\\\"product-addtocart-button\\\"]")).click();
		dr.findElement(By.xpath("//*[@id=\"product-addtocart-button\"]")).click();
		
		// add another product to add to cart
		
		dr.findElement(By.xpath("(//span[text()='Men'])[1]")).click();
		
		// verify page navigated to men's page
		
		boolean men_page=dr.findElement(By.xpath("(//span[text()='Men'])[2]")).isDisplayed();
		assertTrue(men_page,"Men");
		
		
		JavascriptExecutor js_dowm1 = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm1.executeScript("window.scrollBy(0,2000)", "");
		
		
		dr.findElement(By.xpath("//*[@id=\"maincontent\"]/div[4]/div[1]/div[2]/div[3]/div/div/ol/li[1]/div/a/span/span/img")).click();
		
		dr.findElement(By.xpath("//*[@id=\"option-label-size-143-item-169\"]")).click();
		dr.findElement(By.xpath("//*[@id=\"option-label-color-93-item-52\"]")).click();
		dr.findElement(By.xpath("//*[@id=\"product-addtocart-button\"]")).click();
		
		// click on cart button
		dr.findElement(By.xpath("(//span[text()='My Cart'])[1]")).click();
		
		dr.findElement(By.xpath("//span[text()='View and Edit Cart']")).click();
		
		//verify products are added to cart
		
		boolean verify_cart=dr.findElement(By.xpath("//span[text()='Shopping Cart']")).isDisplayed();
		assertTrue(verify_cart,"Shopping Cart");
		
		
	}
	
	@Test(priority=3)
	
	public void close()
	{
		dr.close();
		
	}
	
	
}
