package lumawebtesting;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Order_and_return_with_Validate_Data
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
	public void Search_Order_with_Validate_Data()
	{
		JavascriptExecutor down = (JavascriptExecutor) dr; // scroll down
		down.executeScript("window.scrollBy(0,2500)", "");
		
		// click on order and return
		
		dr.findElement(By.xpath("//a[text()='Orders and Returns']")).click();
		
		// verify user navigated to order and return page.
		
	   String order_return=	dr.findElement(By.xpath("//span[text()='Orders and Returns']")).getText();
	   assertEquals(order_return,"Orders and Returns");
	   
	   JavascriptExecutor down1 = (JavascriptExecutor) dr; // scroll down
		down1.executeScript("window.scrollBy(0,500)", "");
		
		// enter Order details to search of that order.
		
		dr.findElement(By.id("oar-order-id")).sendKeys("000051761");
		dr.findElement(By.id("oar-billing-lastname")).sendKeys("Badgujar");
		WebElement  abc= dr.findElement(By.name("oar_type"));
		Select op = new Select(abc);
		op.selectByValue("email");
		dr.findElement(By.id("oar_email")).sendKeys("vaibhavbadgujar16@gmail.com");
		dr.findElement(By.xpath("//*[@id=\"oar-widget-orders-and-returns-form\"]/div/div/button")).click();
		
		// verify user navigated to searched order details.
		
		boolean searched_order= dr.findElement(By.xpath("(//strong[text()='Order Information'])[1]")).isDisplayed();
		assertTrue(searched_order,"Order Information");
		
		
		
	}
	
	@Test(priority=3)
	public void close()
	{
		dr.close();
	}
	
	
	
	
	
}