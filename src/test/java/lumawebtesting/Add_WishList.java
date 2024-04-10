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

public class Add_WishList 
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
	public void Add_Wishlist()
	{
		// click on any category ex: women's
		
		dr.findElement(By.xpath("//span[text()='Women']")).click();
		
		// verify page navigated to women's page
		
		boolean women_page=dr.findElement(By.xpath("//*[@id=\"page-title-heading\"]/span")).isDisplayed();
		assertTrue(women_page,"Women");
		
		JavascriptExecutor js_dowm = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm.executeScript("window.scrollBy(0,2000)", "");
		
		// add any product to Wish list
		
		dr.findElement(By.xpath("(//div[@class='actions-secondary']//a)[1]")).click();
		
		
		//verify "customer login" page is visible
		
		boolean verify_customer_login= dr.findElement(By.xpath("//span[text()='Customer Login']")).isDisplayed();
		assertTrue(verify_customer_login,"Customer Login");
			
		// sign-up with email ID and Password
		
        dr.findElement(By.id("email")).sendKeys("vaibhavbadgujar15@gmail.com");
		
		dr.findElement(By.id("pass")).sendKeys("vabby$1234");
		
		// click on sign-up button
		
		dr.findElement(By.id("send2")).click();
		
		// select all product from page ex: Men's page
		
		dr.findElement(By.xpath("(//span[text()='Men'])[1]")).click();
				
		// verify page navigated to men's page
				
		boolean men_page=dr.findElement(By.xpath("(//span[text()='Men'])[2]")).isDisplayed();
		assertTrue(men_page,"Men");
				
				
		JavascriptExecutor js_dowm1 = (JavascriptExecutor) dr; // scroll down to see products
		js_dowm1.executeScript("window.scrollBy(0,2000)", "");
				
		
		// click on any product to add Wish List
		
		dr.findElement(By.xpath("(//div[@class='actions-secondary']//a[1])[1]")).click();
		
		// verify My wish List is visible
		
		boolean my_Wish_List= dr.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[1]/h1/span")).isDisplayed();
		assertTrue(my_Wish_List,"My Wish List");				
		
	}
	
	@Test(priority=3)
	public void close()
	{
		dr.close();
	}
	
}
