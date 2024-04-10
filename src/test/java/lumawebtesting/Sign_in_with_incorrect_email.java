package lumawebtesting;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sign_in_with_incorrect_email 
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
	
	public void sign_up_with_incorrect_email()
	{
		// click on sign_up button
		
		dr.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/ul/li[2]/a")).click();
		
		//verify "customer login" is visible
		
		boolean verify_customer_login= dr.findElement(By.xpath("//span[text()='Customer Login']")).isDisplayed();
		assertTrue(verify_customer_login,"Customer Login");
		
		// Enter Email ID and Password 
		
		dr.findElement(By.id("email")).sendKeys("vai@gmail.com");
		
		dr.findElement(By.id("pass")).sendKeys("vabby$1234");
		
		// click on sign-up button
		
		dr.findElement(By.id("send2")).click();
		
		// verify error message is Displayed
		
		boolean error_message=dr.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[2]/div/div")).isDisplayed();
		assertTrue(error_message,"The account sign-in was incorrect or your account is disabled temporarily. Please wait and try again later.");
		
		
	}
	
	@Test(priority=3)
	
	public void close()
	{
		dr.close();
		
	}
	


}

