package lumawebtesting;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sign_in_with_correct_email
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
	
	public void sign_up_with_correct_email()
	{
		// click on sign_up button
		
		dr.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/a")).click();
		
		//verify "customer login" is visible
		
		boolean verify_customer_login= dr.findElement(By.xpath("//span[text()='Customer Login']")).isDisplayed();
		assertTrue(verify_customer_login,"Customer Login");
		
		// Enter Email ID and Password 
		
		dr.findElement(By.id("email")).sendKeys("vaibhavbadgujar15@gmail.com");
		
		dr.findElement(By.id("pass")).sendKeys("vabby$1234");
		
		// click on sign-up button
		
		dr.findElement(By.id("send2")).click();
		
		// verify user logged in and navigated to home page
		
		boolean verify_to_home= dr.findElement(By.xpath("//span[text()='New Luma Yoga Collection']")).isDisplayed();
		assertTrue(verify_to_home,"New Luma Yoga Collection");
		
		// click on drop down arrow button
		
		dr.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/span/button")).click();
				
		// click on sign out button from drop down menu
				
		dr.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/div/ul/li[3]/a")).click();
				
		
	}
	
	@Test(priority=3)
	
	public void close()
	{
		dr.close();
		
	}
	


}
